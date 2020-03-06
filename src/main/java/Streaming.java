import MapFunctions.*;
import Model.CentrifugeResult;
import Model.LastResult;
import Model.Read;
import TransformFunctions.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.elasticsearch.spark.streaming.api.java.JavaEsSparkStreaming;
import org.spark_project.guava.collect.ImmutableMap;

import java.util.ArrayList;

public class Streaming {

    public JavaSparkContext ssc;

    public static void main(String[] args) throws InterruptedException {

        SparkConf conf = new SparkConf().setAppName("fileStreaming").setMaster("local[*]");
        conf.set("es.index.auto.create", "true");
        conf.set("es.nodes", "localhost");
        conf.set("es.port", "9200");
        conf.set("es.net.http.auth.user", "");
        conf.set("es.net.http.auth.pass", "");
        conf.set("es.resource", "sparkstreaming");
        conf.set("es.nodes.wan.only", "true");
        JavaStreamingContext ssc = new JavaStreamingContext(conf, new Duration(10000));
        JavaDStream<String> stream = ssc.textFileStream("/vol/Ma_Data/sequences");
        //JavaDStream<String> stream = ssc.textFileStream("/home/vanessa/Masterarbeit/workdir/sequences/");


        //JavaDStream<String> metamapsresults = stream.transform(new PipeToMetaMaps());
        //metamapsresults.print();

        //JavaDStream<String> centrifugeResults = stream.transform(new PipeToCentrifuge());
        //centrifugeResults.print();

        JavaDStream<String> fastq = stream.map(new ReadFastq()).filter(x -> x!=null);
        JavaDStream<Read> reads = fastq.map(new ToReadObject()).filter(x -> x!=null).map(new CalculateGCContent());
        JavaDStream<String> savedReads = reads.map(new ToFasta());


        JavaDStream<String> centrifugeResults = savedReads.transform(new PipeToCentrifuge());
        JavaDStream<CentrifugeResult> endResult = centrifugeResults.map(new ToCentrifugeResult()).filter(x -> x!=null).transform(new SaveCentrifugeResultsToElastic());
        JavaDStream<String> lineage = endResult.map(new ToLineageInput());
        lineage.print();

        //JavaEsSparkStreaming.saveToEs(endResult, "centrifugeresults", ImmutableMap.of("es.mapping.id","id"));
        //centrifugeResults.dstream().saveAsTextFiles("/vol/Ma_Data_new/centrifugeresults", "txt");

//        JavaDStream<String> lastResults = savedReads.transform(new PipeToLast());
//        JavaDStream<String> resultStream = lastResults.map(new GetLastResults()).filter(x -> x!=null);
//        JavaDStream<LastResult> endResults = resultStream.map(new ToLastResult());
//        JavaEsSparkStreaming.saveToEs(endResults, "lastresults");

        //ImmutableMap.of("es.mapping.id","queryName")
        //resultStream.print();
        //lastResults.print();
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

