package fr.cylian91.ccarpet.mixin;

import fr.cylian91.ccarpet.Command;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandRegister {
    @Inject(at = @At("HEAD"),method = "Lnet/minecraft/server/command/CommandManager;<init>(Lnet/minecraft/server/command/CommandManager$RegistrationEnvironment;Lnet/minecraft/command/CommandRegistryAccess;)V")
    private static void register(CommandManager.RegistrationEnvironment environment, CommandRegistryAccess commandRegistryAccess, CallbackInfo ci){
        Command.register();
    }
}
