package nyth.prae.awesome.plugins.prag.listeners;

import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        player.setScoreboard(Prag.PRAG_SCOREBOARD);
        Util.setupNametagDisplay(player);

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        Util.removeRole(player);

    }

}
