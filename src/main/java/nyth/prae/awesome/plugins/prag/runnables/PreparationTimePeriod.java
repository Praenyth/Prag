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

            List<UUID> tempPlayerList = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                tempPlayerList.add(player.getUniqueId());
            }
            while (Prag.taggers.size() < Prag.config.getInt("Amount-Of-Taggers")) {
                int random = new Random().nextInt(tempPlayerList.size());
                UUID randomUUID = tempPlayerList.get(random);

                if (Bukkit.getPlayer(randomUUID).getGameMode() != GameMode.SURVIVAL || Bukkit.getPlayer(randomUUID).getGameMode() != GameMode.ADVENTURE) {
                    tempPlayerList.remove(random);
                } else {
                    Prag.taggers.add(randomUUID);
                }
            }

            cancel();
            new GameTimePeriod().start();

        }
    }
}
