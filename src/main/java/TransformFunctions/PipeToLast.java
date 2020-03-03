package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToLast implements Function<JavaRDD<String>, JavaRDD<String>> {

    @Override
    public JavaRDD<String> call(JavaRDD<String> read) throws Exception {

        //fasta input
        String lastCall = "lastal -F15 /vol/Ma_Data_new/argdb/argdb -f BlastTab";
        JavaRDD<String> pipeRDD = read.pipe(lastCall);
        pipeRDD.collect();
        return pipeRDD;
    }

}
