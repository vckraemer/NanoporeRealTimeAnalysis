package MapFunctions;

import org.apache.spark.api.java.function.Function;

public class ReadFastq implements Function<String, String> {

    public String fastqblock = "";
    public int lineMark = 0;

    @Override
    public String call(String line) throws Exception {

        if(line.contains("@")){
            fastqblock = line;
            lineMark = 1;
            return null;
        }
//        else if(line.charAt(0) == '+'){
//            fastqblock = fastqblock + "\t" + line;
//            lineMark++;
//            return null;
//        }
        else if(lineMark == 3){
            String completeBlock = fastqblock + "\t" + line;
            lineMark = 0;
            fastqblock = "";
            return completeBlock;
        }else{
            fastqblock = fastqblock + "\t" + line;
            lineMark++;
            return null;
        }
    }
}
