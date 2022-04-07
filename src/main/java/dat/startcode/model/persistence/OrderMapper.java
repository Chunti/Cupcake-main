package dat.startcode.model.persistence;

import dat.startcode.model.entities.Bottom;
import dat.startcode.model.entities.Cupcake;
import dat.startcode.model.entities.Order;
import dat.startcode.model.exceptions.DatabaseException;

import java.io.InvalidObjectException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper {

    ConnectionPool connectionPool;

    public OrderMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public int createOrder(int userId) {
        LocalDate today = LocalDate.now();


        Logger.getLogger("web").log(Level.INFO, "");

        boolean result = false;
        int newId = 0;

        String sql = "INSERT INTO `order` (user_id, `date`) values(?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, String.valueOf(userId));
                ps.setString(2, String.valueOf(today));

                int rowsAffexted = ps.executeUpdate();
                if (rowsAffexted == 1) {
                    result = true;
                } else {
                    throw new DatabaseException("Kunne ikke insaette ordre");
                }
                ResultSet idResultset = ps.getGeneratedKeys();
                if (idResultset.next()) {
                    newId = idResultset.getInt(1);

                }
            } catch (SQLException | DatabaseException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    public void createOrderline(int orderId, int bottom, int topping, int amount) {
        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "insert into orderline (order_id, bottom_id, topping_id, amount) values(?,?,?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.setInt(2, bottom);
                ps.setInt(3, topping);
                ps.setInt(4, amount);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cupcake> orderlineData(int orderId) {
        ArrayList<Cupcake> cupcakes = new ArrayList<>();

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "select bottom_name, bottom_price , topping_name, topping_price, amount from orderline  " +
                "inner join bottom on orderline.bottom_id = bottom.bottom_id " +
                "inner join topping on orderline.topping_id = topping.topping_id " +
                "where order_id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,orderId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String bottom = rs.getString("bottom_name");
                    int bottomPrice = rs.getInt("bottom_price");
                    String topping = rs.getString("topping_name");
                    int toppingPrice = rs.getInt("topping_price");
                    int amount = rs.getInt("amount");

                    cupcakes.add(new Cupcake(bottom, bottomPrice, topping, toppingPrice, amount));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cupcakes;
    }

    public ArrayList<Order> getOrderDataForUser(int userId) {
        ArrayList<Order> orders = new ArrayList<>();
        System.out.println(userId);
        int totalPrice = 0;
        int oldId =-1;
        int orderId = 0;
        String date = null;

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "select  cupcake.order.order_id, cupcake.order.user_id, cupcake.order.date, amount * (topping_price + bottom_price) AS totalPrice from `order` " +
                "inner join orderline on cupcake.order.order_id = orderline.order_id " +
                "inner join bottom on orderline.bottom_id = bottom.bottom_id " +
                "inner join topping on orderline.topping_id = topping.topping_id "+
                "where cupcake.order.user_id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,userId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    orderId = rs.getInt("order_id");
                    if(orderId != oldId && oldId != -1){
                        orders.add(new Order(oldId, userId,date,totalPrice));
                        totalPrice = 0;
                    }

                    userId = rs.getInt("user_id");
                    date = rs.getString("date");

                    int price = rs.getInt("totalPrice");
                    totalPrice += price;

                    oldId = orderId;
                }
                if(date != null){
                    orders.add(new Order(orderId, userId,date,totalPrice));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public ArrayList<Order> getOrderDataForAdmin() {
        ArrayList<Order> orders = new ArrayList<>();
        int totalPrice = 0;
        int oldId =-1;
        int orderId = 0;
        int userId = 0;
        String date = null;

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "select  cupcake.order.order_id, cupcake.order.user_id, cupcake.order.date, amount * (topping_price + bottom_price) AS totalPrice from `order` " +
                "inner join orderline on cupcake.order.order_id = orderline.order_id " +
                "inner join bottom on orderline.bottom_id = bottom.bottom_id " +
                "inner join topping on orderline.topping_id = topping.topping_id ";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    orderId = rs.getInt("order_id");
                    if(orderId != oldId && oldId != -1){
                        orders.add(new Order(oldId, userId,date,totalPrice));
                        totalPrice = 0;
                    }

                    userId = rs.getInt("user_id");
                    date = rs.getString("date");

                    int price = rs.getInt("totalPrice");
                    totalPrice += price;

                    oldId = orderId;
                }
                if(date != null){
                    orders.add(new Order(orderId, userId,date,totalPrice));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
