package rs.raf.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Komentar implements Serializable {

    private static final long serialVersionUID = 4657428929989239650L;

    private int id;
    private String autor;
//    @NotNull(message = "Text field is required")
//    @NotEmpty(message = "Text field is required")
    private String tekst;
    private LocalDate datumKreiranja;
//    @NotNull(message = "Name field is required")
//    @NotEmpty(message = "Name field is required")
//	private String name;
//

    public Komentar(){

    }

    public Komentar(int id, String tekst, String autor){
        this.id = id;
        this.tekst = tekst;
        this.autor = autor;
    }

    public Komentar(int id, String autor, String tekst, LocalDate datumKreiranja) {
        this.id = id;
        this.autor = autor;
        this.tekst = tekst;
        this.datumKreiranja = datumKreiranja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDate getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(LocalDate datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

}
