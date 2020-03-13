package MapFunctions;

import Model.CentrifugeResult;
import Model.LineageResults;
import org.apache.spark.api.java.function.Function;

public class ToLineageResult implements Function<String, LineageResults> {
    @Override
    public LineageResults call(String s) throws Exception {
        return null;
    }
}
