package io.github.sulkywhale.townypersistenttoggle.metadata;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.huds.HUDManager;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.metadata.StringDataField;
import com.palmergames.bukkit.towny.object.resident.mode.ResidentModeHandler;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class MetadataController {

    private static final StringListDataField modes = new StringListDataField("townypersistenttoggle_modes", Collections.emptyList());
    private static final StringDataField huds = new StringDataField("townypersistenttoggle_huds", "");

    public static void setModesData(Resident resident, List<String> newModes) {
        StringListDataField sdf = (StringListDataField) modes.clone();
        if (resident.hasMeta(sdf.getKey())) {
            resident.removeMetaData(sdf);
        }
        if (!newModes.isEmpty()) {
            resident.addMetaData(new StringListDataField(sdf.getKey(), newModes));
        }
    }

    public static List<String> getModesData(Resident resident) {
        StringListDataField sldf = (StringListDataField) modes.clone();
        if (resident.hasMeta(sldf.getKey())) {
            return resident.getMetadata(sldf.getKey(), StringListDataField.class).getValue();
        }
        return Collections.emptyList();
    }

    public static void setHUDSData(Resident resident, String newHUDs) {
        StringDataField sdf = (StringDataField) huds.clone();
        if (resident.hasMeta(sdf.getKey())) {
            resident.removeMetaData(sdf);
        }
        if (newHUDs != null) {
            resident.addMetaData(new StringDataField(sdf.getKey(), newHUDs));
        }
    }

    public static String getHUDsData(Resident resident) {
        StringDataField sdf = (StringDataField) huds.clone();
        if (resident.hasMeta(sdf.getKey())) {
            return resident.getMetadata(sdf.getKey(), StringDataField.class).getValue();
        }
        return "";
    }

    public static void save(Player player) throws TownyException {
        Resident resident = TownyAPI.getInstance().getResidentOrThrow(player);
        List<String> modes = ResidentModeHandler.getResidentModesNames(resident);
        boolean usingPermHUD = HUDManager.isPermHUDActive(player);
        boolean usingMapHUD = HUDManager.isMapHudActive(player);
        String hud = usingPermHUD ? "permhud" : usingMapHUD ? "maphud" : null;
        MetadataController.setModesData(resident, modes);
        MetadataController.setHUDSData(resident, hud);
    }
}
