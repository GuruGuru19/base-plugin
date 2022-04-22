package me.guruguru19.baseplugin.file.tpdata;

import me.guruguru19.baseplugin.BasePlugin;
import me.guruguru19.baseplugin.file.AbstractSerializedFile;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class TpListDataClass extends AbstractSerializedFile<HashMap<UUID, HashMap<String, SerializableLocation>>> {



    public TpListDataClass() {
        super("tp_to_data", new File(BasePlugin.instance.getDataFolder()+File.separator +"/DATA"), new HashMap<>());
        setup();
    }

    private boolean addNewMap(UUID uuid){
        if (!getItem().containsKey(uuid)){
            getItem().put(uuid, new HashMap<String, SerializableLocation>());
            return true;
        }
        return false;
    }

    public HashMap<String, SerializableLocation> getTpDataOfUUID(UUID uuid) {
        addNewMap(uuid);

        return getItem().get(uuid);
    }

}
