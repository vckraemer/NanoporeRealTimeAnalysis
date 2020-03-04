package Model;

import java.io.Serializable;

public class LastResult implements Serializable {

    private String queryName;
    private String referenceName;
    private double percentIdentity;
    private int alignmentLength;
    private int mismatches;
    private int gapOpens;
    private int queryStart;
    private int queryEnd;
    private int referenceStart;
    private int referenceEnd;
    private String eValue;
    private double bitScore;

    public LastResult(String queryName, String referenceName, double percentIdentity, int alignmentLength, int mismatches, int gapOpens, int queryStart, int queryEnd, int referenceStart, int referenceEnd, String eValue, double bitScore) {
        this.queryName = queryName;
        this.referenceName = referenceName;
        this.percentIdentity = percentIdentity;
        this.alignmentLength = alignmentLength;
        this.mismatches = mismatches;
        this.gapOpens = gapOpens;
        this.queryStart = queryStart;
        this.queryEnd = queryEnd;
        this.referenceStart = referenceStart;
        this.referenceEnd = referenceEnd;
        this.eValue = eValue;
        this.bitScore = bitScore;
    }

    public LastResult(){

    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public double getPercentIdentity() {
        return percentIdentity;
    }

    public void setPercentIdentity(double percentIdentity) {
        this.percentIdentity = percentIdentity;
    }

    public int getAlignmentLength() {
        return alignmentLength;
    }

    public void setAlignmentLength(int alignmentLength) {
        this.alignmentLength = alignmentLength;
    }

    public int getMismatches() {
        return mismatches;
    }

    public void setMismatches(int mismatches) {
        this.mismatches = mismatches;
    }

    public int getGapOpens() {
        return gapOpens;
    }

    public void setGapOpens(int gapOpens) {
        this.gapOpens = gapOpens;
    }

    public int getQueryStart() {
        return queryStart;
    }

    public void setQueryStart(int queryStart) {
        this.queryStart = queryStart;
    }

    public int getQueryEnd() {
        return queryEnd;
    }

    public void setQueryEnd(int queryEnd) {
        this.queryEnd = queryEnd;
    }

    public int getReferenceStart() {
        return referenceStart;
    }

    public void setReferenceStart(int referenceStart) {
        this.referenceStart = referenceStart;
    }

    public int getReferenceEnd() {
        return referenceEnd;
    }

    public void setReferenceEnd(int referenceEnd) {
        this.referenceEnd = referenceEnd;
    }

    public String geteValue() {
        return eValue;
    }

    public void seteValue(String eValue) {
        this.eValue = eValue;
    }

    public double getBitScore() {
        return bitScore;
    }

    public void setBitScore(double bitScore) {
        this.bitScore = bitScore;
    }
}
