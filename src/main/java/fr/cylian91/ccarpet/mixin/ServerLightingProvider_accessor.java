package fr.cylian91.ccarpet.mixin;

import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.server.world.ServerLightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(ServerLightingProvider.class)
public interface ServerLightingProvider_accessor {
    @Accessor("pendingTasks")
    ObjectList getPendingTasks();

}

