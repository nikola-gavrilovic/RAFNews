package rs.raf.demo.services;

import rs.raf.demo.entities.Komentar;
import rs.raf.demo.entities.Vest;
import rs.raf.demo.repositories.IVestRepository;

import javax.inject.Inject;
import java.util.List;

public class VestService {

    public VestService() {
        System.out.println(this);
    }

    @Inject
    private IVestRepository vestRepository;

    public List<Vest> all() {
        return this.vestRepository.all();
    }

    public Vest findById(int id) {
        return this.vestRepository.findById(id);
    }

    public Vest insert(Vest vest) {
        Vest vestDb = this.vestRepository.insert(vest);
        return vestDb;
    }

    public Vest addKomentar(Komentar komentar, int id) {
        Vest vest = this.vestRepository.addKomentar(komentar,id);
        return vest;
    }

    public void delete(int id) {
        this.vestRepository.deleteById(id);
    }

    public List<Vest> najcitanijeVesti() {
        return this.vestRepository.najcitanijeVesti();
    }
}
