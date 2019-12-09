package MapFunctions;

import Model.Read;
import org.apache.spark.api.java.function.Function;

public class GetBlastResultJsonSingleReport implements Function<String, String> {

    String completeBlastResult = "";
    String readId = "";
    int counter = 0;

    @Override
    public String call(String s) throws Exception {

        if((counter == 0) && !(completeBlastResult.equals(""))){
            String finishedResult = completeBlastResult + s.replace(",", "");
            completeBlastResult = "";

            return finishedResult;
        }
        else if(s.contains("report")){
            completeBlastResult = completeBlastResult + '{' + s;
            counter ++;
            return null;
        }
        else if(s.contains("{") && !completeBlastResult.equals("")){
            completeBlastResult = completeBlastResult + s;
            counter ++;
            return null;
        }
        else if(s.contains("}")){
            completeBlastResult = completeBlastResult + s;
            counter --;
            return null;
        }
        else if((counter == 0) && (completeBlastResult.equals(""))){
            return null;
        }
        else{
            completeBlastResult = completeBlastResult + s;
            return null;
        }

    }

}
