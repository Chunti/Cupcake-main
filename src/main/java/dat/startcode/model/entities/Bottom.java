package dat.startcode.model.entities;

public class Bottom {

    String name;
    int price;

    public Bottom(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String toString() {
        return name + " (" +price + ")";
    }
}
