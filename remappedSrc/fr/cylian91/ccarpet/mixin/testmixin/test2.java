package fr.cylian91.ccarpet.mixin.testmixin;

import fr.cylian91.ccarpet.ExampleMod;
import net.minecraft.server.world.ServerLightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLightingProvider.class)
public class test2 {
    @Inject(at = @At("HEAD"),method = "Lnet/minecraft/server/world/ServerLightingProvider;tick()V",cancellable = true)
    private void test2(CallbackInfo ci){
        if (ExampleMod.cancel) ci.cancel();
    }
}
