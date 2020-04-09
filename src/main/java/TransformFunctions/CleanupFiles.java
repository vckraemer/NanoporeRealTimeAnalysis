package TransformFunctions;

import Model.LineageResults;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CleanupFiles implements Function<JavaRDD<LineageResults>, JavaRDD<LineageResults>>  {

    @Override
    public JavaRDD<LineageResults> call(JavaRDD<LineageResults> lineageResults) throws Exception {

        String debug = lineageResults.toDebugString();
        String[] debugParts = debug.split("\\|");
        String path = debugParts[debugParts.length-1].split(" ")[2].replace("file:", "");
        String[] pathParts = path.split("/");
        String filename = pathParts[pathParts.length-1];

        String cleanupCall = "bash /vol/spool/cleanupWrapper.sh "+filename;

        JavaRDD<String> pipeRDD = lineageResults.pipe(cleanupCall);
        pipeRDD.collect();

        return lineageResults;
    }
}
