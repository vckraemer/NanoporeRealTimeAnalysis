package Model;

import java.io.Serializable;
import java.util.Date;

public class LastResult implements Serializable {

    private String id;
    private String readId;
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
    private String geneName;

    //ARGANNOT fields
    private String geneClass;
    private String accessionNumber;
    private String geneLocation;
    private String geneSize;

    //AMRFinder fields
    private String proteinGI;
    private String proteinAccession;
    private String fusionGenePartNumber;
    private String fusionGenePartTotalNumber;
    private String internalFamilyId;
    private String internalFamilyClass;
    private String resistanceMechanismType;


    public LastResult(String queryName, String referenceName, float percentIdentity, int alignmentLength, int mismatches, int gapOpens, int queryStart, int queryEnd, int referenceStart, int referenceEnd, double eValue, float bitScore, int lengthQuery, int lengthReference) {
        id = queryName;
        readId = queryName;
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

    public LastResult(String queryName, String referenceName, float percentIdentity, int alignmentLength, int mismatches, int gapOpens, int queryStart, int queryEnd, int referenceStart, int referenceEnd, double eValue, float bitScore, int lengthQuery, int lengthReference, String database) {
        id = queryName;
        readId = queryName;
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

        if (database.equals("ARGANNOT")){
            String[] refParts = referenceName.split(":");
            int pos = refParts[0].indexOf("\\)");
            geneName = refParts[0].substring(pos);
            geneClass = refParts[0].split("\\)")[0].substring(1);
            //accessionNumber = refParts[1];
            //geneLocation = refParts[2];
            //geneSize = refParts[3];
        }else if (database.equals("AMRFinder")){
            String[] refParts = referenceName.split("\\|");
            proteinGI = refParts[0];
            proteinAccession = refParts[1];
            fusionGenePartNumber = refParts[2];
            fusionGenePartTotalNumber = refParts[3];
            internalFamilyId = refParts[4];
            internalFamilyClass = refParts[5];
            resistanceMechanismType = refParts[6];
            geneName = refParts[7];
        }else{
            geneName = referenceName;
        }

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

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public String getGeneClass() {
        return geneClass;
    }

    public void setGeneClass(String geneClass) {
        this.geneClass = geneClass;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getGeneLocation() {
        return geneLocation;
    }

    public void setGeneLocation(String geneLocation) {
        this.geneLocation = geneLocation;
    }

    public String getGeneSize() {
        return geneSize;
    }

    public void setGeneSize(String geneSize) {
        this.geneSize = geneSize;
    }

    public String getProteinGI() {
        return proteinGI;
    }

    public void setProteinGI(String proteinGI) {
        this.proteinGI = proteinGI;
    }

    public String getProteinAccession() {
        return proteinAccession;
    }

    public void setProteinAccession(String proteinAccession) {
        this.proteinAccession = proteinAccession;
    }

    public String getFusionGenePartNumber() {
        return fusionGenePartNumber;
    }

    public void setFusionGenePartNumber(String fusionGenePartNumber) {
        this.fusionGenePartNumber = fusionGenePartNumber;
    }

    public String getFusionGenePartTotalNumber() {
        return fusionGenePartTotalNumber;
    }

    public void setFusionGenePartTotalNumber(String fusionGenePartTotalNumber) {
        this.fusionGenePartTotalNumber = fusionGenePartTotalNumber;
    }

    public String getInternalFamilyId() {
        return internalFamilyId;
    }

    public void setInternalFamilyId(String internalFamilyId) {
        this.internalFamilyId = internalFamilyId;
    }

    public String getInternalFamilyClass() {
        return internalFamilyClass;
    }

    public void setInternalFamilyClass(String internalFamilyClass) {
        this.internalFamilyClass = internalFamilyClass;
    }

    public String getResistanceMechanismType() {
        return resistanceMechanismType;
    }

    public void setResistanceMechanismType(String resistanceMechanismType) {
        this.resistanceMechanismType = resistanceMechanismType;
    }
}
