package fr.cylian91.ccarpet.Logger;

import carpet.CarpetServer;
import carpet.logging.HUDLogger;
import carpet.logging.Logger;
import carpet.utils.Messenger;
import fr.cylian91.ccarpet.ExampleMod;
import carpet.logging.HUDController;
import fr.cylian91.ccarpet.Logger.Logger.HUDdepth;
import fr.cylian91.ccarpet.Logger.Logger.HUDsize;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static carpet.logging.LoggerRegistry.registerLogger;

public class RegisterLogger {
    public static boolean __UpdateQueue;
    public static boolean __UpdateType;
    public static boolean __depth;
    public static void register(){
        registerLogger("UpdateQueue",makeHUDLogger("UpdateQueue",null,null,false));
        registerLogger("UpdateType",makeLogger("UpdateType",null,null,false));
        registerLogger("depth",makeHUDLogger("depth",null,null,false));
        HUDController.register(RegisterLogger::UpdateHud);
    }
    public static Logger makeLogger(String logName, String def, String[] options, boolean strictOptions) {
        try {
            return new Logger(RegisterLogger.class.getField("__" + logName), logName, def, options, strictOptions);
        }catch (NoSuchFieldException e) {throw new RuntimeException("Failed to create logger "+logName);}

    }
    public static HUDLogger makeHUDLogger(String logName, String def, String[] options, boolean strictOptions) {
        try {
            return new HUDLogger(RegisterLogger.class.getField("__" + logName), logName, def, options, strictOptions);
        }catch (NoSuchFieldException e) {throw new RuntimeException("Failed to create logger "+logName);}
    }
    public static void UpdateHud(MinecraftServer server){
        HUDsize.onUpdateHUD();
        HUDdepth.onUpdateHUD();
    }
    public static void forceSendHud(){
        //this is not ideal...
        HUDdepth.onUpdateHUD();
        HUDController.update_hud(CarpetServer.minecraft_server,CarpetServer.minecraft_server.getPlayerManager().getPlayerList());
    }
}
