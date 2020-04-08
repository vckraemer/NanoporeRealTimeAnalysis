package MapFunctions;

import Model.Read;
import org.apache.spark.api.java.function.Function;

import java.util.Date;

public class ToReadObject implements Function<String, Read> {

    @Override
    public Read call(String s) throws Exception {

        String[] parts = s.split("\t");

        if(parts.length == 4){
            return new Read(parts[0],parts[1],parts[2],parts[3], parts[1].length());
        }else {
            return null;
        }
    }
}
