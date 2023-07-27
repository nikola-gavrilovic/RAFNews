package rs.raf.demo.repositories;

import rs.raf.demo.entities.Korisnik;

import java.util.List;

public interface IKorisnikRepository {
    public List<Korisnik> all();
    public Korisnik findById(int id);
    public Korisnik insert(Korisnik korisnik);

    Korisnik findUser(String email);

    Korisnik aktivirajDeaktiviraj(Korisnik korisnik);

    Korisnik edit(Korisnik korisnik);
}
