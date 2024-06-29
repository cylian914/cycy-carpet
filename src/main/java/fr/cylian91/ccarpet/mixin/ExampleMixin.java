package fr.cylian91.ccarpet.mixin;

import carpet.CarpetServer;
import carpet.network.CarpetClient;
import fr.cylian91.ccarpet.settings;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.block.ChainRestrictedNeighborUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChainRestrictedNeighborUpdater.class)
public class ExampleMixin {
	@Unique Logger LOGGER = LoggerFactory.getLogger("Depth");
	@Shadow private int depth;
	@Unique
	private static int lastdepth=0;
	@Inject(at = @At("HEAD"), method = "enqueue",cancellable = true)
	private void setDepthValue(CallbackInfo ci){
		if (settings.DisableCollector) ci.cancel();
	}
	@Inject(at = @At("TAIL"), method = "enqueue")
	private void init(CallbackInfo ci) {
		if (settings.LogUpdateInteration > 0) {
			if (depth >= lastdepth) {
				lastdepth = depth;
			} else {
				OverlayMessageS2CPacket mess = new OverlayMessageS2CPacket(Text.of("Count: " + lastdepth));
				CarpetServer.minecraft_server.getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> serverPlayerEntity.networkHandler.sendPacket(mess));
				if (settings.LogUpdateInteration>1) {
					CarpetClient.getPlayer().sendMessage(Text.of(String.valueOf(lastdepth)));
				}
				lastdepth = 0;
			}
		} else if (depth==settings.UpdateInterationValue) {
			if (lastdepth>1 && settings.LogUpdateInteration==2) CarpetServer.minecraft_server.getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> serverPlayerEntity.sendMessage(Text.of(lastdepth+" update")));
		}
	}
}