package fr.cylian91.ccarpet.mixin.testmixin;

import fr.cylian91.ccarpet.ExampleMod;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.world.chunk.light.LightStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightStorage.class)
public class testMixin {
    @Inject(method = "Lnet/minecraft/world/chunk/light/LightStorage;hasLightUpdates()Z",at=@At("HEAD"),cancellable = true)
    private void test(CallbackInfoReturnable cir){
        cir.setReturnValue(false);
    }
}
