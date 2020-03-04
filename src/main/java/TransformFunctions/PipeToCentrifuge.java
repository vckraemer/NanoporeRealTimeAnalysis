package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToCentrifuge implements Function<JavaRDD<String>, JavaRDD<String>> {

    @Override
    public JavaRDD<String> call(JavaRDD<String> read) throws Exception {

        //-q fastq input -f fasta input
        String centrifugeCall = "centrifuge -f -x  /vol/Ma_Data_new/phv/p+h+v";
        JavaRDD<String> pipeRDD = read.pipe(centrifugeCall);
        pipeRDD.collect();
        return pipeRDD;
    }

}
