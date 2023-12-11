package fr.cylian91.ccarpet.mixin;

import carpet.CarpetServer;
import carpet.logging.LoggerRegistry;
import carpet.utils.Messenger;
import fr.cylian91.ccarpet.Logger.RegisterLogger;
import net.minecraft.text.Text;
import net.minecraft.world.block.ChainRestrictedNeighborUpdater;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mixin(ChainRestrictedNeighborUpdater.class)
public class updatesthingtomixin {
    @Shadow @Final private static Logger LOGGER;
    @Unique
    private String lastText="";

    @Inject(at = @At("HEAD"),method = "replaceWithStateForNeighborUpdate")
    private void StateReplacementEntry(CallbackInfo info){send("replaceWithStateForNeighborUpdate");}
    @Inject(at = @At("HEAD"),method = "updateNeighbor(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;Lnet/minecraft/util/math/BlockPos;)V")
    private void SimpleEntry(CallbackInfo info){send("SimpleEntry");}
    @Inject(at = @At("HEAD"),method = "updateNeighbor(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;Lnet/minecraft/util/math/BlockPos;Z)V")
    private void StatefulEntry(CallbackInfo info){send("StatefulEntry");}
    @Inject(at = @At("HEAD"),method = "updateNeighbors")
    private void SixWayEntry(CallbackInfo info){send("SixWayEntry");}
    @Unique
    private void send(String text){
        if (!RegisterLogger.__UpdateType) return;
        if (!lastText.equals(text))
            LoggerRegistry.getLogger("UpdateType").log(() ->{
                List<Text> mess= new ArrayList<Text>();
                mess.add(Messenger.c("g Entry type:","r "+text));
                return mess.toArray(new Text[0]);
            });
        lastText=text;
    }
}
