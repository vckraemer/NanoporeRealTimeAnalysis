package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToCentrifuge implements Function<JavaRDD<String>, JavaRDD<String>> {

    @Override
    public JavaRDD<String> call(JavaRDD<String> read) throws Exception {

        String debug = read.toDebugString();
        String[] debugParts = debug.split("\\|");
        String path = debugParts[debugParts.length-1].split(" ")[2].replace("file:", "");
        String[] pathParts = path.split("/");
        String filename = pathParts[pathParts.length-1];

        //-q fastq input -f fasta input
        String centrifugeCall = "bash /vol/Ma_Data_new/centrifugeWrapper.sh " + filename;
        //String centrifugeCall = "bash /home/vanessa/Masterarbeit/centrifugeWrapper.sh " + filename;
        JavaRDD<String> pipeRDD = read.pipe(centrifugeCall);
        pipeRDD.collect();
        return pipeRDD;
    }

}
