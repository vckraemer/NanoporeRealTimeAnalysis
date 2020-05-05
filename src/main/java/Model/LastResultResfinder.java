package Model;

import java.io.Serializable;
import java.util.Date;

public class LastResultResfinder implements Serializable {

    private String queryName;
    private String referenceName;
    private float percentIdentity;
    private int alignmentLength;
    private int mismatches;
    private int gapOpens;
    private int queryStart;
    private int queryEnd;
    private int referenceStart;
    private int referenceEnd;
    private double eValue;
    private float bitScore;
    private Date lastSaveTime;
    private int lengthQuery;
    private int lengthReference;
    private float rawScore;
    private double identityCompleteGeneLength;

    public LastResultResfinder(String queryName, String referenceName, float percentIdentity, int alignmentLength, int mismatches, int gapOpens, int queryStart, int queryEnd, int referenceStart, int referenceEnd, double eValue, float bitScore, int lengthQuery, int lengthReference) {
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
        lastSaveTime = new Date();
        this.lengthQuery = lengthQuery;
        this.lengthReference = lengthReference;
        identityCompleteGeneLength = (((referenceEnd-referenceStart+1.0)/lengthReference)*(percentIdentity/100.0))*100.0;
    }

    public LastResultResfinder(){

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

    public float getPercentIdentity() {
        return percentIdentity;
    }

    public void setPercentIdentity(float percentIdentity) {
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

    public double geteValue() {
        return eValue;
    }

    public void seteValue(double eValue) {
        this.eValue = eValue;
    }

    public float getBitScore() {
        return bitScore;
    }

    public void setBitScore(float bitScore) {
        this.bitScore = bitScore;
    }

    public Date getLastSaveTime() {
        return lastSaveTime;
    }

    public void setLastSaveTime(Date lastSaveTime) {
        this.lastSaveTime = lastSaveTime;
    }

    public int getLengthQuery() {
        return lengthQuery;
    }

    public void setLengthQuery(int lengthQuery) {
        this.lengthQuery = lengthQuery;
    }

    public int getLengthReference() {
        return lengthReference;
    }

    public void setLengthReference(int lengthReference) {
        this.lengthReference = lengthReference;
    }

    public float getRawScore() {
        return rawScore;
    }

    public void setRawScore(float rawScore) {
        this.rawScore = rawScore;
    }

    public double getIdentityCompleteGeneLength() {
        return identityCompleteGeneLength;
    }

    public void setIdentityCompleteGeneLength(double identityCompleteGeneLength) {
        this.identityCompleteGeneLength = identityCompleteGeneLength;
    }
}
