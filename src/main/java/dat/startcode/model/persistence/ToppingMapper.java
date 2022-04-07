package dat.startcode.model.persistence;

import dat.startcode.model.entities.Bottom;
import dat.startcode.model.entities.Topping;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToppingMapper {
    ConnectionPool connectionPool;

    public ToppingMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    public ArrayList<Topping> getToppingData() throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<Topping> toppingArrayList = new ArrayList<>();

        String sql = "SELECT * FROM topping";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    String name = rs.getString("topping_name");
                    int price = rs.getInt("topping_price");
                    Topping topping = new Topping(name,price);
                    toppingArrayList.add(topping);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toppingArrayList;
    }


    public Topping getToppingFromString(int toppingId){


        Logger.getLogger("web").log(Level.INFO, "");

        Topping topping = null;

        String sql = "SELECT * FROM topping where topping_id= ? ";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1,toppingId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    String toppingName = rs.getString("topping_name");
                    int price = rs.getInt("topping_price");

                    topping = new Topping(toppingName,price);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topping;

    }

}
