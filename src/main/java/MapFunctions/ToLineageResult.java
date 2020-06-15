package MapFunctions;

import Model.LineageResult;
import org.apache.spark.api.java.function.Function;

public class ToLineageResult implements Function<String, LineageResult> {

    @Override
    public LineageResult call(String s) throws Exception {

        String superkingdom = "";
        String phylum = "";
        String tclass = "";
        String order = "";
        String family = "";
        String genus = "";
        String species = "";
        String subspecies = "";

//        String[] fields = s.split("\t");
//        if(fields.length==13){
//            if(!fields[0].equals("")) {
//                return new LineageResult(fields[0], fields[1], fields[5], fields[6], fields[7], fields[8], fields[9], fields[10], fields[11], fields[12]);
//            }else {
//                return null;
//            }
//        }else {
//            return null;
//        }

        String[] fields = s.split("\\|");

        for (String field:fields) {
            if(field.contains("superkingdom")){
                superkingdom = field.split("_")[0];
            }
            else if(field.contains("family")){
                family = field.split("_")[0];
            }
            else if(field.contains("genus")){
                genus = field.split("_")[0];
            }
            else if(field.contains("phylum")){
                phylum = field.split("_")[0];
            }
            else if(field.contains("class")){
                tclass = field.split("_")[0];
            }
            else if(field.contains("species") && !field.contains("subspecies")){
                species = field.split("_")[0];
            }
            else if(field.contains("order")){
                order = field.split("_")[0];
            }
            else if(field.contains("subspecies")){
                subspecies = field.split("_")[0];
            }
        }

            if (!fields[0].equals("")) {
                return new LineageResult(fields[0], superkingdom, phylum, tclass, order, family, genus, species, subspecies);
            } else {
                return null;
            }
    }
}
