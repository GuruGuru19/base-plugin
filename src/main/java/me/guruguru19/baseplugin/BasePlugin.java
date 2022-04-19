package me.guruguru19.baseplugin;

import me.guruguru19.baseplugin.Commands.TpCommands;
import me.guruguru19.baseplugin.Commands.TpHomeCommands;
import me.guruguru19.baseplugin.file.tpdata.TpDataClass;
import me.guruguru19.baseplugin.file.tpdata.TpHomeDataClass;
import me.guruguru19.baseplugin.listeners.EventClass;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasePlugin extends JavaPlugin {

    public static TpHomeDataClass tpHomeData;

    public static BasePlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        tpHomeData = new TpHomeDataClass();

        setListener(new EventClass());

        getCommand("tphome").setExecutor(new TpHomeCommands());
        getCommand("tpto").setExecutor(new TpCommands());

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"\nBasePlugin Loaded 123 banana\n");

        System.out.println(tpHomeData.getFile().getAbsolutePath());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setListener(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
