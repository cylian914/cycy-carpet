package fr.cylian91.ccarpet.mixin;

import fr.cylian91.ccarpet.settings;
import net.minecraft.world.block.ChainRestrictedNeighborUpdater;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(ChainRestrictedNeighborUpdater.class)
public class UpdateInteractionValue {
    @Unique private static int oldUpdateInteractionValue=settings.UpdateInterationValue;
    @Shadow private int depth;
    @Inject(at = @At("HEAD"), method = "enqueue")
    private void setDepthValue(CallbackInfo ci){
        if (oldUpdateInteractionValue!=settings.UpdateInterationValue){
            depth=settings.UpdateInterationValue;
            oldUpdateInteractionValue=settings.UpdateInterationValue;
        }
    }
    @ModifyConstant(method = "runQueuedUpdates",constant = @Constant(intValue = 0))
    private int ChangeConstUpdate(int constant){
        return settings.UpdateInterationValue;
    }
    /*@ModifyConstant(method = "enqueue",constant = @Constant(intValue = 0))
    private int ChangeConstbl(int bl){
        return settings.UpdateInterationValue;
    }*/
}
