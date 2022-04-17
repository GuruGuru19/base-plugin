package me.guruguru19;

import me.guruguru19.listeners.EventClass;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"\nBasePlugin Loaded\n");
        setListener(new EventClass());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setListener(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
