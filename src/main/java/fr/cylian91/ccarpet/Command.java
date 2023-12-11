package fr.cylian91.ccarpet;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

public class Command {

    public static void register(){

    }
    private int dumpworld(CommandContext<ServerCommandSource> source){
        return 0;

    }
}
