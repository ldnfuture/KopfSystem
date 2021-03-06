package at.ldnfuture.kopfsystem.data.mysql;

import at.ldnfuture.kopfsystem.main.Main;
import at.ldnfuture.kopfsystem.utils.PlayerKopf;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 10.05.2021
 */
public class SQLManager {


    public static boolean inKopf(String uuid){
        String qry = "SELECT * FROM Kopf WHERE UUID=?";
        try{
            PreparedStatement stmt = MySQL.connection.prepareStatement(qry);
            stmt.setString(1,uuid);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch (SQLException e) {
            return false;
        }
    }

    public static void addToKopf(String uuid, long time){
        if(!inKopf(uuid)){
            String qry = "INSERT INTO Kopf (UUID,Time) VALUES (?,?)";
            try{
                PreparedStatement stmt = MySQL.connection.prepareStatement(qry);
                stmt.setString(1, uuid);
                stmt.setLong(2, time);
                stmt.execute();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static PlayerKopf getData(String uuid){
        String qry = "SELECT * FROM Kopf WHERE UUID=?";
        Long t = 0L;
        try{
            PreparedStatement stmt = MySQL.connection.prepareStatement(qry);
            stmt.setString(1,uuid);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                t = rs.getLong("Time");
            }
            return new PlayerKopf(uuid, t);
        }catch (SQLException e) {
            return null;
        }
    }

    public static void updateData(Player player, Long time){
        setData(player.getUniqueId().toString(), time);
        Main.players.put(player, new PlayerKopf(player.getUniqueId().toString(), time));
    }

    private static void setData(String uuid, long Time){
        String qry = "UPDATE Kopf SET Time=? WHERE UUID=?";
        try{
            PreparedStatement stmt = MySQL.connection.prepareStatement(qry);
            stmt.setLong(1, Time);
            stmt.setString(2, uuid);
            stmt.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
