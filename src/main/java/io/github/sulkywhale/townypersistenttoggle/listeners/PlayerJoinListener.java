package io.github.sulkywhale.townypersistenttoggle.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.huds.HUDManager;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.resident.mode.ResidentModeHandler;
import io.github.sulkywhale.townypersistenttoggle.metadata.MetadataController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Resident resident = TownyAPI.getInstance().getResident(player);
        List<String> modes = MetadataController.getModesData(resident);
        String hud = MetadataController.getHUDsData(resident);
        for (String mode : modes) {
            try {
                ResidentModeHandler.toggleMode(resident, mode, false);
            } catch (TownyException e) {
                event.getPlayer().sendMessage(e.getMessage());
            }
        }
        if (hud.equalsIgnoreCase("maphud")) {
            HUDManager.toggleMapHud(player);
        } else if (hud.equalsIgnoreCase("permhud")) {
            HUDManager.togglePermHUD(player);
        }
    }
}
