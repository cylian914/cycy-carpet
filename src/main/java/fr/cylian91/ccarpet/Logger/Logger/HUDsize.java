package fr.cylian91.ccarpet.Logger.Logger;

import carpet.logging.LoggerRegistry;
import fr.cylian91.ccarpet.ExampleMod;
import fr.cylian91.ccarpet.Logger.HUD;
import fr.cylian91.ccarpet.Logger.RegisterLogger;
import fr.cylian91.ccarpet.mixin.DisableMaxMixin;
import fr.cylian91.ccarpet.mixin.ExampleMixin;
import net.minecraft.text.Text;
import carpet.utils.Messenger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HUDsize implements HUD {
    public static void onUpdateHUD() {
        if (RegisterLogger.__UpdateQueue) {
            LoggerRegistry.getLogger("UpdateQueue").log(() -> {
                List<Text> mess = new ArrayList<Text>();
                mess.add(Messenger.c("/g" + "Global size: ","r"+ (ExampleMod.Pending.size() +
                        ExampleMod.queue.size())));
                mess.add(Messenger.c("/g" + "Pending size: ","r"+ExampleMod.Pending.size()));
                mess.add(Messenger.c("/g" + "Queue size: ","r "+ExampleMod.queue.size()));
                ExampleMod.LOGGER.warn(mess.toString());
                ExampleMod.LOGGER.warn(Arrays.toString(mess.toArray(new Text[0])));
                return mess.toArray(new Text[0]);
            });

        }
    }
}
