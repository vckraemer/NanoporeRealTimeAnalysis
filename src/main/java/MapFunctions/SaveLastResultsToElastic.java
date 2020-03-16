package MapFunctions;

import Model.LastResult;
import Model.Read;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.spark_project.guava.collect.ImmutableMap;

public class SaveLastResultsToElastic implements Function<JavaRDD<LastResult>, JavaRDD<LastResult>> {


    @Override
    public JavaRDD<LastResult> call(JavaRDD<LastResult> resultRDD) throws Exception {

        //JavaEsSpark.saveToEs(readRDD, "sparkstreaming", ImmutableMap.of("es.mapping.id","id","es.mapping.exclude","quality, sequence"));
        JavaEsSpark.saveToEs(resultRDD, "lastresults");
        return resultRDD;
    }
}
