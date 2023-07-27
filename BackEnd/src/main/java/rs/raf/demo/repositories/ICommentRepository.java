package rs.raf.demo.repositories;


import rs.raf.demo.entities.Komentar;

import java.util.List;

public interface ICommentRepository {
    public List<Komentar> getCommentsByPostId(int postId);
    public Komentar insert(Komentar comment);
}
