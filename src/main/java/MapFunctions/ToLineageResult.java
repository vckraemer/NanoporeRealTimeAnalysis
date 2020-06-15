package MapFunctions;

import Model.LineageResult;
import org.apache.spark.api.java.function.Function;

public class ToLineageResult implements Function<String, LineageResult> {

    @Override
    public LineageResult call(String s) throws Exception {

//        String[] fields = s.split("\t");
//        if(fields.length==13){
//            if(!fields[0].equals("")) {
//                return new LineageResult(fields[0], fields[1], fields[5], fields[6], fields[7], fields[8], fields[9], fields[10], fields[11], fields[12]);
//            }else {
//                return null;
//            }
//        }else {
//            return null;
//        }

        String[] fields = s.split("\\|");
        if(fields.length>13) {
            if (!fields[0].equals("")) {
                return new LineageResult(fields[0], fields[2], fields[4], fields[8], fields[18], fields[23], fields[28], fields[30], fields[31], fields[31]);
            } else {
                return null;
            }
        }
         else if(fields.length==11){
                if(!fields[0].equals("")) {
                    return new LineageResult(fields[0], fields[0].split("_")[fields[0].split("_").length-1], fields[2], fields[6], fields[7], fields[8], fields[3], fields[4], fields[5], fields[10]);
                }else {
                    return null;
                }
            }
        else {
            return null;
        }

    }
}
