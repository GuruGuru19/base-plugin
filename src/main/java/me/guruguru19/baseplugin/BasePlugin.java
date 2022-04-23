package me.guruguru19.baseplugin;

import me.guruguru19.baseplugin.Commands.BaseCommands;
import me.guruguru19.baseplugin.Commands.TpListCommands;
import me.guruguru19.baseplugin.Commands.TpHomeCommands;
import me.guruguru19.baseplugin.file.basedata.BaseDataClass;
import me.guruguru19.baseplugin.file.tpdata.TpHomeDataClass;
import me.guruguru19.baseplugin.file.tpdata.TpListDataClass;
import me.guruguru19.baseplugin.listeners.BaseEvents;
import me.guruguru19.baseplugin.listeners.EventClass;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasePlugin extends JavaPlugin {

    public static TpHomeDataClass tpHomeData;
    public static TpListDataClass tpListData;
    public static BaseDataClass baseDataClass;

    public static BasePlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        tpHomeData = new TpHomeDataClass();
        tpListData = new TpListDataClass();
        baseDataClass = new BaseDataClass();

        setListener(new EventClass());
        setListener(new BaseEvents());

        getCommand("tphome").setExecutor(new TpHomeCommands());
        getCommand("tplist").setExecutor(new TpListCommands());
        getCommand("base").setExecutor(new BaseCommands());

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"\nBasePlugin Loaded\n");

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
