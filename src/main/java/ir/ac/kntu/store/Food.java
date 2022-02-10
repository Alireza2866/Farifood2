package ir.ac.kntu.store;

import ir.ac.kntu.comment.Comment;
import ir.ac.kntu.comment.Commentable;

import java.util.ArrayList;
import java.util.Objects;

public class Food extends Stuff implements Commentable {
    private int cookTime;

    private double score;

    private ArrayList<Comment> comments;

    public Food(String name, int price, int cookTime, Restaurant restaurant) {
        super(name, price, restaurant);
        this.cookTime = cookTime;
        score = 5;
        comments = new ArrayList<>();
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public double getScore() {
        return score;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Food food = (Food) object;
        return Objects.equals(getName(), food.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return getName() + " :    price:" + getPrice() + "    --> score:" +
                (Double.toString(getScore()) + "0").substring(0, 4) + " , " + getComments().size() + " vote(s)\n";
    }
}
