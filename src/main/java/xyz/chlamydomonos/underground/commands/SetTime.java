package xyz.chlamydomonos.underground.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import xyz.chlamydomonos.underground.DataLoader;

public class SetTime implements Command<CommandSource>
{
    public static final SetTime INSTANCE = new SetTime();
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException
    {
        int value = context.getArgument("value", Integer.class);
        DataLoader.DATA.setTime(value);
        context.getSource().sendFeedback(new StringTextComponent("Time set to " + value), false);
        return 0;
    }
}
