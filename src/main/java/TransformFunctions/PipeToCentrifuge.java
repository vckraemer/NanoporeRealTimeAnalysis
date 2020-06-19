package TransformFunctions;

import Model.IntegerInkrement;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class PipeToCentrifuge implements Function<JavaRDD<String>, JavaRDD<String>> {

    private String selectedDatabase;
    private int counter;

    public PipeToCentrifuge(String database, IntegerInkrement i){
        selectedDatabase = database;
        counter = i.ai;
        i.ai = i.ai+1;
    }

    @Override
    public JavaRDD<String> call(JavaRDD<String> read) throws Exception {

        String debug = read.toDebugString();
        String[] debugParts = debug.split("\\|");
        String path = debugParts[debugParts.length-1].split(" ")[2].replace("file:", "");
        String[] pathParts = path.split("/");
        String filename = pathParts[pathParts.length-1] + Thread.currentThread().getId();;

        System.out.println(Thread.currentThread().getId());

        //-q fastq input -f fasta input
        String centrifugeCall = "bash /home/ubuntu/centrifugeWrapper.sh " + filename + " " + selectedDatabase;
        JavaRDD<String> pipeRDD = read.pipe(centrifugeCall);
        pipeRDD.collect();
        return pipeRDD;
    }

}
