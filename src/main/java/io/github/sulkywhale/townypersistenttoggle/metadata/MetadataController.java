package io.github.sulkywhale.townypersistenttoggle.metadata;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.huds.HUDManager;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import com.palmergames.bukkit.towny.object.metadata.StringDataField;
import com.palmergames.bukkit.towny.object.resident.mode.ResidentModeHandler;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class MetadataController {

    private static final StringListDataField modes = new StringListDataField("townypersistenttoggle_modes", Collections.emptyList(), "Modes");
    private static final StringDataField huds = new StringDataField("townypersistenttoggle_huds", "", "HUDS");

    public static void setModesData(Resident resident, List<String> newModes) {
        StringListDataField idf = (StringListDataField) modes.clone();
        if (resident.hasMeta(idf.getKey())) {
            resident.removeMetaData(idf);
        }
        if (!newModes.isEmpty()) {
            resident.addMetaData(new StringListDataField(idf.getKey(), newModes, null));
        }
    }

    public static List<String> getModesData(Resident resident) {
        StringListDataField idf = (StringListDataField) modes.clone();
        if (resident.hasMeta(idf.getKey())) {
            CustomDataField<?> cdf = resident.getMetadata(idf.getKey());
            if (cdf instanceof StringListDataField amount) {
                return amount.getValue();
            }
        }
        return Collections.emptyList();
    }

    public static void setHUDSData(Resident resident, String newHUDs) {
        StringDataField idf = (StringDataField) huds.clone();
        if (resident.hasMeta(idf.getKey())) {
            resident.removeMetaData(idf);
        }
        if (newHUDs != null) {
            resident.addMetaData(new StringDataField(idf.getKey(), newHUDs, null));
        }
    }

    public static String getHUDsData(Resident resident) {
        StringDataField idf = (StringDataField) huds.clone();
        if (resident.hasMeta(idf.getKey())) {
            CustomDataField<?> cdf = resident.getMetadata(idf.getKey());
            if (cdf instanceof StringDataField amount) {
                return amount.getValue();
            }
        }
        return "";
    }

    public static void save(Player player) {
        Resident resident = TownyAPI.getInstance().getResident(player);
        List<String> modes = ResidentModeHandler.getResidentModesNames(resident);
        boolean usingPermHUD = HUDManager.isPermHUDActive(player);
        boolean usingMapHUD = HUDManager.isMapHudActive(player);
        String hud = usingPermHUD ? "permhud" : usingMapHUD ? "maphud" : null;
        MetadataController.setModesData(resident, modes);
        MetadataController.setHUDSData(resident, hud);
    }
}
