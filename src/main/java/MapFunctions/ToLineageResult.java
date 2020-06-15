package MapFunctions;

import Model.LineageResult;
import org.apache.spark.api.java.function.Function;

public class ToLineageResult implements Function<String, LineageResult> {

    @Override
    public LineageResult call(String s) throws Exception {

        String[] fields = s.split("\t");
        if(fields.length==13){
            if(!fields[0].equals("")) {
                return new LineageResult(fields[0], fields[1], fields[5], fields[6], fields[7], fields[8], fields[9], fields[10], fields[11], fields[12]);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }
}
