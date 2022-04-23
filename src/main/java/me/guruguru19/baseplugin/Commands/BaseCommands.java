package me.guruguru19.baseplugin.Commands;

import me.guruguru19.baseplugin.BasePlugin;
import me.guruguru19.baseplugin.file.basedata.SerializableChunk;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseCommands extends CommandException implements Listener, CommandExecutor {

    private static final int BASE_SIDE_LENGTH = 13;
    private static final double DISTANCE_TO_BUILD_BASE = 0.0;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED+ "only players can use this command");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0){
            player.sendMessage(ChatColor.RED+ "Invalid input");
            return false;
        }
        if (args.length == 1 && args[0].equals("set")){
            if (player.getWorld().getEnvironment() != World.Environment.NORMAL){
                player.sendMessage(ChatColor.RED+ "You cant use this command in the " + player.getLocation().getWorld().getName());
                return false;
            }
            if (Math.sqrt(Math.pow(player.getLocation().getX(),2)+Math.pow(player.getLocation().getZ(),2))<DISTANCE_TO_BUILD_BASE){
                player.sendMessage(ChatColor.RED+ "You cant use this command because you are not far enough from (0,0)");
                return false;
            }
            return tryToBuildBase(player);
        }


        if (args.length == 1 && args[0].equals("print")){
            if (BasePlugin.baseDataClass.getBase(player.getUniqueId()) == null){
                player.sendMessage(ChatColor.RED+ "you don't have a base");
                return false;
            }
            player.sendMessage(ChatColor.WHITE+ (BasePlugin.baseDataClass.getBase(player.getUniqueId()).toString()));
            return true;
        }

        if (args.length == 1 && args[0].equals("remove")){
            if (BasePlugin.baseDataClass.getBase(player.getUniqueId()) == null){
                player.sendMessage(ChatColor.RED+ "you don't have a base");
                return false;
            }
            BasePlugin.baseDataClass.removeBase(player.getUniqueId());
            player.sendMessage(ChatColor.WHITE+ "base removed");
            return true;
        }

        if (args.length == 1 && args[0].equals("inside")){
            UUID uuid = BasePlugin.baseDataClass.claimed(player.getChunk());
            if (uuid == null){
                player.sendMessage(ChatColor.GOLD+ "you're not in anyone's base");
                return true;
            }
            player.sendMessage(ChatColor.WHITE+ "you're inside "+ChatColor.GOLD+Bukkit.getOfflinePlayer(uuid).getName()+ChatColor.WHITE+"'s base");
            return true;
        }


        if (args.length == 2 && args[0].equals("print")){
            if (!player.isOp()){
                player.sendMessage(ChatColor.RED+ "you need to have OP to use this command");
                return false;
            }
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
            if (target == null){
                player.sendMessage(ChatColor.RED+ "Player '"+args[1]+"' not found");
                return false;
            }
            if (BasePlugin.baseDataClass.getBase(target.getUniqueId()) == null){
                player.sendMessage(ChatColor.RED+ "Player '"+args[1]+"' doesn't have a base");
                return false;
            }
            player.sendMessage(ChatColor.WHITE+ (BasePlugin.baseDataClass.getBase(target.getUniqueId()).toString()));
            return true;
        }
        if (args.length == 2 && args[0].equals("remove")){
            if (!player.isOp()){
                player.sendMessage(ChatColor.RED+ "you need to have OP to use this command");
                return false;
            }
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
            if (target == null){
                player.sendMessage(ChatColor.RED+ "Player '"+args[1]+"' not found");
                return false;
            }
            if (BasePlugin.baseDataClass.getBase(target.getUniqueId()) == null){
                player.sendMessage(ChatColor.RED+ "Player '"+args[1]+"' doesn't have a base");
                return false;
            }
            BasePlugin.baseDataClass.removeBase(target.getUniqueId());
            player.sendMessage(ChatColor.WHITE+ args[1]+"'s base removed");
            return true;
        }


        player.sendMessage(ChatColor.RED+ "Invalid input");
        return false;
    }

    private boolean tryToBuildBase(Player player){
        List<Chunk> chunks = new ArrayList<>();
        Chunk chunk;
        int mid_x = player.getChunk().getX();
        int mid_z = player.getChunk().getZ();
        int p = 0;

        int l = BASE_SIDE_LENGTH;

        for (int i = -l/2; i <= l/2; i++) {
            for (int j = -l/2; j <= l/2; j++) {

                chunk = player.getWorld().getChunkAt(mid_x+i,mid_z+j );
                if (BasePlugin.baseDataClass.claimed(chunk)!=null && BasePlugin.baseDataClass.claimed(chunk) != player.getUniqueId()){
                    player.sendMessage(ChatColor.RED+ "Chunk claimed by"+ ChatColor.DARK_RED + Bukkit.getOfflinePlayer(BasePlugin.baseDataClass.claimed(chunk)).getName() + ChatColor.RED+" at\n"
                            +ChatColor.DARK_RED+new SerializableChunk(chunk));
                    return true;
                }
                chunks.add(chunk);
            }
        }

        if (Bukkit.getServer().getWorld("world_nether") == null){
            player.sendMessage(ChatColor.RED+ "nether chunks can't be accessed");
            return true;
        }

        l = 1+(BASE_SIDE_LENGTH/8);
        for (int i = -l/2; i <= l/2; i++) {
            for (int j = -l/2; j <= l/2; j++) {


                chunk = Bukkit.getServer().getWorld("world_nether").getChunkAt((mid_x/8)+i,(mid_z/8)+j );
                if (BasePlugin.baseDataClass.claimed(chunk) != null && BasePlugin.baseDataClass.claimed(chunk) != player.getUniqueId()){
                    player.sendMessage(ChatColor.RED+ "Chunk claimed by"+ ChatColor.DARK_RED + Bukkit.getOfflinePlayer(BasePlugin.baseDataClass.claimed(chunk)).getName() + ChatColor.RED+" at\n"
                            +ChatColor.DARK_RED+new SerializableChunk(chunk));
                    return true;
                }
                chunks.add(chunk);
            }
        }

        BasePlugin.baseDataClass.addBase(player.getUniqueId(), chunks);
        player.sendMessage(ChatColor.GOLD+ "New base set");
        return true;
    }
}
