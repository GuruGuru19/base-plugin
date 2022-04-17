package me.guruguru19;

import me.guruguru19.file.AbstractSpigotYamlFile;
import me.guruguru19.file.tpdata.tpDataClass;
import me.guruguru19.listeners.EventClass;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasePlugin extends JavaPlugin {

    public static tpDataClass tpData;


    @Override
    public void onEnable() {
        // Plugin startup logic

        AbstractSpigotYamlFile.setupAll();
        setListener(new EventClass());

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"\nBasePlugin Loaded\n");
    }

    @Override
    public void onDisable() {
        tpData.save();
        AbstractSpigotYamlFile.saveAll();

        // Plugin shutdown logic
    }

    private void setListener(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
