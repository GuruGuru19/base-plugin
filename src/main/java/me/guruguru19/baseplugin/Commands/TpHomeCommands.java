package me.guruguru19.baseplugin.Commands;

import me.guruguru19.baseplugin.BasePlugin;
import me.guruguru19.baseplugin.file.tpdata.SerializableLocation;
import me.guruguru19.baseplugin.file.tpdata.TpHomeDataClass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class TpHomeCommands extends CommandException implements Listener, CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED+ "only players can use this command");
            return false;
        }
        Player sender = (Player) commandSender;
        if (args.length == 0){
            SerializableLocation pHome = BasePlugin.tpHomeData.getHome(sender.getUniqueId());
            if (pHome == null){
                sender.sendMessage(ChatColor.RED + "You don't have a home location saved");
                return true;
            }
            sender.teleport(pHome.toLocation());
            sender.sendMessage(ChatColor.WHITE + "Teleported Home");
            return true;
        }
        else{
            if (args.length == 1 && args[0].equals("set")){
                BasePlugin.tpHomeData.setHome(sender.getUniqueId(), sender.getLocation());
                sender.sendMessage(ChatColor.WHITE + "new Home set");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "invalid args, try: "+ChatColor.WHITE+"/tphome set");
            return true;
        }
    }
}
