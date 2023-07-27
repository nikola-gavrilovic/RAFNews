package rs.raf.demo.entities;

public enum Tip {

    CONTENT_CREATOR("Kontent kreator"), ADMIN("Admin");
    private String tip;
    private Tip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString(){
        return tip;
    }
}
