package Model;

import java.io.Serializable;

public class CentrifugeResult implements Serializable {

    private String readId;
    private String seqId;
    private String taxID;
    private float score;
    private float secondBestScore;
    private int hitLength;
    private int queryLength;
    private int numMatches;
    private String domain;
    private String kingdom;
    private String phylum;
    private String tclass;
    private String order;
    private String family;
    private String genus;
    private String species;

    public CentrifugeResult(String readId, String seqId, String taxID, float score, float secondBestScore, int hitLength, int queryLength, int numMatches) {
        this.readId = readId;
        this.seqId = seqId;
        this.taxID = taxID;
        this.score = score;
        this.secondBestScore = secondBestScore;
        this.hitLength = hitLength;
        this.queryLength = queryLength;
        this.numMatches = numMatches;
    }

    public String getReadId() {
        return readId;
    }

    public void setReadId(String readId) {
        this.readId = readId;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getSecondBestScore() {
        return secondBestScore;
    }

    public void setSecondBestScore(float secondBestScore) {
        this.secondBestScore = secondBestScore;
    }

    public int getHitLength() {
        return hitLength;
    }

    public void setHitLength(int hitLength) {
        this.hitLength = hitLength;
    }

    public int getQueryLength() {
        return queryLength;
    }

    public void setQueryLength(int queryLength) {
        this.queryLength = queryLength;
    }

    public int getNumMatches() {
        return numMatches;
    }

    public void setNumMatches(int numMatches) {
        this.numMatches = numMatches;
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
