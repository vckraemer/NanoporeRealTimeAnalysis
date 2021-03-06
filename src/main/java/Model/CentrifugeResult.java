package Model;

import java.io.Serializable;
import java.util.Date;

public class CentrifugeResult implements Serializable {

    private String id;
    private String readId;
    private String seqId;
    private String taxId;
    private float score;
    private float secondBestScore;
    private int hitLength;
    private int queryLength;
    private int numMatches;
    private Date centrifugeSaveTime;


    public CentrifugeResult(String Id, String readId, String seqId, String taxID, float score, float secondBestScore, int hitLength, int queryLength, int numMatches) {
        this.id = Id;
        this.readId = readId;
        this.seqId = seqId;
        this.taxId = taxID;
        this.score = score;
        this.secondBestScore = secondBestScore;
        this.hitLength = hitLength;
        this.queryLength = queryLength;
        this.numMatches = numMatches;
        centrifugeSaveTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
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

    public Date getCentrifugeSaveTime() {
        return centrifugeSaveTime;
    }

    public void setCentrifugeSaveTime(Date centrifugeSaveTime) {
        this.centrifugeSaveTime = centrifugeSaveTime;
    }

}
