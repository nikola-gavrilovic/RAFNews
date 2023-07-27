package rs.raf.demo.services;

import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.repositories.IKategorijaRepository;

import javax.inject.Inject;
import java.util.List;

public class KategorijaService {

    public KategorijaService() {
        System.out.println(this);
    }

    @Inject
    private IKategorijaRepository kategorijaRepository;
    
    public List<Kategorija> all() {
    	return this.kategorijaRepository.all();
    }
    
    public Kategorija findById(int id) {
    	return this.kategorijaRepository.findById(id);
    } 
    
    public Kategorija insert(Kategorija kategorija) {
    	this.kategorijaRepository.insert(kategorija);
    	return kategorija;
    }


    public Kategorija edit(Kategorija kategorija) {
        this.kategorijaRepository.edit(kategorija);

        return kategorija;
    }

    public void delete(Integer id) {
        this.kategorijaRepository.delete(id);
    }
}
