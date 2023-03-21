package nyth.prae.awesome.plugins.prag.commands.subcommands;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import nyth.prae.awesome.plugins.prag.Prag;
import org.bukkit.command.CommandSender;

public class PragStartCommand {

    @CommandMethod("prag start")
    @CommandDescription("Starts the game!")
    @CommandPermission("prag.admin.start")
    public void start(CommandContext<CommandSender> context) {
        context.getSender().sendMessage("Game started with type: "+ Prag.config.get("Tag-Type"));
    }

}
