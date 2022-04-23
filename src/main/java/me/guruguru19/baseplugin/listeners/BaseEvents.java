package me.guruguru19.baseplugin.listeners;

import me.guruguru19.baseplugin.BasePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class BaseEvents  implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if (event.getFrom().getChunk()!=event.getTo().getChunk()){
            if (BasePlugin.baseDataClass.claimed(event.getFrom().getChunk()) != BasePlugin.baseDataClass.claimed(event.getTo().getChunk())){
                Player player = event.getPlayer();
                if (BasePlugin.baseDataClass.claimed(event.getTo().getChunk()) != null){
                    player.sendMessage(ChatColor.WHITE + "Welcome to "+ChatColor.GOLD + Bukkit.getOfflinePlayer(BasePlugin.baseDataClass.claimed(event.getTo().getChunk())).getName() + "'s "+ChatColor.WHITE +"base");
                }
                else{
                    player.sendMessage(ChatColor.WHITE + "You just Exited from "+ChatColor.GOLD + Bukkit.getOfflinePlayer(BasePlugin.baseDataClass.claimed(event.getFrom().getChunk())).getName() + "'s"+ChatColor.WHITE +"base");
                }
            }
        }
    }
}
