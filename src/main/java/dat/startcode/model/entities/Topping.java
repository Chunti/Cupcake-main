package dat.startcode.model.entities;

public class Topping {

    String name;
    int price;

    public Topping(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (" +price + ")";
    }
}
