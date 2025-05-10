package io.github.sulkywhale.townypersistenttoggle;

import com.palmergames.bukkit.towny.object.metadata.MetadataLoader;
import io.github.sulkywhale.townypersistenttoggle.listeners.PlayerJoinListener;
import io.github.sulkywhale.townypersistenttoggle.listeners.PlayerQuitListener;
import io.github.sulkywhale.townypersistenttoggle.metadata.MetadataController;
import io.github.sulkywhale.townypersistenttoggle.metadata.StringListDFDeserilizer;
import io.github.sulkywhale.townypersistenttoggle.metadata.StringListDataField;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TownyPersistentToggle extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);

        MetadataLoader.getInstance().registerDeserializer(StringListDataField.typeID(), new StringListDFDeserilizer());

        getLogger().info("Plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : Bukkit.getOnlinePlayers()) {
            MetadataController.save(player);
        }

        getLogger().info("Plugin has been disabled.");
    }
}
