package xyz.chlamydomonos.underground.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import xyz.chlamydomonos.underground.DataLoader;

public class SetEnabled implements Command<CommandSource>
{
    public static final SetEnabled INSTANCE = new SetEnabled();
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException
    {
        boolean value = context.getArgument("value", Boolean.class);
        DataLoader.DATA.setEnabled(value);
        context.getSource().sendFeedback(new StringTextComponent("Enabled set to " + value), false);
        return 0;
    }
}
