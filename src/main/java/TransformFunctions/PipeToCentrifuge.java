package TransformFunctions;

import Model.IntegerInkrement;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class PipeToCentrifuge implements Function<JavaRDD<String>, JavaRDD<String>> {

    private String selectedDatabase;
    private String threads;

    public PipeToCentrifuge(String database, String threads){
        selectedDatabase = database;
        this.threads = threads;
    }

    @Override
    public JavaRDD<String> call(JavaRDD<String> read) throws Exception {

        String debug = read.toDebugString();
        String[] debugParts = debug.split("\\|");
        String path = debugParts[debugParts.length-1].split(" ")[2].replace("file:", "");
        String[] pathParts = path.split("/");
        String filename = pathParts[pathParts.length-1];

        //-q fastq input -f fasta input
        String centrifugeCall = "bash /home/ubuntu/centrifugeWrapper.sh " + filename + " " + selectedDatabase + " " + threads;
        JavaRDD<String> pipeRDD = read.pipe(centrifugeCall);
        pipeRDD.collect();
        return pipeRDD;
    }

}
