package xyz.chlamydomonos.underground;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.LightType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber
public class PlayerHandler
{
    public static final HashMap<ServerPlayerEntity, Integer> playerTimes = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(event.player instanceof ServerPlayerEntity && DataLoader.DATA.isEnabled())
        {
            if(playerTimes.containsKey(event.player))
            {
                int lightLevel = event.player.getEntityWorld().getLightFor(LightType.SKY, event.player.getPosition());
                if(lightLevel > 0 && !event.player.isCreative() && !event.player.isSpectator())
                {
                    int time = playerTimes.get(event.player);
                    if(time > DataLoader.DATA.getTime() * 20 || time < 0)
                        playerTimes.replace((ServerPlayerEntity) event.player, DataLoader.DATA.getTime() * 20);
                    else
                        playerTimes.replace((ServerPlayerEntity) event.player, time - 1);
                    if(time % 20 == 0)
                        event.player.sendMessage(
                                new TranslationTextComponent("underground.warn1")
                                        .appendString(" ")
                                        .appendString(Integer.toString(time / 20))
                                        .appendString(" ")
                                        .append(new TranslationTextComponent("underground.warn2")),
                                UUID.randomUUID()
                        );
                    if(time == 0)
                        event.player.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
                }
                else
                    playerTimes.replace((ServerPlayerEntity) event.player, 0x40000000);
            }
            else
                playerTimes.put((ServerPlayerEntity) event.player, 0x40000000);
        }
    }
}
