package fr.cylian91.ccarpet.mixin;

import carpet.CarpetServer;
import fr.cylian91.ccarpet.settings;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.block.ChainRestrictedNeighborUpdater;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChainRestrictedNeighborUpdater.class)
public class ExampleMixin {
	@Shadow private int depth;
	@Shadow @Final private static Logger LOGGER;
	@Unique
	private static int lastdepth=0;
	@Inject(at = @At("TAIL"), method = "enqueue")
	private void init(CallbackInfo ci) {
		lastdepth++;
		if (lastdepth>1000000){
			LOGGER.info(String.valueOf(depth));
			lastdepth=0;
		}
		//if (settings.LogUpdateInteration>0 && lastdepth<depth) {
		//	OverlayMessageS2CPacket mess = new OverlayMessageS2CPacket(Text.of("Count: " + depth));
		//	CarpetServer.minecraft_server.getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> serverPlayerEntity.networkHandler.sendPacket(mess));
		//	lastdepth = depth;
		//} else if (depth==settings.UpdateInterationValue) {
		//	if (lastdepth>1 && settings.LogUpdateInteration==2) CarpetServer.minecraft_server.getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> serverPlayerEntity.sendMessage(Text.of(lastdepth+" update")));
		//	lastdepth=settings.UpdateInterationValue;
		//}
	}
}