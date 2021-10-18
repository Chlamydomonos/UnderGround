package xyz.chlamydomonos.underground.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import xyz.chlamydomonos.underground.DataLoader;

public class GetEnabled implements Command<CommandSource>
{
    public static final GetEnabled INSTANCE = new GetEnabled();
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException
    {
        context.getSource().sendFeedback(new StringTextComponent(Boolean.toString(DataLoader.DATA.isEnabled())), false);
        return 0;
    }
}
