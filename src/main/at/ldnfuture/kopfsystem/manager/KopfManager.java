package at.ldnfuture.kopfsystem.manager;

import at.ldnfuture.kopfsystem.main.Main;
import at.ldnfuture.kopfsystem.data.Data;
import at.ldnfuture.kopfsystem.data.file.FileManager;
import at.ldnfuture.kopfsystem.data.mysql.SQLManager;
import org.bukkit.entity.Player;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 10.05.2021
 */
public class KopfManager {

    public static boolean canGetSkull(Player player){
        long end;
        if(player.hasPermission("kopf.admin")){
            return true;
        }
        if(Data.useMySQL){
            end = Main.players.get(player).getTime() + Data.kopfCooldown*1000L;
        }else{
            end = FileManager.getFile().getLong("Users." + player.getUniqueId().toString() + ".Time") + Data.kopfCooldown*1000L;
        }


        if(System.currentTimeMillis() >= end){
            return true;
        }else{
            return false;
        }
    }


    public static Long getRemindingTIme(Player player){
        if(Data.useMySQL){
            return (Main.players.get(player).getTime() + Data.kopfCooldown*1000L);
        }else{
            return (FileManager.getFile().getLong("Users." + player.getUniqueId().toString() + ".Time") + Data.kopfCooldown*1000L);
        }
    }

    public static void setWatingTime(Player player){
        if(Data.useMySQL){
            SQLManager.updateData(player, System.currentTimeMillis());
        }else{
            FileManager.getFile().setValue("Users." + player.getUniqueId().toString() + ".Time", System.currentTimeMillis());
            FileManager.getFile().save();
        }
    }


}
