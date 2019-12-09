package TransformFunctions;

import Model.Read;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.spark_project.guava.collect.ImmutableMap;


public class SaveToElastic implements Function<JavaRDD<Read>, JavaRDD<Read>> {


    @Override
    public JavaRDD<Read> call(JavaRDD<Read> readRDD) throws Exception {

        JavaEsSpark.saveToEs(readRDD, "sparkstreaming", ImmutableMap.of("es.mapping.id","id"));

        return readRDD;
    }
}
