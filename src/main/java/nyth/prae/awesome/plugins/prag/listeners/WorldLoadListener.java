package nyth.prae.awesome.plugins.prag.listeners;

import nyth.prae.awesome.plugins.prag.Prag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class WorldLoadListener implements Listener {

    // scuffed way to do this, but it works (hopefully)
    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {

        World world = event.getWorld();
        if (!(world.getName().contains("nether") || world.getName().contains("end"))) {
            Prag.PRAG_SCOREBOARD = Bukkit.getScoreboardManager().getNewScoreboard();
        }

    }

}
