package dat.startcode.model.persistence;

import dat.startcode.model.entities.Bottom;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BottomMapper
{
    ConnectionPool connectionPool;

    public BottomMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    public ArrayList<Bottom> getBottomData() throws DatabaseException {

        //createUser("mogens@lykketoft.dk", "Mogens Lykketoft", "MogensErGud","admin");
        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<Bottom> bottomArrayList = new ArrayList<>();

        String sql = "SELECT * FROM bottom";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    String name = rs.getString("bottom_name");
                    int price = rs.getInt("price");
                    Bottom bottom = new Bottom(name,price);
                    bottomArrayList.add(bottom);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return bottomArrayList;


    }



}