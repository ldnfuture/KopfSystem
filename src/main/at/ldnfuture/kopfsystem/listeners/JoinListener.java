package at.ldnfuture.kopfsystem.listeners;

import at.ldnfuture.kopfsystem.main.Main;
import at.ldnfuture.kopfsystem.data.Data;
import at.ldnfuture.kopfsystem.data.file.FileManager;
import at.ldnfuture.kopfsystem.data.mysql.SQLManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 10.05.2021
 */
public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(Data.useMySQL){
            String uuid = e.getPlayer().getUniqueId().toString();
            if(SQLManager.inKopf(uuid)){
                Main.players.put(e.getPlayer(), SQLManager.getData(uuid));
            }else{
                SQLManager.addToKopf(uuid, 0);
                Main.players.put(e.getPlayer(), SQLManager.getData(uuid));
                Main.log(" §6" + e.getPlayer().getName() + " §awurde in die Datenbank eingetragen!");
            }
        }else{
            if(!FileManager.getFile().valueExist("Users." + e.getPlayer().getUniqueId().toString() + ".Time")){
                savePlayerToFile(e.getPlayer());
            }
        }
    }

    private void savePlayerToFile(Player p ){
        FileManager.relaodFile();
        FileManager.getFile().setValue("Users." + p.getUniqueId().toString() + ".Time", 0);
        FileManager.getFile().save();
        Main.log(" §6" + p.getName() + " §awurde in die Config eingetragen!");
    }

}
