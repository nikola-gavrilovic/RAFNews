package rs.raf.demo.repositories;


import rs.raf.demo.entities.Tag;
import rs.raf.demo.entities.Vest;

import java.util.List;

public interface ITagRepository {
    public List<Tag> all();
    public List<Vest> findVestiByTagId(int id);
    public Tag insert(Tag tag);
}
