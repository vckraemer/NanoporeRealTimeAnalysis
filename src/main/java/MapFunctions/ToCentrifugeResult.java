package MapFunctions;

import Model.CentrifugeResult;
import Model.LastResult;
import org.apache.spark.api.java.function.Function;

public class ToCentrifugeResult implements Function<String, CentrifugeResult> {

    @Override
    public CentrifugeResult call(String s) throws Exception {

        String[] fields = s.split("\t");

        if(fields.length==8){
            if(!fields[0].equals("readID")){
                float sc = Float.parseFloat(fields[3]);
                float sBS = Float.parseFloat(fields[4]);
                int hL = Integer.parseInt(fields[5]);
                int qL = Integer.parseInt(fields[6]);
                int nM = Integer.parseInt(fields[7]);
                return new CentrifugeResult(fields[0], fields[1], fields[2], sc, sBS, hL, qL, nM);
            }else
                return null;
        }else{
            return null;
        }
    }
}
