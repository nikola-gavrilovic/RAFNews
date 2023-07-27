package rs.raf.demo.services;

import rs.raf.demo.entities.Komentar;
import rs.raf.demo.repositories.IKomentarRepository;

import javax.inject.Inject;
import java.util.List;

public class KomentarService {

    public KomentarService() {
        System.out.println(this);
    }

    @Inject
    private IKomentarRepository komentarRepository;
    
    public List<Komentar> all() {
    	return this.komentarRepository.all();
    }
    
    public Komentar findById(int id) {
    	return this.komentarRepository.findById(id);
    } 
    
    public Komentar insert(Komentar komentar) {
    	this.komentarRepository.insert(komentar);
    	return komentar;
    }

    
}
