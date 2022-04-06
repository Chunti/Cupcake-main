package dat.startcode.model.persistence;

import dat.startcode.model.entities.Bottom;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDate;
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

    public void createOrderline(int orderId, int bottom, int topping,int amount){
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
}
