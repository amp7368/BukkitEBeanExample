package org.example.ebean;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.ebean.database.DatabaseSetup;

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
    }

    public File getFile(String childFile) {
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdir();
        return new File(dataFolder, childFile);
    }
}
