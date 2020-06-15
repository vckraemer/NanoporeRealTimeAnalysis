package Model;

import java.io.Serializable;
import java.util.Date;

public class LineageResult implements Serializable {

    private String id;
    private String readId;
    private String taxnum;
    private String superkingdom;
    private String phylum;
    private String tclass;
    private String order;
    private String family;
    private String genus;
    private String species;
    private String subspecies;
    private Date lineageSaveTime;

    public LineageResult(String id, String taxId, String superkingdom, String phylum, String tclass, String order, String family, String genus, String species, String subspecies) {
        this.id = id;
        readId = id.split("_")[0];
        this.taxnum = taxId;
        this.superkingdom = superkingdom;
        this.phylum = phylum;
        this.tclass = tclass;
        this.order = order;
        this.family = family;
        this.genus = genus;
        this.species = species;
        this.subspecies = subspecies;
        lineageSaveTime = new Date();
    }

    public LineageResult(String id, String superkingdom, String phylum, String tclass, String order, String family, String genus, String species, String subspecies) {
        this.id = id;
        readId = id.split("_")[0];
        this.taxnum = id.split("_")[id.split("_").length-1];
        this.superkingdom = superkingdom;
        this.phylum = phylum;
        this.tclass = tclass;
        this.order = order;
        this.family = family;
        this.genus = genus;
        this.species = species;
        this.subspecies = subspecies;
        lineageSaveTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaxnum() {
        return taxnum;
    }

    public void setTaxnum(String taxnum) {
        this.taxnum = taxnum;
    }

    public String getPhylum() {
        return phylum;
    }

    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    public String getTclass() {
        return tclass;
    }

    public void setTclass(String tclass) {
        this.tclass = tclass;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSuperkingdom() {
        return superkingdom;
    }

    public void setSuperkingdom(String superkingdom) {
        this.superkingdom = superkingdom;
    }

    public String getSubspecies() {
        return subspecies;
    }

    public void setSubspecies(String subspecies) {
        this.subspecies = subspecies;
    }

    public Date getLineageSaveTime() {
        return lineageSaveTime;
    }

    public void setLineageSaveTime(Date lineageSaveTime) {
        this.lineageSaveTime = lineageSaveTime;
    }

    public String getReadId() {
        return readId;
    }

    public void setReadId(String readId) {
        this.readId = readId;
    }
}
