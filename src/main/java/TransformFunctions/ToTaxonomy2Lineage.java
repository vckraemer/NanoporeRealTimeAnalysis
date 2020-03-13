package TransformFunctions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class ToTaxonomy2Lineage implements Function<JavaRDD<String>, JavaRDD<String>> {
    @Override
    public JavaRDD<String> call(JavaRDD<String> taxId) throws Exception {

        String debug = taxId.toDebugString();
        String[] debugParts = debug.split("\\|");
        String path = debugParts[debugParts.length-1].split(" ")[2].replace("file:", "");
        String[] pathParts = path.split("/");
        String filename = pathParts[pathParts.length-1];

        //String lineage2TaxonomyCall = "bash ./vol/Ma_Data_new/megan_taxon2lineage.pl -db /vol/Ma_Data_new/MetaMaps-master/databases/miniSeq+H/taxonomy/ ";
        String lineage2TaxonomyCall = "bash /vol/Ma_Data_new/lineagetestwrapper.sh " + filename;

        JavaRDD<String> pipeRDD = taxId.pipe(lineage2TaxonomyCall);
        pipeRDD.collect();
        return pipeRDD;
    }
}
