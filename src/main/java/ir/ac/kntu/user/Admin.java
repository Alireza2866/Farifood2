package ir.ac.kntu.user;

import java.util.Objects;

public class Admin extends User{
    public Admin(String userName, String password) {
        super(userName, password);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Admin admin = (Admin) object;
        return Objects.equals(getUserName(), admin.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName());
    }
}
