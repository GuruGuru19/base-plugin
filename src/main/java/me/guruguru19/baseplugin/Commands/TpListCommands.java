package me.guruguru19.baseplugin.Commands;

import me.border.utilities.scheduler.AsyncTasker;
import me.guruguru19.baseplugin.BasePlugin;
import me.guruguru19.baseplugin.file.tpdata.SerializableLocation;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TpListCommands extends CommandException implements Listener, CommandExecutor {

    private List<String> bannedWords = new ArrayList<String>(List.of(new String[]{"help", "add", "remove"}));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED+ "only players can use this command");
            return false;
        }
        Player player = (Player) sender;
        HashMap<String, SerializableLocation> tpList = BasePlugin.tpListData.getTpDataOfUUID(player.getUniqueId());
        if (tpList == null){
            sender.sendMessage(ChatColor.RED+ "tpList == null (should not happen)");
            return false;
        }
        if (args.length == 0){
            if (tpList.isEmpty()){
                player.sendMessage(ChatColor.GRAY+ "You don't have any tp spots saved");
                return true;
            }
            player.sendMessage(ChatColor.GOLD + player.getName() + ChatColor.WHITE + "'s tp list:\n");
            for (Map.Entry<String, SerializableLocation> entry: tpList.entrySet()) {
                player.sendMessage(ChatColor.GRAY + entry.getKey() + ": " + ChatColor.DARK_GRAY + entry.getValue() + "\n");
            }
            return true;
        }
        if (args.length == 1){
            if (args[0].equals("add")){
                player.sendMessage(ChatColor.GRAY + "/tplist add " + ChatColor.RED + "NAME");
                return true;
            }
            if (args[0].equals("remove")){
                player.sendMessage(ChatColor.GRAY + "/tplist remove " + ChatColor.RED + "NAME");
                return true;
            }
            if (args[0].equals("help")){
                player.sendMessage("");//TODO: add help message
                return true;
            }
            if (!tpList.containsKey(args[0])){
                player.sendMessage(ChatColor.RED + "Could not find '"+ ChatColor.DARK_RED+args[0]+ChatColor.RED+"' in your tp List");
                return true;
            }
            if (tpList.get(args[0]) != null){
                player.teleport(((SerializableLocation)tpList.get(args[0])).toLocation());
                player.sendMessage(ChatColor.WHITE + "teleported to: " + ChatColor.GOLD + args[0]);
                return true;
            }
            player.sendMessage(ChatColor.RED+ "Err (TpListCommands.java line:68)");
            return false;
        }
        if (args.length == 2){
            if (args[0].equals("add")){
                if (bannedWords.contains(args[1])){
                    player.sendMessage(ChatColor.GRAY + "/tplist add " + ChatColor.RED + args[1] + ChatColor.DARK_RED + "\n you can't use this name");
                    return true;
                }
                tpList.remove(args[1]);
                tpList.put(args[1], new SerializableLocation(player.getLocation()));
                saveData();
                player.sendMessage(ChatColor.WHITE + "New Tp '"+ChatColor.GOLD+args[1]+ChatColor.WHITE+"' added to you tp list");
                return true;
            }
            if (args[0].equals("remove")){
                if (tpList.containsKey(args[1])){
                    tpList.remove(args[1]);
                    saveData();
                    player.sendMessage(ChatColor.WHITE + "Tp '"+ChatColor.GOLD+args[1]+ChatColor.WHITE+"' removed from your tp list");
                    return true;
                }
                player.sendMessage(ChatColor.RED + "Could not find '"+ ChatColor.DARK_RED+args[1]+ChatColor.RED+"' in your tp List");
                return true;
            }
        }
        player.sendMessage(ChatColor.RED+ "Invalid Input... try '/tplist help')");
        return false;
    }

    private void saveData(){
        AsyncTasker.runTaskAsync(new Thread(() -> BasePlugin.tpListData.save()));
    }
}
