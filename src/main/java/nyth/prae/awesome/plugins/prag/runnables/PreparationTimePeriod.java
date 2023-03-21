package nyth.prae.awesome.plugins.prag.runnables;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PreparationTimePeriod extends BukkitRunnable {

    private int timeLeft = Prag.config.getInt("Preparation-Time");

    public void start() {
        runTaskTimer(Prag.instance, 0, 20);
    }
    @Override
    public void run() {
        timeLeft--;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN+ Util.displayTimer(timeLeft)));
        }

        if (timeLeft <= 0) {
            cancel();
        }
    }
}
