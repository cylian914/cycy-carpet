package fr.cylian91.ccarpet.Logger;

import net.minecraft.text.Text;

public interface HUD {
    static Text[] onUpdateHUD() {
        return new Text[0];
    }
}
