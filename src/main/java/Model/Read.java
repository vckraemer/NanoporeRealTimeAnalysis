package Model;

public class Read {

    private String id;
    private String Seqeuence;
    private String Description;
    private String Quality;
    private float GCContent;
    private int Readlength;
    private String Fasta;
    private String BlastResult;

    public Read(){

    }

    public Read(String id, String sequence, String description, String quality, int readlength){
        this.id = id.substring(1);
        Seqeuence = sequence;
        Description = description;
        Quality = quality;
        Readlength = readlength;
        Fasta = id.replaceFirst("@",">")+"\n" + sequence;
        BlastResult = "";
    }

    public String getBlastResult() { return BlastResult; }

    public void setBlastResult(String blastResult) { BlastResult = blastResult; }

    public String getFasta() {
        return Fasta;
    }

    public void setFasta(String fasta) {
        Fasta = fasta;
    }

    public int getReadlength() {
        return Readlength;
    }

    public void setReadlength(int readlength) {
        Readlength = readlength;
    }

    public float getGCContent() {
        return GCContent;
    }

    public void setGCContent(float GCContent) {
        this.GCContent = GCContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeqeuence() {
        return Seqeuence;
    }

    public void setSeqeuence(String seqeuence) {
        Seqeuence = seqeuence;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String quality) {
        Quality = quality;
    }
}
