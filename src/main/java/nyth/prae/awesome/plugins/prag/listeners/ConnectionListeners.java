package nyth.prae.awesome.plugins.prag.listeners;

import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import nyth.prae.awesome.plugins.prag.enums.GameState;
import nyth.prae.awesome.plugins.prag.enums.Role;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        Util.setupCustomNameDisplay(player);

        Util.setRole(player, Role.RUNNER);

        if (Prag.gameState == GameState.INGAME) {
            Util.setRole(player, Role.SPECTATOR);
        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        Util.removeRole(player);
        if (Prag.gameState == GameState.INGAME) {
            if (Util.checkForTaggerWin()) {
                Util.endGame(true);
            }
        }
    }

}
