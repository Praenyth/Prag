package nyth.prae.awesome.plugins.prag;

import nyth.prae.awesome.plugins.prag.enums.Roles;
import nyth.prae.awesome.plugins.prag.enums.TaggerDamageType;
import nyth.prae.awesome.plugins.prag.enums.GameType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Util {

    public static void setAndSaveConfig(String s, Object v) {

        Prag.INSTANCE.getConfig().set(s, v);
        Prag.settingsCache = new SettingsCache();
        Prag.settingsCache.adventureMode = Prag.INSTANCE.getConfig().getBoolean("Adventure-Mode");
        Prag.settingsCache.gameTime = Prag.INSTANCE.getConfig().getInt("Game-Time");
        Prag.settingsCache.taggerDamage = Prag.INSTANCE.getConfig().getDouble("Tagger-Damage");
        Prag.settingsCache.taggerDamageType = TaggerDamageType.valueOf(Prag.INSTANCE.getConfig().getString("Tagger-Damage-Type"));
        Prag.settingsCache.preparationTime = Prag.INSTANCE.getConfig().getInt("Preparation-Time");
        Prag.settingsCache.tagType = GameType.valueOf(Prag.INSTANCE.getConfig().getString("Tag-Type"));
        Prag.settingsCache.amountOfTaggers = Prag.INSTANCE.getConfig().getInt("Amount-Of-Taggers");
        Prag.INSTANCE.saveConfig();
        Prag.INSTANCE.reloadConfig();
    }

    /**
     * Displays a timer in the format of HH:MM:SS
     * @param sec The amount of seconds to convert and display
     * @return A string in the format of HH:MM:SS
     */
    public static String displayTimer(int sec) {
        int hours = (int) TimeUnit.SECONDS.toHours(sec);
        int minutes = (int) (TimeUnit.SECONDS.toMinutes(sec) - TimeUnit.HOURS.toMinutes(hours));
        int seconds = (int) (TimeUnit.SECONDS.toSeconds(sec) - TimeUnit.MINUTES.toSeconds(minutes));
        if (seconds < 0) {
            seconds = 0;
        }
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Gets all of the player UUIDs that can exclude taggers and dead players
     * @param excludeTaggers Whether or not to exclude taggers from the list
     * @param excludeDead Whether or not to exclude dead players from the list
     * @return A list of all online players' UUIDs
     */
    public static List<UUID> getAllPlayerUUIDs(boolean excludeTaggers, boolean excludeDead) {
        List<UUID> uuids = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (excludeDead && (player.getGameMode() == GameMode.SPECTATOR && player.getGameMode() == GameMode.CREATIVE)) {
                continue;
            }

            if (excludeTaggers && Prag.taggers.contains(player.getUniqueId())) {
                continue;
            }

            uuids.add(player.getUniqueId());
        }
        return uuids;
    }

    /**
     * Gets a random player's UUID
     * @return A random player's UUID
     */
    public static UUID getRandomPlayerUUID() {
        List<UUID> uuids = getAllPlayerUUIDs(true, true);
        return uuids.get((int) (Math.random() * uuids.size()));
    }

    /**
     * Announces a message to all players
     * @param message The message to send to all online players
     */
    public static void announceMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    /**
     * Checks if the tagger has won
     * @return Whether or not the tagger has won
     */
    public static boolean checkForTaggerWin() {
        List<UUID> uuids = getAllPlayerUUIDs(true, true);
        switch (Prag.config.getString("Tag-Type")) {
            case "FREEZE":
                return uuids.size() == Prag.frozenPlayers.size();
            case "INFECTION":
            case "ELIMINATION":
                return uuids.size() == 0;
        }
        return false;
    }

    /**
     * Sets up the scoreboard for the nametag display
     * @param player The player to set up the scoreboard for
     */
    public static void setupNametagDisplay(Player player) {

        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for (Roles role : Roles.values()) {
            Team team = player.getScoreboard().registerNewTeam(role.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', role.getName()+" "));
        }

    }

    /**
     * Sets the player's role
     * @param player The player to set the role for
     * @param role The role to set the player to
     */
    public static void setRole(Player player, Roles role) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getScoreboard().getTeam(role.name()).addEntry(player.getName());
        }

    }

    /**
     * Removes the player's role
     * @param player The player to remove the role from
     * @param role The role to remove from the player
     */
    public static void removeRole(Player player) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }

    }

}
