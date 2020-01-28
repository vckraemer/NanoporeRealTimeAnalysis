package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToMashDist implements Function<JavaRDD<String>, JavaRDD<String>> {


    @Override
    public JavaRDD<String> call(JavaRDD<String> stringJavaRDD) throws Exception {

        String blastCall = "mash dist -p 4 /vol/Ma_Data/refseq.genomes.k21s1000.msh";
        JavaRDD<String> pipeRDD = stringJavaRDD.pipe(blastCall);
        pipeRDD.collect();
        return pipeRDD;
    }
}
