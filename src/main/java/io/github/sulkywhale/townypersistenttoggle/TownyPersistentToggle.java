package io.github.sulkywhale.townypersistenttoggle;

import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.metadata.MetadataLoader;
import io.github.sulkywhale.townypersistenttoggle.listeners.PlayerJoinListener;
import io.github.sulkywhale.townypersistenttoggle.listeners.PlayerQuitListener;
import io.github.sulkywhale.townypersistenttoggle.metadata.MetadataController;
import io.github.sulkywhale.townypersistenttoggle.metadata.StringListDFDeserializer;
import io.github.sulkywhale.townypersistenttoggle.metadata.StringListDataField;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TownyPersistentToggle extends JavaPlugin {

    private static TownyPersistentToggle plugin;

    public TownyPersistentToggle() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        MetadataLoader.getInstance().registerDeserializer(StringListDataField.typeID(), new StringListDFDeserializer());
        getLogger().info("Plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            try {
                MetadataController.save(player);
            } catch (TownyException e) {
                getLogger().warning("Failed to save data for player " + player.getName() + " with error: " + e.getMessage());
            }
        }

        getLogger().info("Plugin has been disabled.");
    }

    public static TownyPersistentToggle getPlugin() {
        return plugin;
    }
}
