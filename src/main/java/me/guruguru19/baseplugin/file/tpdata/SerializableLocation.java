package me.guruguru19.baseplugin.file.tpdata;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.Serializable;

public class SerializableLocation implements Serializable {
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;
    private String world;

    public SerializableLocation(Location location){
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
        this.world = location.getWorld().getName();
    }



    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getPitch() {
        return pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public String getWorld() {
        return world;
    }

    public Location toLocation(){
        return new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z, this.yaw, this.pitch);
    }

    @Override
    public String toString() {
        return "at" +
                "x=" + ((int)x) +
                ", y=" + ((int)y) +
                ", z=" + ((int)z) +
                " ("+ world +")";
    }
}
