package rs.raf.demo.repositories;


import rs.raf.demo.entities.Komentar;
import rs.raf.demo.entities.Vest;

import java.util.List;

public interface IVestRepository {
    public List<Vest> all();
    public Vest findById(int id);
    public Vest insert(Vest vest);

    public Vest addKomentar(Komentar komentar, int id);
//    public Vest insert(Vest vest, int kategorijaId);

    public void deleteById(int id);

    List<Vest> najcitanijeVesti();
}
