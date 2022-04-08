package dat.startcode.model.entities;

public class Customer {

    int userId;
    String name;
    String email;
    int phoneNumber;
    int balance;

    public Customer(int userId, String name, String email, int phoneNumber, int balance) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public void updateBalance(){

    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getBalance() {
        return balance;
    }
}
