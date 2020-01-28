package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToMashSketch implements Function<JavaRDD<String>, JavaRDD<String>> {

    @Override
    public JavaRDD<String> call(JavaRDD<String> stringJavaRDD) throws Exception {

        String blastCall = "mash sketch -m 2";
        JavaRDD<String> pipeRDD = stringJavaRDD.pipe(blastCall);
        pipeRDD.collect();
        return pipeRDD;
    }

}
