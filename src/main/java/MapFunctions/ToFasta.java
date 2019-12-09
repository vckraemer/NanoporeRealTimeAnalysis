package MapFunctions;

import Model.Read;
import org.apache.spark.api.java.function.Function;

public class ToFasta implements Function<Read, String> {

    @Override
    public String call(Read read) throws Exception {

        //String fasta = read.getHeader().replaceFirst("@", ">") + "\n" + read.getSeqeuence();

        return read.getFasta();
    }
}
