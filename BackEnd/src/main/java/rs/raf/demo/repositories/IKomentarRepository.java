package rs.raf.demo.repositories;


import rs.raf.demo.entities.Komentar;

import java.util.List;

public interface IKomentarRepository {
    public List<Komentar> all();
    public Komentar findById(int id);
    public Komentar insert(Komentar komentar);
}
