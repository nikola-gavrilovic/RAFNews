package rs.raf.demo.entities;

import java.time.LocalDate;
import java.util.List;

public class Vest {

    private int id;
    private String naslov;
    private String tekst;
    private LocalDate vremeKreiranja;
    private int brojPoseta;
    private String autor;
    private List<Komentar> komentari;
    private Kategorija kategorija;
    private List<Tag> tagovi;

    public Vest(int vestId, String naslov, String tekst, LocalDate vremeKreiranja, int brojPoseta, Object o, Object o1, Object o2, List<Tag> tagovi, String autor){

    }

    public Vest(int id, String naslov, String tekst, int brojPoseta, LocalDate vremeKreiranja, Kategorija kategorija, String autor) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.brojPoseta = brojPoseta;
        this.vremeKreiranja = vremeKreiranja;
        this.kategorija = kategorija;
        this.autor = autor;
    }

    public Vest(int id, String naslov, String tekst) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
    }

    public Vest(int id, String naslov, String tekst, LocalDate vremeKreiranja, int brojPoseta, String autor, List<Komentar> komentari, Kategorija kategorija, List<Tag> tagovi) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.vremeKreiranja = vremeKreiranja;
        this.brojPoseta = brojPoseta;
        this.autor = autor;
        this.komentari = komentari;
        this.kategorija = kategorija;
        this.tagovi = tagovi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDate getVremeKreiranja() {
        return vremeKreiranja;
    }

    public void setVremeKreiranja(LocalDate vremeKreiranja) {
        this.vremeKreiranja = vremeKreiranja;
    }

    public int getBrojPoseta() {
        return brojPoseta;
    }

    public void setBrojPoseta(int brojPoseta) {
        this.brojPoseta = brojPoseta;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Komentar> komentari) {
        this.komentari = komentari;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public List<Tag> getTagovi() {
        return tagovi;
    }

    public void setTagovi(List<Tag> tagovi) {
        this.tagovi = tagovi;
    }
}
