package ir.ac.kntu.comment;

import ir.ac.kntu.user.Customer;

public class Comment {
    private Customer customer;

    private Commentable commentable;

    private String comment;

    private int score;

    public Comment(int score, String comment, Customer customer, Commentable commentable) {
        this.comment = comment;
        this.customer = customer;
        this.commentable = commentable;
        if (1 <= score && score <= 5) {
            this.score = score;
        } else {
            this.score = 5;
        }
    }

    public double getScore() {
        return score;
    }

    public void setScore(int score) {
        if(1 <= score && score <= 5) {
            this.score = score;
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Commentable getCommentable() {
        return commentable;
    }

    @Override
    public String toString() {
        return ":\n(" + score + "/5)" + "\n" + comment;
    }
}