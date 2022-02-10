package ir.ac.kntu.main;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.store.*;
import ir.ac.kntu.main.DataBase;

import java.util.ArrayList;

public class Sort {
    private DataBase dataBase;

    public Sort(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void sorting(SortMode sortMode) {
        switch (sortMode) {
            case ASCENDING_BASED_ON_SCORE:
                restaurantsAscendingSortBasedOnScore();
                superMarketsAscendingSortBasedOnScore();
                fruitShopsAscendingSortBasedOnScore();
                deliveriesAscendingSortBasedOnScore();
                foodsAscendingSortBasedOnScore();
                break;
            case ASCENDING_BASED_ON_COMMENTS:
                restaurantsAscendingSortBasedOnCommentNumber();
                superMarketsAscendingSortBasedOnCommentNumber();
                fruitShopsAscendingSortBasedOnCommentNumber();
                deliveriesAscendingSortBasedOnCommentsNumber();
                foodsAscendingSortBasedOnCommentsNumber();
                break;
            case DESCENDING_BASED_ON_SCORE:
                restaurantsDescendingSortBasedOnScore();
                superMarketsDescendingSortBasedOnScore();
                fruitShopsDescendingSortBasedOnScore();
                deliveriesDescendingSortBasedOnScore();
                foodsDescendingSortBasedOnScore();
                break;
            case DESCENDING_BASED_ON_COMMENTS:
                restaurantsDescendingSortBasedOnCommentNumber();
                superMarketsDescendingSortBasedOnCommentNumber();
                fruitShopsDescendingSortBasedOnCommentNumber();
                deliveriesDescendingSortBasedOnCommentsNumber();
                foodsDescendingSortBasedOnCommentsNumber();
                break;
            default:
                break;
        }
    }

    private void restaurantsAscendingSortBasedOnScore() {
        if (dataBase.getRestaurants().size() == 0) {
            return;
        }
        ArrayList<Restaurant> sorted = new ArrayList<>();
        int numberOfRestaurants = dataBase.getRestaurants().size();
        for (int i = 0; i < numberOfRestaurants; i++) {
            Restaurant maxScore = dataBase.getRestaurants().get(0);
            for (Restaurant restaurant : dataBase.getRestaurants()) {
                if (restaurant.getScore() > maxScore.getScore()) {
                    maxScore = restaurant;
                }
            }
            dataBase.getRestaurants().remove(maxScore);
            sorted.add(maxScore);
        }
        dataBase.setRestaurants(sorted);
    }

    private void restaurantsAscendingSortBasedOnCommentNumber() {
        if (dataBase.getRestaurants().size() == 0) {
            return;
        }
        ArrayList<Restaurant> sorted = new ArrayList<>();
        int numberOfRestaurants = dataBase.getRestaurants().size();
        for (int i = 0; i < numberOfRestaurants; i++) {
            Restaurant maxComment = dataBase.getRestaurants().get(0);
            for (Restaurant restaurant : dataBase.getRestaurants()) {
                if (restaurant.getComments().size() > maxComment.getComments().size()) {
                    maxComment = restaurant;
                }
            }
            dataBase.getRestaurants().remove(maxComment);
            sorted.add(maxComment);
        }
        dataBase.setRestaurants(sorted);
    }

    private void restaurantsDescendingSortBasedOnScore() {
        if (dataBase.getRestaurants().size() == 0) {
            return;
        }
        ArrayList<Restaurant> sorted = new ArrayList<>();
        int numberOfRestaurants = dataBase.getRestaurants().size();
        for (int i = 0; i < numberOfRestaurants; i++) {
            Restaurant minScore = dataBase.getRestaurants().get(0);
            for (Restaurant restaurant : dataBase.getRestaurants()) {
                if (restaurant.getScore() < minScore.getScore()) {
                    minScore = restaurant;
                }
            }
            dataBase.getRestaurants().remove(minScore);
            sorted.add(minScore);
        }
        dataBase.setRestaurants(sorted);
    }

    private void restaurantsDescendingSortBasedOnCommentNumber() {
        if (dataBase.getRestaurants().size() == 0) {
            return;
        }
        ArrayList<Restaurant> sorted = new ArrayList<>();
        int numberOfRestaurants = dataBase.getRestaurants().size();
        for (int i = 0; i < numberOfRestaurants; i++) {
            Restaurant minComment = dataBase.getRestaurants().get(0);
            for (Restaurant restaurant : dataBase.getRestaurants()) {
                if (restaurant.getComments().size() < minComment.getComments().size()) {
                    minComment = restaurant;
                }
            }
            dataBase.getRestaurants().remove(minComment);
            sorted.add(minComment);
        }
        dataBase.setRestaurants(sorted);
    }

    private void superMarketsAscendingSortBasedOnScore() {
        if (dataBase.getSuperMarkets().size() == 0) {
            return;
        }
        ArrayList<SuperMarket> sorted = new ArrayList<>();
        int numberOfSuperMarkets = dataBase.getSuperMarkets().size();
        for (int i = 0; i < numberOfSuperMarkets; i++) {
            SuperMarket maxScore = dataBase.getSuperMarkets().get(0);
            for (SuperMarket superMarket : dataBase.getSuperMarkets()) {
                if (superMarket.getScore() > maxScore.getScore()) {
                    maxScore = superMarket;
                }
            }
            dataBase.getSuperMarkets().remove(maxScore);
            sorted.add(maxScore);
        }
        dataBase.setSuperMarkets(sorted);
    }

    private void superMarketsAscendingSortBasedOnCommentNumber() {
        if (dataBase.getSuperMarkets().size() == 0) {
            return;
        }
        ArrayList<SuperMarket> sorted = new ArrayList<>();
        int numberOfSuperMarkets = dataBase.getSuperMarkets().size();
        for (int i = 0; i < numberOfSuperMarkets; i++) {
            SuperMarket maxComment = dataBase.getSuperMarkets().get(0);
            for (SuperMarket superMarket : dataBase.getSuperMarkets()) {
                if (superMarket.getComments().size() > maxComment.getComments().size()) {
                    maxComment = superMarket;
                }
            }
            dataBase.getSuperMarkets().remove(maxComment);
            sorted.add(maxComment);
        }
        dataBase.setSuperMarkets(sorted);
    }

    private void superMarketsDescendingSortBasedOnScore() {
        if (dataBase.getSuperMarkets().size() == 0) {
            return;
        }
        ArrayList<SuperMarket> sorted = new ArrayList<>();
        int numberOfSuperMarkets = dataBase.getSuperMarkets().size();
        for (int i = 0; i < numberOfSuperMarkets; i++) {
            SuperMarket minScore = dataBase.getSuperMarkets().get(0);
            for (SuperMarket superMarket : dataBase.getSuperMarkets()) {
                if (superMarket.getScore() < minScore.getScore()) {
                    minScore = superMarket;
                }
            }
            dataBase.getSuperMarkets().remove(minScore);
            sorted.add(minScore);
        }
        dataBase.setSuperMarkets(sorted);
    }

    private void superMarketsDescendingSortBasedOnCommentNumber() {
        if (dataBase.getSuperMarkets().size() == 0) {
            return;
        }
        ArrayList<SuperMarket> sorted = new ArrayList<>();
        int numberOfSuperMarkets = dataBase.getSuperMarkets().size();
        for (int i = 0; i < numberOfSuperMarkets; i++) {
            SuperMarket minComment = dataBase.getSuperMarkets().get(0);
            for (SuperMarket superMarket : dataBase.getSuperMarkets()) {
                if (superMarket.getComments().size() < minComment.getComments().size()) {
                    minComment = superMarket;
                }
            }
            dataBase.getSuperMarkets().remove(minComment);
            sorted.add(minComment);
        }
        dataBase.setSuperMarkets(sorted);
    }

    private void fruitShopsAscendingSortBasedOnScore() {
        if (dataBase.getFruitShops().size() == 0) {
            return;
        }
        ArrayList<FruitShop> sorted = new ArrayList<>();
        int numberOfFruitShops = dataBase.getFruitShops().size();
        for (int i = 0; i < numberOfFruitShops; i++) {
            FruitShop maxScore = dataBase.getFruitShops().get(0);
            for (FruitShop fruitShop : dataBase.getFruitShops()) {
                if (fruitShop.getScore() > maxScore.getScore()) {
                    maxScore = fruitShop;
                }
            }
            dataBase.getFruitShops().remove(maxScore);
            sorted.add(maxScore);
        }
        dataBase.setFruitShops(sorted);
    }

    private void fruitShopsAscendingSortBasedOnCommentNumber() {
        if (dataBase.getFruitShops().size() == 0) {
            return;
        }
        ArrayList<FruitShop> sorted = new ArrayList<>();
        int numberOfFruitShops = dataBase.getFruitShops().size();
        for (int i = 0; i < numberOfFruitShops; i++) {
            FruitShop maxComment = dataBase.getFruitShops().get(0);
            for (FruitShop fruitShop : dataBase.getFruitShops()) {
                if (fruitShop.getComments().size() > maxComment.getComments().size()) {
                    maxComment = fruitShop;
                }
            }
            dataBase.getFruitShops().remove(maxComment);
            sorted.add(maxComment);
        }
        dataBase.setFruitShops(sorted);
    }

    private void fruitShopsDescendingSortBasedOnScore() {
        if (dataBase.getFruitShops().size() == 0) {
            return;
        }
        ArrayList<FruitShop> sorted = new ArrayList<>();
        int numberOfFruitShops = dataBase.getFruitShops().size();
        for (int i = 0; i < numberOfFruitShops; i++) {
            FruitShop minScore = dataBase.getFruitShops().get(0);
            for (FruitShop fruitShop : dataBase.getFruitShops()) {
                if (fruitShop.getScore() < minScore.getScore()) {
                    minScore = fruitShop;
                }
            }
            dataBase.getFruitShops().remove(minScore);
            sorted.add(minScore);
        }
        dataBase.setFruitShops(sorted);
    }

    private void fruitShopsDescendingSortBasedOnCommentNumber() {
        if (dataBase.getFruitShops().size() == 0) {
            return;
        }
        ArrayList<FruitShop> sorted = new ArrayList<>();
        int numberOfFruitShops = dataBase.getFruitShops().size();
        for (int i = 0; i < numberOfFruitShops; i++) {
            FruitShop minComment = dataBase.getFruitShops().get(0);
            for (FruitShop fruitShop : dataBase.getFruitShops()) {
                if (fruitShop.getComments().size() < minComment.getComments().size()) {
                    minComment = fruitShop;
                }
            }
            dataBase.getFruitShops().remove(minComment);
            sorted.add(minComment);
        }
        dataBase.setFruitShops(sorted);
    }

    private void deliveriesAscendingSortBasedOnScore() {
        if (dataBase.getDeliveries().size() == 0) {
            return;
        }
        ArrayList<Delivery> sorted = new ArrayList<>();
        int numberOfDeliveries = dataBase.getDeliveries().size();
        for (int i = 0; i < numberOfDeliveries; i++) {
            Delivery maxScore = dataBase.getDeliveries().get(0);
            for (Delivery delivery : dataBase.getDeliveries()) {
                if (delivery.getScore() > maxScore.getScore()) {
                    maxScore = delivery;
                }
            }
            dataBase.getDeliveries().remove(maxScore);
            sorted.add(maxScore);
        }
        dataBase.setDeliveries(sorted);
    }

    private void deliveriesAscendingSortBasedOnCommentsNumber() {
        if (dataBase.getDeliveries().size() == 0) {
            return;
        }
        ArrayList<Delivery> sorted = new ArrayList<>();
        int numberOfDeliveries = dataBase.getDeliveries().size();
        for (int i = 0; i < numberOfDeliveries; i++) {
            Delivery maxComment = dataBase.getDeliveries().get(0);
            for (Delivery delivery : dataBase.getDeliveries()) {
                if (delivery.getComments().size() > maxComment.getComments().size()) {
                    maxComment = delivery;
                }
            }
            dataBase.getDeliveries().remove(maxComment);
            sorted.add(maxComment);
        }
        dataBase.setDeliveries(sorted);
    }

    private void deliveriesDescendingSortBasedOnScore() {
        if (dataBase.getDeliveries().size() == 0) {
            return;
        }
        ArrayList<Delivery> sorted = new ArrayList<>();
        int numberOfDeliveries = dataBase.getDeliveries().size();
        for (int i = 0; i < numberOfDeliveries; i++) {
            Delivery minScore = dataBase.getDeliveries().get(0);
            for (Delivery delivery : dataBase.getDeliveries()) {
                if (delivery.getScore() < minScore.getScore()) {
                    minScore = delivery;
                }
            }
            dataBase.getDeliveries().remove(minScore);
            sorted.add(minScore);
        }
        dataBase.setDeliveries(sorted);
    }

    private void deliveriesDescendingSortBasedOnCommentsNumber() {
        if (dataBase.getDeliveries().size() == 0) {
            return;
        }
        ArrayList<Delivery> sorted = new ArrayList<>();
        int numberOfDeliveries = dataBase.getDeliveries().size();
        for (int i = 0; i < numberOfDeliveries; i++) {
            Delivery minComment = dataBase.getDeliveries().get(0);
            for (Delivery delivery : dataBase.getDeliveries()) {
                if (delivery.getComments().size() < minComment.getComments().size()) {
                    minComment = delivery;
                }
            }
            dataBase.getDeliveries().remove(minComment);
            sorted.add(minComment);
        }
        dataBase.setDeliveries(sorted);
    }

    private void foodsAscendingSortBasedOnScore() {
        ArrayList<Food> sorted = new ArrayList<>();
        for (Restaurant restaurant : dataBase.getRestaurants()) {
            int numberOfFoods = restaurant.getMenu().size();
            for (int i = 0; i < numberOfFoods; i++) {
                if (restaurant.getMenu().size() == 0) {
                    continue;
                }
                Food maxScore = restaurant.getMenu().get(0);
                for (Food food: restaurant.getMenu()) {
                    if (food.getScore() > maxScore.getScore()) {
                        maxScore = food;
                    }
                }
                restaurant.getMenu().remove(maxScore);
                sorted.add(maxScore);
            }
            restaurant.setMenu(sorted);
            sorted = new ArrayList<>();
        }
    }

    private void foodsAscendingSortBasedOnCommentsNumber() {
        ArrayList<Food> sorted = new ArrayList<>();
        for (Restaurant restaurant : dataBase.getRestaurants()) {
            int numberOfFoods = restaurant.getMenu().size();
            for (int i = 0; i < numberOfFoods; i++) {
                if (restaurant.getMenu().size() == 0) {
                    continue;
                }
                Food maxComment = restaurant.getMenu().get(0);
                for (Food food : restaurant.getMenu()) {
                    if (food.getComments().size() > maxComment.getComments().size()) {
                        maxComment = food;
                    }
                }
                restaurant.getMenu().remove(maxComment);
                sorted.add(maxComment);
            }
            restaurant.setMenu(sorted);
            sorted = new ArrayList<>();
        }
    }

    private void foodsDescendingSortBasedOnScore() {
        ArrayList<Food> sorted = new ArrayList<>();
        for (Restaurant restaurant : dataBase.getRestaurants()) {
            int numberOfFoods = restaurant.getMenu().size();
            for (int i = 0; i < numberOfFoods; i++) {
                if (restaurant.getMenu().size() == 0) {
                    continue;
                }
                Food minScore = restaurant.getMenu().get(0);
                for (Food food: restaurant.getMenu()) {
                    if (food.getScore() < minScore.getScore()) {
                        minScore = food;
                    }
                }
                restaurant.getMenu().remove(minScore);
                sorted.add(minScore);
            }
            restaurant.setMenu(sorted);
            sorted = new ArrayList<>();
        }
    }

    private void foodsDescendingSortBasedOnCommentsNumber() {
        ArrayList<Food> sorted = new ArrayList<>();
        for (Restaurant restaurant : dataBase.getRestaurants()) {
            int numberOfFoods = restaurant.getMenu().size();
            for (int i = 0; i < numberOfFoods; i++) {
                if (restaurant.getMenu().size() == 0) {
                    continue;
                }
                Food minComment = restaurant.getMenu().get(0);
                for (Food food : restaurant.getMenu()) {
                    if (food.getComments().size() < minComment.getComments().size()) {
                        minComment = food;
                    }
                }
                restaurant.getMenu().remove(minComment);
                sorted.add(minComment);
            }
            restaurant.setMenu(sorted);
            sorted = new ArrayList<>();
        }
    }
}
