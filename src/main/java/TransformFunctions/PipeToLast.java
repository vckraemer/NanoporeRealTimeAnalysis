package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToLast implements Function<JavaRDD<String>, JavaRDD<String>> {

    private String selectedDatabase;

    public PipeToLast(String database){
        selectedDatabase = database;
    }


    @Override
    public JavaRDD<String> call(JavaRDD<String> read) throws Exception {

        String lastCall = "";

        if(selectedDatabase.equals("ARGANNOT")){
            lastCall = "lastal -P 14 -F15 -f BlastTab+ /home/ubuntu/arg_annot_db/arg_annot_db ";
        } else if (selectedDatabase.equals("ResFinder")){
            lastCall = "lastal -P 14 -f BlastTab+ /home/ubuntu/resfinderdb/resfinder ";
        } else if (selectedDatabase.equals("AMRFinder")){
            lastCall = "lastal -P 14 -F15 -f BlastTab+ /home/ubuntu/AMRdb/AMRdb ";
        }

        JavaRDD<String> pipeRDD = read.pipe(lastCall);
        pipeRDD.collect();

        return pipeRDD;
    }

}
