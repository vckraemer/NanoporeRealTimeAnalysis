package MapFunctions;

import Model.Read;
import org.apache.spark.api.java.function.Function;


public class GetBlastResultsStandard implements Function<String, Read> {

    String completeBlastResult = "";
    String readId = "";

    @Override
    public Read call(String s) throws Exception {

        if(s.contains("Query=")){
            readId = s;
            completeBlastResult = completeBlastResult + s + '\n';
            return null;
        }else if(s.contains("Gap Penalties:") && s.contains("Existence") && s.contains("Extension")){
            Read read = new Read();
            read.setId(readId);
            read.setBlastResult(completeBlastResult);
            completeBlastResult = "";
            readId = "";
            return read;
        }else{
          completeBlastResult = completeBlastResult + s + '\n';
          return null;
        }
    }
}
