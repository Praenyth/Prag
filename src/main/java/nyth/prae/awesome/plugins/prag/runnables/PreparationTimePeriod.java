package nyth.prae.awesome.plugins.prag.runnables;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PreparationTimePeriod extends BukkitRunnable {

    private int timeLeft;

    public void start() {
        timeLeft = Prag.config.getInt("Preparation-Time");
        runTaskTimer(Prag.instance, 0, 20);
    }
    public void cancel() { super.cancel(); }
    @Override
    public void run() {
        timeLeft--;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN+ Util.displayTimer(timeLeft)));
        }

        if (timeLeft <= 0) {

            while (Prag.taggers.size() < Prag.config.getInt("Amount-Of-Taggers")) {
                Player player = Bukkit.getPlayer(Util.getRandomPlayerUUID());

                if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
                    Prag.taggers.add(player.getUniqueId());
                    player.setDisplayName(ChatColor.RED + player.getName());
                    Util.announceMessage(ChatColor.RED + player.getName() + " is a tagger!");

                }
            }

            cancel();
            Prag.gamePeriod = new GameTimePeriod();
            Prag.gamePeriod.start();

        }
    }
}
