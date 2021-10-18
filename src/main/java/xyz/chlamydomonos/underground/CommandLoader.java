package xyz.chlamydomonos.underground;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.chlamydomonos.underground.commands.GetEnabled;
import xyz.chlamydomonos.underground.commands.GetTime;
import xyz.chlamydomonos.underground.commands.SetEnabled;
import xyz.chlamydomonos.underground.commands.SetTime;

@Mod.EventBusSubscriber
public class CommandLoader
{
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event)
    {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();

        dispatcher.register(
                Commands.literal("underground")
                 .requires(commandSource -> commandSource.hasPermissionLevel(2))
                 .then(Commands.literal("enabled")
                 .executes(GetEnabled.INSTANCE)
                 .then(Commands.argument("value", BoolArgumentType.bool())
                 .executes(SetEnabled.INSTANCE)))
                 .then(Commands.literal("time")
                 .executes(GetTime.INSTANCE)
                 .then(Commands.argument("value", LongArgumentType.longArg(1, 0x3333333))
                 .executes(SetTime.INSTANCE)))
        );
    }
}
