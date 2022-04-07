package dat.startcode.model.entities;

import dat.startcode.model.persistence.BottomMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ToppingMapper;

public class Cupcake {

    String bottom;
    String topping;
    int price;

    public Cupcake(String bottomName, int bottomPrice, String toppingName,int toppingPrice, int amount) {

        this.bottom = bottomName;
        this.topping = toppingName;
        this.price = (bottomPrice+toppingPrice)*amount;
    }


    public String getBottom() {
        return bottom;
    }

    public String getTopping() {
        return topping;
    }

    public int getPrice() {
        return price;
    }
}
