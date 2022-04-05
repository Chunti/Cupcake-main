package dat.startcode.model.entities;

import java.util.Objects;

public class User
{
    private String name;
    private String email;
    private int role;
    private int phoneNumber;
    private int balance;

    public User(String name,String email, int role, int phoneNumber, int balance) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }



    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", balance=" + balance +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getBalance() {
        return balance;
    }
}
