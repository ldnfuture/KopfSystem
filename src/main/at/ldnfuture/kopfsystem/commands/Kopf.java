package at.ldnfuture.kopfsystem.commands;

import at.ldnfuture.kopfsystem.data.Data;
import at.ldnfuture.kopfsystem.manager.KopfManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;

/**
 * @author LDN_Future
 * @version 1.0.0
 * created at 10.05.2021
 */
public class Kopf implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst ein Spieler sein um diesen Befehl auszuführen!");
            return  false;
        }

        Player p = ((Player) sender);

        if(!p.hasPermission("kopf.use")){
            p.sendMessage(Data.noPerm);
            return false;
        }

        if(args.length == 1){
            if(KopfManager.canGetSkull(p)){
                KopfManager.setWatingTime(p);
                ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
                SkullMeta im = (SkullMeta) item.getItemMeta();
                im.setOwner(args[0]);
                im.setDisplayName("§dKopf von " + args[0]);
                item.setItemMeta(im);
                p.getInventory().addItem(item);
                p.sendMessage(Data.Prefix + " §bDu hast den Kopf von §a" + args[0] + " §berhalten!");
                playSoundForAll(Sound.WITHER_DEATH, 1, 2);
            }else{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                p.sendMessage(Data.Prefix + " §cBitte warte bis §a" + simpleDateFormat.format(KopfManager.getRemindingTIme(p)) + " §c!");
            }
        }else{
            if(KopfManager.canGetSkull(p)){
                p.sendMessage(Data.Prefix + " §bBitte benutze: §a/kopf <Spieler>§b!");
            }else{
                p.sendMessage(Data.Prefix + " §bBitte benutze: §a/kopf <Spieler>§b!");
            }
        }

        return false;
    }

    private void playSoundForAll(Sound sound, Integer v1, Integer v2){
        Bukkit.getOnlinePlayers().forEach(o -> o.playSound(o.getLocation(), sound, v1, v2));
    }
}
