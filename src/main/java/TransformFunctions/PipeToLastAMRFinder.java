package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToLastAMRFinder implements Function<JavaRDD<String>, JavaRDD<String>> {

    @Override
    public JavaRDD<String> call(JavaRDD<String> read) throws Exception {

        String lastCall = "lastal -f BlastTab+ /home/ubuntu/AMRdb/AMRdb ";
        JavaRDD<String> pipeRDD = read.pipe(lastCall);
        pipeRDD.collect();
        return pipeRDD;
    }

}
