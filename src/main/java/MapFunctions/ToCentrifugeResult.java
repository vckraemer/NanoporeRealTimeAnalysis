package MapFunctions;

import Model.CentrifugeResult;
import Model.LastResult;
import org.apache.spark.api.java.function.Function;

import java.util.ArrayList;

public class ToCentrifugeResult implements Function<String, CentrifugeResult> {

    @Override
    public CentrifugeResult call(String s) throws Exception {

        String[] fields = s.split("\t");

        if(fields.length==8){
            if(!fields[0].equals("readID")){
                String id = fields[0]+"_"+fields[1]+"_"+fields[2];
                float sc = Float.parseFloat(fields[3]);
                float sBS = Float.parseFloat(fields[4]);
                int hL = Integer.parseInt(fields[5]);
                int qL = Integer.parseInt(fields[6]);
                int nM = Integer.parseInt(fields[7]);
                int ql = Integer.parseInt(fields[8]);
                int rl = Integer.parseInt(fields[9]);
                float rs = Float.parseFloat(fields[10]);
                return new CentrifugeResult(id, fields[0], fields[1], fields[2], sc, sBS, hL, qL, nM, ql, rl, rs);

            }else
                return null;
        }else{
            return null;
        }
    }
}
