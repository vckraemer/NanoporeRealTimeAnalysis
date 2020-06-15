package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToPythonLineage implements Function<JavaRDD<String>, JavaRDD<String>> {
    @Override
    public JavaRDD<String> call(JavaRDD<String> taxId) throws Exception {

//        String debug = taxId.toDebugString();
//        String[] debugParts = debug.split("\\|");
//        String path = debugParts[debugParts.length-1].split(" ")[2].replace("file:", "");
//        String[] pathParts = path.split("/");
//        String filename = pathParts[pathParts.length-1];

        String lineageCall = "python3 /home/ubuntu/etelineage.py ";
        JavaRDD<String> pipeRDD = taxId.pipe(lineageCall);
        pipeRDD.collect();
        return pipeRDD;
    }
}
