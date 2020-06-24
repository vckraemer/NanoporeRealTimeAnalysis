package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class PipeToCentrifuge2  implements Function<JavaRDD<String>, JavaRDD<String>>{

    private String selectedDatabase;
    private String threads;

    public PipeToCentrifuge2(String database, String threads){
        selectedDatabase = database;
        this.threads = threads;
    }

    @Override
    public JavaRDD<String> call(JavaRDD<String> read) throws Exception {

        if(selectedDatabase.equals("provided")){
            String centrifugeCall = " centrifuge  -k 1 -p "+threads+" --mm -x /mnt/p+h+v/p+h+v -U -";
            JavaRDD<String> pipeRDD = read.pipe(centrifugeCall);
            pipeRDD.collect();
            return pipeRDD;
        }else{
            String centrifugeCall = "centrifuge -k 1 -p "+threads+" --mm -x "+selectedDatabase+ "-U";
            JavaRDD<String> pipeRDD = read.pipe(centrifugeCall);
            pipeRDD.collect();
            return pipeRDD;
        }
    }







}
