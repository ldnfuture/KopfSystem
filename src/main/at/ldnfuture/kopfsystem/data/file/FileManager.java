package at.ldnfuture.kopfsystem.data.file;

import at.ldnfuture.kopfsystem.main.Main;
import at.ldnfuture.kopfsystem.data.Data;

import java.util.ArrayList;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 10.05.2021
 */
public class FileManager {

    private static FileWriter file = new FileWriter(Main.getInctance().getDataFolder().getPath(), "config.yml");

    public static FileWriter getFile(){
        return file;
    }

    public static void loadFile(){
        if(!file.exist()){
            file.setValue("Prefix", "§b[§aKopfSystem§b]");
            file.setValue("KopfCooldown", 1209600);
            file.setValue("MySQL.use", false);
            file.setValue("MySQL.Host", "127.0.0.1");
            file.setValue("MySQL.Port", 3306);
            file.setValue("MySQL.Database", "KopfDB");
            file.setValue("MySQL.User", "root");
            file.setValue("MySQL.Password", "admin");
            ArrayList<String> list = new ArrayList<>();
            file.setValue("Users", list);
            file.save();
        }
    }

    public static void readFile(){
        Data.Prefix = file.getString("Prefix").replaceAll("&", "§");
        Data.kopfCooldown = file.getInt("KopfCooldown");
        Data.useMySQL = file.getBoolean("MySQL.use");
        Data.MySQLHost = file.getString("MySQL.Host");
        Data.MySQLPort = file.getInt("MySQL.Port");
        Data.MySQLDatabase = file.getString("MySQL.Database");
        Data.MySQLUser = file.getString("MySQL.User");
        Data.MySQLPassword = file.getString("MySQL.Password");
    }

    public static void relaodFile(){
        file = new FileWriter(Main.getInctance().getDataFolder().getPath(), "config.yml");
        readFile();
    }

}
