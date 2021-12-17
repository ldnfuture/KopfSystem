package at.ldnfuture.kopfsystem.main;

import at.ldnfuture.kopfsystem.utils.PlayerKopf;
import at.ldnfuture.kopfsystem.commands.Kopf;
import at.ldnfuture.kopfsystem.commands.KopfReload;
import at.ldnfuture.kopfsystem.data.Data;
import at.ldnfuture.kopfsystem.data.file.FileManager;
import at.ldnfuture.kopfsystem.data.mysql.MySQL;
import at.ldnfuture.kopfsystem.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 10.05.2021
 */
public class Main extends JavaPlugin {

    public static HashMap<Player, PlayerKopf> players = new HashMap<>();

    private static Main inctance;
    public static Main getInctance(){
        return inctance;
    }

    @Override
    public void onEnable() {
        inctance = this;
        try {
            loadFile();
            loadCommands();
            loadListener(Bukkit.getPluginManager());
            if(Data.useMySQL){
                loadMySQL();
                Bukkit.getOnlinePlayers().forEach(p -> p.kickPlayer(" §cReload des Server!"));
            }
        }catch (Exception e1){
            e1.printStackTrace();
            log(" §cDas Plugin konnte nicht geladen werden!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }finally{
            log(" §aDas Plugin wurde aktiviert!");
        }
    }

    @Override
    public void onDisable() {
        log(" §cDas Plugin wurde deaktiviert!");
    }

    private void loadCommands(){
        getCommand("kopf").setExecutor(new Kopf());
        getCommand("kopfreload").setExecutor(new KopfReload());
    }

    private void loadListener(PluginManager pm){
        pm.registerEvents(new JoinListener(),this);
    }

    private void loadFile(){
        FileManager.loadFile();
        FileManager.readFile();
    }

    private void loadMySQL(){
        MySQL.connect();
        MySQL.update("CREATE TABLE IF NOT EXISTS Kopf (UUID VARCHAR(255),Time LONG)");
    }

    public static void log(String msg){
        Bukkit.getConsoleSender().sendMessage(Data.Prefix + msg);
    }

}
