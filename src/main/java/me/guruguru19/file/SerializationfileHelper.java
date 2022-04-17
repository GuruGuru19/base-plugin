package me.guruguru19.file;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;

public class SerializationfileHelper extends AbstractSpigotYamlFile{



    public SerializationfileHelper(String file, File path) {
        super(file, path);
    }

    public boolean serializeData(Serializable serializableData) {
        try{
            FileOutputStream fileOut = new FileOutputStream(super.getFile().getPath());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(serializableData);
            out.close();
            fileOut.close();
            System.out.println("data Serialized");
            return true;
        } catch (Exception e) {
            System.out.println("data not Serialized");
            return false;
        }
    }

    public Serializable deserializeData(){
        try{
            FileInputStream fileIn = new FileInputStream(super.getFile().getPath());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Serializable data = (Serializable) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("data Deserialized");
            return data;
        } catch (Exception e) {
            System.out.println("data not Deserialized");
            return null;
        }
    }
}
