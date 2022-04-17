package me.guruguru19.file.tpdata;

import me.guruguru19.file.SerializationfileHelper;
import org.bukkit.entity.Pose;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class tpDataClass{

    private HashMap<UUID, HashMap<String,Pose>> tpData;

    private SerializationfileHelper tpDataFile;

    public tpDataClass() {
        tpDataFile = new SerializationfileHelper("tpData", new File("/data"));
        tpData = new HashMap<>();

    }

    private boolean addNewMap(UUID uuid){
        if (!tpData.containsKey(uuid)){
            tpData.put(uuid, new HashMap<String,Pose>());
            return true;
        }
        return false;
    }

    public HashMap<String, Pose> getTpDataOfUUID(UUID uuid) {
        addNewMap(uuid);

        return tpData.get(uuid);
    }

    public void save(){
        tpDataFile.serializeData(tpData);
    }
}
