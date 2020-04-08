package MapFunctions;

import Model.CentrifugeResult;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.spark_project.guava.collect.ImmutableMap;

public class SaveCentrifugeResultsToElastic implements Function<JavaRDD<CentrifugeResult>, JavaRDD<CentrifugeResult>> {

    private String esIndexPrefix;

    public SaveCentrifugeResultsToElastic(String esIndexPrefix){
        this.esIndexPrefix = esIndexPrefix;
    }

    @Override
    public JavaRDD<CentrifugeResult> call(JavaRDD<CentrifugeResult> centrifugeResultJavaRDD) throws Exception {
        JavaEsSpark.saveToEs(centrifugeResultJavaRDD, esIndexPrefix+"centrifugeresults", ImmutableMap.of("es.mapping.id","id"));
        return centrifugeResultJavaRDD;
    }
}
