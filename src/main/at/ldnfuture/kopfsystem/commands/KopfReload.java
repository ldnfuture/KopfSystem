package at.ldnfuture.kopfsystem.commands;

import at.ldnfuture.kopfsystem.data.Data;
import at.ldnfuture.kopfsystem.data.file.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 10.05.2021
 */
public class KopfReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        if(commandSender.hasPermission("kopf.reload") || commandSender.hasPermission("kopf.admin")){
            FileManager.relaodFile();
            commandSender.sendMessage(Data.Prefix + " §bDie Config wurde reloaded!");
        }else{
            commandSender.sendMessage(Data.noPerm);
        }


        return false;
    }
}
