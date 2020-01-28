package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToBlastX implements Function<JavaRDD<String>, JavaRDD<String>> {
    @Override
    public JavaRDD<String> call(JavaRDD<String> stringJavaRDD) throws Exception {

        String blastxCall = "blastx -db /vol/MA_Data/argannot_db/arg_annot_protein -outfmt 15 -num_threads 5";
        JavaRDD<String> pipeRDD = stringJavaRDD.pipe(blastxCall);
        pipeRDD.collect();
        return pipeRDD;
    }
}
