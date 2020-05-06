package MapFunctions;

import org.apache.hadoop.io.Text;
import org.apache.spark.api.java.function.Function;

public class TextToString implements Function<Text, String> {
    @Override
    public String call(Text text) throws Exception {
        return text.toString();
    }
}
