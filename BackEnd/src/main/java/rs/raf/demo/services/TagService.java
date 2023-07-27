package rs.raf.demo.services;

import rs.raf.demo.entities.Tag;
import rs.raf.demo.entities.Vest;
import rs.raf.demo.repositories.ITagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    public TagService() {
        System.out.println(this);
    }

    @Inject
    private ITagRepository tagRepository;
    
    public List<Tag> all() {
    	return this.tagRepository.all();
    }
    
    public List<Vest> findVestiByTagId(int id) {
    	return this.tagRepository.findVestiByTagId(id);
    } 
    
    public Tag insert(Tag tag) {
    	this.tagRepository.insert(tag);
    	return tag;
    }

    
}
