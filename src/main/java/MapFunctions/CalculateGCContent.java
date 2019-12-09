package MapFunctions;

import Model.Read;
import org.apache.spark.api.java.function.Function;

public class CalculateGCContent implements Function<Read, Read>{

    @Override
    public Read call(Read read) throws Exception {
        float numberGC = 0;
        float totalNumberBases = read.getSeqeuence().length();

        for (char c:read.getSeqeuence().toCharArray()) {
            if(c=='G' || c=='C'){
                numberGC++;
            }
        }
        float gcContent = (numberGC/totalNumberBases)*100;
        read.setGCContent(gcContent);
        return read;
    }
}
