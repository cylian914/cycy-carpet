package fr.cylian91.ccarpet.mixin;

import carpet.CarpetServer;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.NetworkThreadUtils;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.lang.annotation.Target;

@Mixin(NetworkThreadUtils.class)
public abstract class CrashreportMixin {
    @Unique
    private static String str;
    @Inject(at = @At(value = "INVOKE",target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"),method = "method_11072",locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void SendToChat(PacketListener packetListener, Packet packet, CallbackInfo ci, Exception var3){
        if (var3.getCause() != null) str=var3.getCause().toString();
        else str=var3.toString();
        CarpetServer.minecraft_server.getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> serverPlayerEntity.sendMessage(Text.of(str)));
    }
}
