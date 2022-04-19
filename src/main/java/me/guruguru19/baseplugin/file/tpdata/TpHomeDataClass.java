package me.guruguru19.baseplugin.file.tpdata;

import me.border.utilities.scheduler.AsyncTasker;
import me.guruguru19.baseplugin.BasePlugin;
import me.guruguru19.baseplugin.file.AbstractSerializedFile;
import org.bukkit.Location;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class TpHomeDataClass extends AbstractSerializedFile<HashMap<UUID, SerializableLocation>> {



    public TpHomeDataClass(){
        super("tp_home_data", new File(BasePlugin.instance.getDataFolder()+File.separator +"/DATA"), new HashMap<>());
        setup();
    }
    public boolean setHome(UUID uuid, SerializableLocation location){
        if (getItem().containsKey(uuid)){
            getItem().replace(uuid, location);
        }
        else{
            getItem().put(uuid, location);
        }
        AsyncTasker.runTaskAsync(new Thread(() -> save()));
        return true;
    }

    public boolean setHome(UUID uuid, Location location){
        SerializableLocation serializableLocation = new SerializableLocation(location);
        return setHome(uuid, serializableLocation);
    }

    public SerializableLocation getHome(UUID uuid){
        return getItem().get(uuid);
    }
}
