package me.guruguru19.baseplugin.file.basedata;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SerializableBase implements Serializable {
    private List<SerializableChunk> chunkList;
    private UUID owner;

    public SerializableBase(UUID owner, List<Chunk> chunks){
        this.owner = owner;
        this.chunkList = new ArrayList<>();
        for (Chunk chunk: chunks) {
            chunkList.add(new SerializableChunk(chunk));
        };
    }
    public boolean isInBase(Chunk chunk){
        for (SerializableChunk sc: chunkList) {
            if (sc.isSame(chunk)){
                return true;
            }
        }
        return false;
    }

    public UUID getOwner() {
        return this.owner;
    }

    @Override
    public String toString() {
        String msg = Bukkit.getOfflinePlayer(owner).getName() + "'s base: \n";
        int c = 1;
        for (SerializableChunk chunk: chunkList) {
            msg += (c++) + ". " +chunk.toString()+"\n";
        }
        return msg;
    }
}
