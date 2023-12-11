package fr.cylian91.ccarpet.mixin;

import fr.cylian91.ccarpet.settings;
import net.minecraft.world.block.ChainRestrictedNeighborUpdater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(ChainRestrictedNeighborUpdater.class)
public class UpdateInteractionValue {

    @ModifyConstant(method = "runQueuedUpdates",constant = @Constant(intValue = 0))
    private int ChangeConstUpdate(int constant){
        return settings.UpdateInterationValue;
    }
    @ModifyConstant(method = "enqueue",constant = @Constant(intValue = 0))
    private int ChnageConstbl(int bl){
        return settings.UpdateInterationValue;
    }
}
