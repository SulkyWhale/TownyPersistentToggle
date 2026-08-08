package io.github.sulkywhale.townypersistenttoggle.listeners;

import com.palmergames.bukkit.towny.exceptions.TownyException;
import io.github.sulkywhale.townypersistenttoggle.TownyPersistentToggle;
import io.github.sulkywhale.townypersistenttoggle.metadata.MetadataController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        try {
            MetadataController.save(player);
        } catch (TownyException e) {
            TownyPersistentToggle.getPlugin().getLogger().warning("Failed to save data for player " + player.getName() + " with error: " + e.getMessage());
        }
    }
}
