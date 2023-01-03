package org.example.ebean.listener;

import io.ebean.DB;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.example.ebean.BukkitEBeanPlugin;
import org.example.ebean.database.DPlayerClick;
import org.example.ebean.database.FindByQueryBean;

public class PlayerClickListener implements Listener {

    public PlayerClickListener() {
        Bukkit.getPluginManager().registerEvents(this, BukkitEBeanPlugin.get());
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        String playerName = event.getPlayer().getName();
        Action action = event.getAction();

        saveClick(playerName, action);

        DPlayerClick lastClick = FindByQueryBean.lastClick(playerName);
        event.getPlayer().sendMessage(lastClick.toString());
    }

    private static void saveClick(String playerName, Action action) {
        Logger logger = BukkitEBeanPlugin.get().getLogger();

        // save the click to the database
        DPlayerClick click = new DPlayerClick(playerName, action);
        logger.info(String.format("Saving now! - %s", click));
        DB.save(click);

        // find the click that was just saved
        click = DB.find(DPlayerClick.class, click.getId());
        if (click == null) {
            logger.severe("Could not find the just saved click!");
        } else {
            logger.info(String.format("Successfully saved click! - %s", click));
        }
    }

}
