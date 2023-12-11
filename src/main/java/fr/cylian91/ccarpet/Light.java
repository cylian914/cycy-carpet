package fr.cylian91.ccarpet;

import carpet.CarpetServer;
import com.google.common.collect.Maps;
import fr.cylian91.ccarpet.mixin.ServerLightingProvider_accessor;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Light {
    private static int getCapacity(ObjectList list){
        try {
            Field nField = ObjectList.class.getDeclaredField("n");
            nField.setAccessible(true);
            return nField.getInt(list);
        } catch (Throwable var1){
            throw new AssertionError(var1);
        }
    }
    public static Map<String,int[]> getInfo(){
        Map<String,int[]> toReturn = Maps.newHashMap();
        Iterator<ServerWorld> worldIterator = CarpetServer.minecraft_server.getWorlds().iterator();
        while (worldIterator.hasNext()){
            World world = worldIterator.next();
            ObjectList LightEvent = ((ServerLightingProvider_accessor)world.getLightingProvider()).getPendingTasks();
            int size = LightEvent.size();
            int capacity = getCapacity(LightEvent);
            toReturn.put(world.getDimension().toString(), new int[]{size,capacity});
        }
        return toReturn;
    }
}
