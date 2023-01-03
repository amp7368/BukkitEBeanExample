package org.example.ebean.listener;

import io.ebean.DB;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.example.ebean.BukkitEBeanPlugin;
import org.example.ebean.database.DPlayerClick;

public class PlayerClickListener implements Listener {

    public PlayerClickListener() {
        Bukkit.getPluginManager().registerEvents(this, BukkitEBeanPlugin.get());
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        String playerName = event.getPlayer().getName();
        Action action = event.getAction();
        BukkitEBeanPlugin.get().getLogger().info(playerName + " clicked: " + action);
        DPlayerClick dbEntity = new DPlayerClick(playerName, action);
        DB.save(dbEntity);
    }
}
