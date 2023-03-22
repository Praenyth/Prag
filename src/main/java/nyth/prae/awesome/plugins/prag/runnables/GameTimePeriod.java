package nyth.prae.awesome.plugins.prag.runnables;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import nyth.prae.awesome.plugins.prag.enums.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimePeriod extends BukkitRunnable {

    private int timeLeft;

    public void start() {
        timeLeft = Prag.config.getInt("Game-Time");
        runTaskTimer(Prag.INSTANCE, 0, 20);
    }
    public void cancel() {
        Prag.gameState = GameState.LOBBY;
        Prag.taggers.clear();
        super.cancel();
    }
    @Override
    public void run() {
        timeLeft--;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED+Util.displayTimer(timeLeft)));
            if (Prag.taggers.contains(player.getUniqueId())) {
                player.setDisplayName(ChatColor.RED + player.getName());
            } else {
                player.setDisplayName(ChatColor.GREEN + player.getName());
            }
        }

        if (timeLeft <= 0) {
            cancel();
        }
    }
}
