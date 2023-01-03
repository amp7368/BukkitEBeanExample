package org.example.ebean;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.ebean.database.DatabaseSetup;
import org.example.ebean.listener.PlayerClickListener;

public class BukkitEBeanPlugin extends JavaPlugin {

    private static BukkitEBeanPlugin instance;

    public BukkitEBeanPlugin() {
        instance = this;
    }

    public static BukkitEBeanPlugin get() {
        return instance;
    }

    @Override
    public void onEnable() {
        DatabaseSetup.load();
        new PlayerClickListener();
    }

    public File getFile(String childFile) {
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdir();
        return new File(dataFolder, childFile);
    }
}
