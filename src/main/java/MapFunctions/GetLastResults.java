package MapFunctions;

import Model.LastResult;
import Model.Read;
import org.apache.spark.api.java.function.Function;
import scala.Int;

public class GetLastResults implements Function<String, LastResult> {

    String lastResult = "";
    String readId = "";
    int counter = 0;

    @Override
    public LastResult call(String s) throws Exception {

        if(s.charAt(0)!='#'){
            LastResult result = new LastResult();
            String[] fields = s.split("\t");

            result.setQueryName(fields[0]);
            result.setReferenceName(fields[1]);
            result.setPercentIdentity(Double.parseDouble(fields[2]));
            result.setAlignmentLength(Integer.parseInt(fields[3]));
            result.setMismatches(Integer.parseInt(fields[4]));
            result.setGapOpens(Integer.parseInt(fields[5]));
            result.setQueryStart(Integer.parseInt(fields[6]));
            result.setQueryEnd(Integer.parseInt(fields[7]));
            result.setReferenceStart(Integer.parseInt(fields[8]));
            result.setReferenceEnd(Integer.parseInt(fields[9]));
            result.seteValue(fields[10]);
            result.setBitScore(Double.parseDouble(fields[11]));

            return result;
        }
        else {
            return null;
        }

    }
}
