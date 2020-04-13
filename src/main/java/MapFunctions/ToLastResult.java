package MapFunctions;

import Model.LastResult;
import org.apache.spark.api.java.function.Function;

public class ToLastResult implements Function<String, LastResult> {

    @Override
    public LastResult call(String s) throws Exception {

        String[] fields = s.split("\t");

        float pI =  Float.parseFloat(fields[2]);
        int al = Integer.parseInt(fields[3]);
        int mm = Integer.parseInt(fields[4]);
        int go = Integer.parseInt(fields[5]);
        int qs = Integer.parseInt(fields[6]);
        int qe = Integer.parseInt(fields[7]);
        int rs = Integer.parseInt(fields[8]);
        int re = Integer.parseInt(fields[9]);
        float bs = Float.parseFloat(fields[11]);
        double ev = Double.parseDouble(fields[10]);

        if(fields.length==12) {
            return new LastResult(fields[0], fields[1], pI, al, mm, go, qs, qe, rs, re, ev, bs);
        }else {
            return null;
        }
    }

}
