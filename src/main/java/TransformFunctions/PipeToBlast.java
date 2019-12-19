package TransformFunctions;

import Model.Read;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.codehaus.janino.Java;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

import java.util.ArrayList;
import java.util.List;

public class PipeToBlast implements Function<JavaRDD<String>, JavaRDD<String>> {

    @Override
    public JavaRDD<String> call(JavaRDD<String> read) throws Exception {

        //String blastCall = "blastn -db /home/vanessa/Masterarbeit/workdir/amrtest -outfmt 15";
        String blastCall = "blastn -db /vol/MA_Data/nt_db/nt_v5 -outfmt 15";
        JavaRDD<String> pipeRDD = read.pipe(blastCall);
        pipeRDD.collect();
        return pipeRDD;
    }
}
