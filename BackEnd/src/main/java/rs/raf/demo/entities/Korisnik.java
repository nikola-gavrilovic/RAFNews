package rs.raf.demo.entities;

import org.apache.commons.codec.digest.DigestUtils;

public class Korisnik {

    private int id;
    private String email;
    private String ime;
    private String prezime;
    private String tip;
    private String hashedPassword;
    private boolean aktivan;

    public Korisnik(){

    }

    public Korisnik(int id, String email, String ime, String prezime, String tip, boolean aktivan) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.tip = tip;
        this.aktivan = aktivan;
    }

    public Korisnik(int id, String email, String ime, String prezime, String tip, String hashedPassword, boolean aktivan) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.tip = tip;
        this.hashedPassword = hashedPassword;
        this.aktivan = aktivan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        if(hashedPassword == null || hashedPassword.isBlank()){
            this.hashedPassword = null;
        }else{
            this.hashedPassword = DigestUtils.sha256Hex(hashedPassword);
        }
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }
}
