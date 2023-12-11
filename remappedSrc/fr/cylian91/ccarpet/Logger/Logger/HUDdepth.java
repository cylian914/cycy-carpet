package fr.cylian91.ccarpet.Logger.Logger;

import carpet.logging.LoggerRegistry;
import carpet.utils.Messenger;
import fr.cylian91.ccarpet.ExampleMod;
import fr.cylian91.ccarpet.Logger.HUD;
import fr.cylian91.ccarpet.Logger.RegisterLogger;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HUDdepth implements HUD {
    public static void onUpdateHUD() {
        if (RegisterLogger.__depth) {
            LoggerRegistry.getLogger("depth").log(() -> {
                List<Text> mess = new ArrayList<Text>();
                mess.add(Messenger.s(String.valueOf(ExampleMod.depth)));
                mess.add(Messenger.s(String.valueOf(ExampleMod.depth)));
                mess.add(Messenger.s(String.valueOf(ExampleMod.depth)));
                return mess.toArray(new Text[0]);
            });
        }
    }

}
