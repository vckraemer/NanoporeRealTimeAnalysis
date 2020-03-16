package Model;

import java.io.Serializable;

public class LineageResults implements Serializable {

    private String id;
    private String taxnum;
    private String domain;
    private String kingdom;
    private String phylum;
    private String tclass;
    private String order;
    private String family;
    private String genus;
    private String species;

    public LineageResults(String id, String taxId, String domain, String kingdom, String phylum, String tclass, String order, String family, String genus, String species) {
        this.id = id;
        this.taxnum = taxId;
        this.domain = domain;
        this.kingdom = kingdom;
        this.phylum = phylum;
        this.tclass = tclass;
        this.order = order;
        this.family = family;
        this.genus = genus;
        this.species = species;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
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
}
