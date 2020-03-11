package MapFunctions;

import Model.CentrifugeResult;
import org.apache.spark.api.java.function.Function;

public class ToLineageInput implements Function<CentrifugeResult, String> {
    @Override
    public String call(CentrifugeResult cr) throws Exception {
        return cr.getId()+ '\t' +cr.getTaxID()+'\n';
    }
}
