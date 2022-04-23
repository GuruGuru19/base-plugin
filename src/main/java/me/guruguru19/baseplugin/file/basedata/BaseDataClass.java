package me.guruguru19.baseplugin.file.basedata;

import me.guruguru19.baseplugin.BasePlugin;
import me.guruguru19.baseplugin.file.AbstractSerializedFile;
import me.guruguru19.baseplugin.file.tpdata.SerializableLocation;
import org.bukkit.Chunk;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BaseDataClass extends AbstractSerializedFile<HashMap<UUID, SerializableBase>> {


    public BaseDataClass() {
        super("base_data", new File(BasePlugin.instance.getDataFolder()+File.separator +"/DATA"), new HashMap<>());
        setup();
    }

    public void addBase(UUID uuid, List<Chunk> chunkSet){
        getItem().put(uuid, new SerializableBase(uuid, chunkSet));
        save();
    }

    public void removeBase(UUID uuid){
        getItem().remove(uuid);
        save();
    }

    public SerializableBase getBase(UUID uuid){
        return getItem().get(uuid);
    }

    public UUID claimed(Chunk chunk){
        for (Map.Entry<UUID, SerializableBase> entry: getItem().entrySet()) {
            if (entry.getValue().isInBase(chunk)){
                return entry.getKey();
            }
        }
        return null;
    }

}
