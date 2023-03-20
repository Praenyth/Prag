package nyth.prae.awesome.plugins.prag.commands.subcommands;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import org.bukkit.command.CommandSender;

public class PragTagTypeCommand {
    @CommandMethod("prag tagtype")
    @CommandDescription("Sets the tag type of the game!")
    @CommandPermission("prag.admin.tagtype")
    public void start(CommandContext<CommandSender> sender) {
        sender.getSender().sendMessage("Hello World");
    }
}
