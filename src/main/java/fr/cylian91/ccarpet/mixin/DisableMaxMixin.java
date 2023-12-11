package fr.cylian91.ccarpet.mixin;

import fr.cylian91.ccarpet.ExampleMod;
import fr.cylian91.ccarpet.Logger.Logger.HUDsize;
import fr.cylian91.ccarpet.Logger.RegisterLogger;
import fr.cylian91.ccarpet.settings;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.block.ChainRestrictedNeighborUpdater;

import java.util.ArrayDeque;
import java.util.List;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ChainRestrictedNeighborUpdater.class)
public class DisableMaxMixin {

    @Mutable @Shadow @Final private int maxChainDepth;
    @Shadow @Final private ArrayDeque queue;

    @Shadow @Final private List pending;

    @Shadow private int depth;

    @Inject(at = @At("HEAD"), method = "enqueue")
    private void cycyCarpet$setMaxChainDepth(CallbackInfo ci){
        //if (maxChainDepth!= settings.MaxUpdateChain)
            maxChainDepth=settings.MaxUpdateChain;
    }
    @Inject(method = "enqueue",at = @At(shift = At.Shift.AFTER,value = "INVOKE",target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private void cycyCarpet$exeInEnqueue0(CallbackInfo info){
        sendPending();
    }
    @Inject(method = "enqueue",at = @At(shift = At.Shift.AFTER,value = "INVOKE",target = "Ljava/util/ArrayDeque;push(Ljava/lang/Object;)V"))
    private void cycyCarpet$exeInEnqueue1(CallbackInfo info){
        sendPending();
    }
    @Inject(method = "runQueuedUpdates",at = @At(shift = At.Shift.AFTER,value = "INVOKE",target = "Ljava/util/ArrayDeque;peek()Ljava/lang/Object;"))
    private void cycyCarpet$exeInUpdatepeek(CallbackInfo info){
        sendPending();
    }
    @Inject(method = "runQueuedUpdates",at = @At(shift = At.Shift.AFTER,value = "INVOKE",target = "Ljava/util/ArrayDeque;push(Ljava/lang/Object;)V"))
    private void cycyCarpet$exeInUpdate1(CallbackInfo info){
        sendPending();
    }
    @Inject(method = "runQueuedUpdates",at = @At(shift = At.Shift.AFTER,value = "INVOKE",target = "Ljava/util/ArrayDeque;pop()Ljava/lang/Object;"))
    private void cycyCarpet$exeInUpdate2(CallbackInfo info){
        sendPending();
    }
    @Inject(method = "runQueuedUpdates",at = @At(shift = At.Shift.AFTER,value = "INVOKE",target = "Ljava/util/ArrayDeque;clear()V"))
    private void cycyCarpet$exeInUpdateClear(CallbackInfo info){
        sendPending();
    }
    @Inject(method = "runQueuedUpdates",at = @At(shift = At.Shift.AFTER,value = "INVOKE",target = "Ljava/util/List;clear()V"))
    private void cycyCarpet$exeInUpdateClear2(CallbackInfo info){
        sendPending();
    }
    @Inject(method = "enqueue",at = @At(value = "TAIL"),locals = LocalCapture.PRINT)
    private void cycyCarpet$a(BlockPos pos, ChainRestrictedNeighborUpdater.Entry entry, CallbackInfo ci, boolean bl) { //error can be ignored
        ExampleMod.depth = depth;
       //ExampleMod.bl = bl;
       //ExampleMod.bl2 = bl2;
    }
    @Unique
    private void sendPending(){
        ExampleMod.Pending = pending;
        ExampleMod.queue = queue;
        RegisterLogger.forceSendHud();
    }
}
