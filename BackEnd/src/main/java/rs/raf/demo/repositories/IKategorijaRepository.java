package rs.raf.demo.repositories;


import rs.raf.demo.entities.Kategorija;

import java.util.List;

public interface IKategorijaRepository {
    public List<Kategorija> all();
    public Kategorija findById(int id);
    public Kategorija insert(Kategorija kategorija);

    void edit(Kategorija kategorija);

    void delete(Integer id);
}
