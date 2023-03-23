package nyth.prae.awesome.plugins.prag.commands.subcommands;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import nyth.prae.awesome.plugins.prag.enums.GameState;
import nyth.prae.awesome.plugins.prag.enums.Role;
import nyth.prae.awesome.plugins.prag.runnables.PreparationTimePeriod;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PragStartCommand {

    @CommandMethod("prag start")
    @CommandDescription("Starts the game!")
    @CommandPermission("prag.admin.start")
    public void start(CommandContext<CommandSender> context) {
        if (Prag.gameState != GameState.INGAME) {
            context.getSender().sendMessage(ChatColor.RED+"Game is already in progress!");
            return;
        }
        context.getSender().sendMessage("Game started with type: "+ Prag.config.get("Tag-Type"));

        Prag.gameState = GameState.LOBBY;

        for (Player player:
                Bukkit.getOnlinePlayers()) {
            Util.setRole(player, Role.RUNNER);
        }

        Prag.preparationPeriod = new PreparationTimePeriod();
        Prag.preparationPeriod.start();
    }

}
