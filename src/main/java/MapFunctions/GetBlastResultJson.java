package MapFunctions;

import Model.Read;
import org.apache.spark.api.java.function.Function;

public class GetBlastResultJson implements Function<String, String> {

    String completeBlastResult = "";
    String readId = "";
    int counter = 0;

    @Override
    public String call(String s) throws Exception {

        if(counter == 0 && completeBlastResult!=""){
            Read read = new Read();
            read.setId(readId);
            read.setBlastResult(completeBlastResult + s + '\n');
            completeBlastResult = "";
            readId = "";
            return read.getBlastResult();
        }
        else if(s.contains("{")){
            completeBlastResult = completeBlastResult + s + '\n';
            counter ++;
            return null;
        }else if(s.contains("}")){
            completeBlastResult = completeBlastResult + s + '\n';
            counter --;
            return null;
        }else{
            completeBlastResult = completeBlastResult + s + '\n';
            if(s.contains("query_title")){
                readId = s.split(":")[1].replaceAll("\"","").replace(",","");
            }
            return null;
        }

    }
}
