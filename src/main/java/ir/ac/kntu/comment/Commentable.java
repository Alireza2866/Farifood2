package ir.ac.kntu.comment;

import java.util.ArrayList;

public interface Commentable {
    String getName();

    void addAComment(Comment comment);

    ArrayList<Comment> getComments();
}
