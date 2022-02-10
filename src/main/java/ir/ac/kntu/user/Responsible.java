package ir.ac.kntu.user;

import ir.ac.kntu.store.Store;

public class Responsible extends User{
    private Store store;

    public Responsible(String userName, String password, Store store) {
        super(userName, password);
        this.store = store;
    }

    public Store getStore() {
        return store;
    }
}
