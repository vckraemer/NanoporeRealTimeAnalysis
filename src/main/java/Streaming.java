import InputFormat.FastqInputFormat;
import MapFunctions.*;
import Model.*;
import TransformFunctions.*;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.elasticsearch.spark.streaming.api.java.JavaEsSparkStreaming;
import org.spark_project.guava.collect.ImmutableMap;
import scala.Tuple2;
import java.util.concurrent.atomic.AtomicInteger;



public class Streaming {

    public static void main(String[] args) throws InterruptedException {

        IntegerInkrement count = new IntegerInkrement(0);

        String esIp = "localhost";
        String esPort = "9200";
        String esIndexPrefix = "";
        String folderPath = "";
        String lastDatabase= "";
        String centrifugeDatabasePath = "";
        int repartitioningValue = 1;
        String lastThreads ="";
        String centrifugeThreads ="";

        if(args.length >=2 && args.length % 2 == 0){
            int i = 0;
            while(i < args.length){

                if(args[i].equals("-ip")){
                    esIp = args[i+1];
                } else if(args[i].equals("-port")){
                    esPort = args[i+1];
                } else if(args[i].equals("-prefix")){
                    esIndexPrefix = args[i+1];
                } else if(args[i].equals("-f")){
                    folderPath = args[i+1];
                } else if(args[i].equals("-ldb")){
                    lastDatabase = args[i+1];
                } else if(args[i].equals("-cidx")){
                    centrifugeDatabasePath = args[i+1];
                } else if(args[i].equals("-rp")){
                    try {
                        repartitioningValue = Integer.parseInt(args[i+1]);
                    }catch (Exception e){
                        System.out.println("-rp Argument must be an Integer value!");
                    }
                } else if(args[i].equals("-lp")){
                    lastThreads = args[i+1];
                } else if(args[i].equals("-cp")){
                    centrifugeThreads = args[i+1];
                }
                i++;
            }
        }else{
            System.out.println("Check Usage");
            System.exit(1);
        }
        if(folderPath.equals("") || lastDatabase.equals("")){
            System.out.println("Check Usage");
            System.exit(1);
        }

        if(centrifugeDatabasePath.equals("")){
            centrifugeDatabasePath = "provided";
        }

        SparkConf conf = new SparkConf().setAppName("fileStreaming");
        conf.set("spark.scheduler.mode", "FAIR");
        conf.set("es.index.auto.create", "true");
        conf.set("es.nodes", esIp);
        conf.set("es.port", esPort);
        conf.set("es.net.http.auth.user", "");
        conf.set("es.net.http.auth.pass", "");
        conf.set("es.batch.size.entries", "4000");
        conf.set("es.resource", esIndexPrefix+"sparkstreaming");
        conf.set("es.nodes.wan.only", "true");
        conf.set("mapred.max.split.size","10000000");

        JavaStreamingContext ssc = new JavaStreamingContext(conf, new Duration(10000));

        JavaPairInputDStream<LongWritable, Text> fastqRDD =  ssc.fileStream(folderPath, LongWritable.class, Text.class, FastqInputFormat.class);
        JavaDStream<Read> reads = fastqRDD.map(Tuple2::_2).map(new TextToString()).map(new ToReadObject()).filter(x -> x!=null).map(new CalculateGCContent()).transform(new SaveToElastic(esIndexPrefix));

        JavaDStream<String> savedReads = reads.map(new ToFasta()).cache();
        savedReads.context().sparkContext().setLocalProperty("spark.scheduler.pool", "fair_pool");

        JavaDStream<String> centrifugeResults = savedReads.transform(new PipeToCentrifuge(centrifugeDatabasePath, centrifugeThreads));
        JavaDStream<CentrifugeResult> endResult = centrifugeResults.map(new ToCentrifugeResult()).filter(x -> x!=null).transform(new SaveCentrifugeResultsToElastic(esIndexPrefix));
        //JavaDStream<CentrifugeResult> savedResults = endResult.cache();
        //JavaEsSparkStreaming.saveToEs(savedResults, esIndexPrefix+"centrifugeresults");

        JavaDStream<LineageResult> lineage = endResult.map(new ToLineageInput()).filter(x -> x!=null).transform(new PipeToPythonLineage()).map(new ToLineageResult()).filter(x -> x!=null);
        JavaEsSparkStreaming.saveToEs(lineage, esIndexPrefix+"lineageresults");

        JavaDStream<LastResult> lastResults = savedReads.transform(new PipeToLast(lastDatabase, lastThreads)).map(new ToLastResult(lastDatabase)).filter(x -> x!=null);
        JavaEsSparkStreaming.saveToEs(lastResults, esIndexPrefix+"lastresults", ImmutableMap.of("es.mapping.id","id"));

        //JavaDStream<LineageResult> lineage = savedResults.map(new ToLineageInput()).filter(x -> x!=null).transform(new PipeToTaxonomy2Lineage()).map(new ToLineageResult()).filter(x -> x!=null);
        //JavaEsSparkStreaming.saveToEs(lineage, esIndexPrefix+"lineageresults");

        //JavaDStream<String> blastResults = savedReads.transform(new PipeToBlast()).map(new GetBlastResultJsonSingleReport()).filter(x -> x!=null);
        //JavaDStream<String> blastxResults = savedReads.transform(new PipeToBlastX()).map(new GetBlastResultJsonSingleReport()).filter(x -> x!=null);

        ssc.start();
        ssc.awaitTermination();

    }

}
