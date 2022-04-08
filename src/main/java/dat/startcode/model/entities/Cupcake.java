package dat.startcode.model.entities;

import dat.startcode.model.persistence.BottomMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ToppingMapper;

public class Cupcake {

    int bottomId;
    int toppingId;
    int amount;

    String bottom;
    String topping;
    int price;

    public Cupcake(int bottomId, int toppingId, String bottomName, int bottomPrice, String toppingName,int toppingPrice, int amount) {
        this.bottomId=bottomId;
        this.toppingId=toppingId;
        this.bottom = bottomName;
        this.topping = toppingName;
        this.amount = amount;
        this.price = (bottomPrice+toppingPrice)*amount;
    }


    public String getBottom() {
        return bottom;
    }

    public int getBottomId() {
        return bottomId;
    }

    public int getAmount() {
        return amount;
    }

    public int getToppingId() {
        return toppingId;
    }

    public String getTopping() {
        return topping;
    }

    public int getPrice() {
        return price;
    }
}
