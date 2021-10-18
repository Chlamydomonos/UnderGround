package xyz.chlamydomonos.underground;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DataLoader
{
    public static Data DATA = null;
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(event.player instanceof ServerPlayerEntity && DATA == null)
        {
            DATA = Data.get(event.player.world);
        }
    }
}
