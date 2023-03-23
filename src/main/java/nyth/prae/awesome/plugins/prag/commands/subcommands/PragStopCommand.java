package nyth.prae.awesome.plugins.prag.commands.subcommands;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.context.CommandContext;
import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import nyth.prae.awesome.plugins.prag.enums.GameState;
import nyth.prae.awesome.plugins.prag.enums.Role;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PragStopCommand {

    @CommandMethod("prag stop")
    @CommandDescription("Stops the game!")
    @CommandPermission("prag.admin.stop")
    public void stop(CommandContext<CommandSender> context) {
        context.getSender().sendMessage("Game was forcibly stopped!");
        Prag.gameState = GameState.LOBBY;
        for (Player player : Bukkit.getOnlinePlayers()) {
            Util.setRole(player, Role.RUNNER);
        }

        if (Prag.preparationPeriod != null) {
            Prag.preparationPeriod.cancel();
        }

        if (Prag.gamePeriod != null) {
            Prag.gamePeriod.cancel();
        }
    }

}
