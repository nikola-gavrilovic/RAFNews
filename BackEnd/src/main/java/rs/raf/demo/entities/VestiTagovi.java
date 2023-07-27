package rs.raf.demo.entities;

public class VestiTagovi {

    private int id;
    private Vest vest;
    private Tag tag;

    public VestiTagovi() {

    }

    public VestiTagovi(int id, Vest vest, Tag tag) {
        this.id = id;
        this.vest = vest;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vest getVest() {
        return vest;
    }

    public void setVest(Vest vest) {
        this.vest = vest;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
