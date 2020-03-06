package MapFunctions;

import Model.CentrifugeResult;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.spark_project.guava.collect.ImmutableMap;

public class SaveCentrifugeResultsToElastic implements Function<JavaRDD<CentrifugeResult>, JavaRDD<CentrifugeResult>> {

    @Override
    public JavaRDD<CentrifugeResult> call(JavaRDD<CentrifugeResult> centrifugeResultJavaRDD) throws Exception {
        JavaEsSpark.saveToEs(centrifugeResultJavaRDD, "centrifugeresults", ImmutableMap.of("es.mapping.id","id"));
        return centrifugeResultJavaRDD;
    }
}
