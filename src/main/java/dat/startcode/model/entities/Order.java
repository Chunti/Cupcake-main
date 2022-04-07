package dat.startcode.model.entities;

public class Order {

    int orderId;
    int userId;
    String date;
    int totalPrice;

    public Order(int orderId, int userId, String date, int totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
