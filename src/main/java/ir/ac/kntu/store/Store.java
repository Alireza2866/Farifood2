package ir.ac.kntu.store;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.comment.Commentable;

import java.time.LocalTime;
import java.util.ArrayList;

public class Store implements Commentable {
    private String name;

    private String address;

    private double score;

    private WorkingHours workingHours;

    private ArrayList<Comment> comments;

    public Store() {
        score = 5;
        comments = new ArrayList<>();
    }

    public Store(String name, String address, WorkingHours workingHours) {
        this.name = name;
        this.address = address;
        this.workingHours = workingHours;
        score = 5;
        comments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getScore() {
        return score;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(WorkingHours workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public ArrayList<Comment> getComments() {
        return comments;
    }

    @Override
    public void addAComment(Comment comment) {
        if (comments.size() == 0) {
            score = comment.getScore();
        } else {
            score = ((score * comments.size()) + comment.getScore()) / (comments.size() + 1);
        }
        comments.add(comment);
    }

    public boolean isOpen() {
        if (workingHours.getStartTime().compareTo(LocalTime.now()) < 0
                && workingHours.getCloseTime().compareTo(LocalTime.now()) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }
}
