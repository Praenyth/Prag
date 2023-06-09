package nyth.prae.awesome.plugins.prag.runnables;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import nyth.prae.awesome.plugins.prag.enums.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PreparationTimePeriod extends BukkitRunnable {

    private int timeLeft;

    public void start() {
        timeLeft = Prag.settingsCache.preparationTime;
        runTaskTimer(Prag.INSTANCE, 0, 20);
    }
    public void cancel() { super.cancel(); }
    @Override
    public void run() {
        timeLeft--;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN+ Util.displayTimer(timeLeft)));
        }

        if (timeLeft <= 0) {

            while (Util.getTeamFromName(Role.TAGGER.name()).getSize() < Prag.settingsCache.amountOfTaggers) {
                Player player = Bukkit.getPlayer(Util.getRandomPlayerUUID());
                assert player != null;
                Util.announceMessage(ChatColor.RED + player.getName() + " is a tagger!");
                Util.setRole(player, Role.TAGGER);
            }

            cancel();
            Prag.gamePeriod = new GameTimePeriod();
            Prag.gamePeriod.start();

        }
    }
}
