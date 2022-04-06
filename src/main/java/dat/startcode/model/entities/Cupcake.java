package dat.startcode.model.entities;

import dat.startcode.model.persistence.BottomMapper;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ToppingMapper;

public class Cupcake {

    Bottom bottom;
    Topping topping;
    int amount;

    public Cupcake(int bottomId, int toppingId, int amount, ConnectionPool connectionPool) {
        BottomMapper bottomMapper = new BottomMapper(connectionPool);
        ToppingMapper toppingMapper = new ToppingMapper(connectionPool);

        this.bottom = bottomMapper.getBottomFromString(bottomId);
        this.topping = toppingMapper.getToppingFromString(toppingId);
        this.amount = amount;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public Topping getTopping() {
        return topping;
    }

    public int getAmount() {
        return amount;
    }
}
