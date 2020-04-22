package MapFunctions;

import InputFormat.QRecord;
import Model.Read;
import org.apache.spark.api.java.function.Function;

public class FromQRecordToReadObject implements Function<QRecord, Read> {

        @Override
        public Read call(QRecord record) throws Exception {
            return new Read(record.getKey(),record.getValue(),record.getKey2(),record.getQuality(), record.getValue().length());
        }
}
