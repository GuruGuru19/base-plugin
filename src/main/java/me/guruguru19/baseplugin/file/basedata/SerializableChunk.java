package me.guruguru19.baseplugin.file.basedata;

import org.bukkit.Chunk;

import java.io.Serializable;
import java.util.Objects;

public class SerializableChunk implements Serializable {
    private int x;
    private int z;
    private String world;

    public SerializableChunk(Chunk chunk) {
        this.x = chunk.getX();
        this.z = chunk.getZ();
        this.world = chunk.getWorld().getName();
    }

    public SerializableChunk(int x, int z, String world) {
        this.x = x;
        this.z = z;
        this.world = world;
    }

    public boolean isSame(Chunk chunk){
        return this.x == chunk.getX() && this.z == chunk.getZ() && Objects.equals(this.world, chunk.getWorld().getName());
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public String getWorld() {
        return world;
    }

    @Override
    public String toString() {
        return "(x: "+x+", z: "+z+", world: "+world+")";
    }
}
