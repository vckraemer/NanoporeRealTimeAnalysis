import InputFormat.FASTQInputFileFormat;
import InputFormat.QRecord;
import MapFunctions.*;
import Model.CentrifugeResult;
import Model.LastResult;
import Model.LineageResults;
import Model.Read;
import TransformFunctions.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.elasticsearch.spark.streaming.api.java.JavaEsSparkStreaming;
import org.spark_project.guava.collect.ImmutableMap;
import scala.Tuple2;

public class Streaming {

    public JavaSparkContext ssc;

    public static void main(String[] args) throws InterruptedException {

        String esIp = "localhost";
        String esPort = "9200";
        String esIndexPrefix = "";
        String folderPath = "";

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
                }
                i++;
            }
        }else{
            System.out.println("Check Usage");
            System.exit(1);
        }
        if(folderPath.equals("")){
            System.out.println("Check Usage");
            System.exit(1);
        }

        SparkConf conf = new SparkConf().setAppName("fileStreaming");
        conf.set("es.index.auto.create", "true");
        conf.set("es.nodes", esIp);
        conf.set("es.port", esPort);
        conf.set("es.net.http.auth.user", "");
        conf.set("es.net.http.auth.pass", "");
        conf.set("es.resource", esIndexPrefix+"sparkstreaming");
        conf.set("es.nodes.wan.only", "true");

        JavaStreamingContext ssc = new JavaStreamingContext(conf, new Duration(10000));

        JavaDStream<String> stream = ssc.textFileStream(folderPath);
        JavaDStream<String> fastq = stream.map(new ReadFastq()).filter(x -> x!=null);
        JavaDStream<Read> reads = fastq.map(new ToReadObject()).filter(x -> x!=null).map(new CalculateGCContent()).transform(new SaveToElastic(esIndexPrefix));

        //JavaPairInputDStream<String, QRecord> fastqstream = ssc.fileStream(folderPath, String.class, QRecord.class, FASTQInputFileFormat.class);
        //JavaDStream<Read> reads = fastqstream.map(Tuple2::_2).map(new FromQRecordToReadObject()).filter(x -> x!=null).map(new CalculateGCContent()).transform(new SaveToElastic(esIndexPrefix));
        JavaDStream<String> savedReads = reads.map(new ToFasta()).cache();

        JavaDStream<String> lastResults = savedReads.transform(new PipeToLast());
        JavaDStream<String> resultStream = lastResults.map(new GetLastResults()).filter(x -> x!=null);
        JavaDStream<LastResult> endResults = resultStream.map(new ToLastResult());
        JavaEsSparkStreaming.saveToEs(endResults, esIndexPrefix+"lastresults");

        JavaDStream<String> centrifugeResults = savedReads.transform(new PipeToCentrifuge());
        JavaDStream<CentrifugeResult> endResult = centrifugeResults.map(new ToCentrifugeResult()).filter(x -> x!=null).transform(new SaveCentrifugeResultsToElastic(esIndexPrefix));
        JavaDStream<LineageResults> lineage = endResult.map(new ToLineageInput()).filter(x -> x!=null).transform(new PipeToTaxonomy2Lineage()).map(new ToLineageResult()).filter(x -> x!=null);
        JavaEsSparkStreaming.saveToEs(lineage, esIndexPrefix+"lineageresults", ImmutableMap.of("es.mapping.id","id"));



        //JavaDStream<String> metamapsresults = stream.transform(new PipeToMetaMaps());

//

        //JavaEsSparkStreaming.saveToEs(endResult, "centrifugeresults", ImmutableMap.of("es.mapping.id","id"));
        //lineage.dstream().saveAsTextFiles("/vol/Ma_Data_new/lineageresults", "txt");

        //JavaEsSparkStreaming.saveToEs(endResults, "lastresults");

        //savedReads.dstream().saveAsTextFiles("/vol/Ma_Data_new/lasttestresults", "txt");

        //JavaDStream<String> fastq = stream.map(new ReadFastq()).filter(x -> x!=null);
        //JavaDStream<Read> reads = fastq.map(new ToReadObject()).filter(x -> x!=null).map(new CalculateGCContent());
        //JavaDStream<String> savedReads = reads.transform(new SaveToElastic()).map(new ToFasta());
        //savedReads.cache();

        //JavaDStream<String> blastResults = savedReads.transform(new PipeToBlast()).map(new GetBlastResultJsonSingleReport()).filter(x -> x!=null);
        //JavaDStream<String> blastxResults = savedReads.transform(new PipeToBlastX()).map(new GetBlastResultJsonSingleReport()).filter(x -> x!=null);
        //results.cache();
        //results.print();

        //results.dstream().saveAsTextFiles("fil
        //home/vanessa/Masterarbeit/workdir/test", "txt");
        //JavaEsSparkStreaming.saveJsonToEs(blastResults, "sparkblastresults", ImmutableMap.of("es.mapping.id","report.results.search.query_title","es.mapping.exclude","qseq, hseq, midline"));
        //JavaEsSparkStreaming.saveJsonToEs(blastxResults, "sparkblastxresults", ImmutableMap.of("es.mapping.id","report.results.search.query_title","es.mapping.exclude","qseq, hseq, midline"));
        //JavaEsSparkStreaming.saveJsonToEs(blastxResults, "sparkblastxresults", ImmutableMap.of("es.mapping.id","report.results.search.query_title"));
        //JavaEsSparkStreaming.saveToEs(readsblast, "sparkstreaming");

        ssc.start();
        ssc.awaitTermination();

    }

}

