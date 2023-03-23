package nyth.prae.awesome.plugins.prag;

import nyth.prae.awesome.plugins.prag.enums.GameType;
import nyth.prae.awesome.plugins.prag.enums.Role;
import nyth.prae.awesome.plugins.prag.enums.TaggerDamageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
     * Gets all the player UUIDs that can exclude taggers and dead players
     * @param excludeTaggers Whether to exclude taggers from the list
     * @param excludeDead Whether to exclude dead players from the list
     * @return A list of all online players' UUIDs
     */
    public static List<UUID> getAllPlayerUUIDs(boolean excludeTaggers, boolean excludeDead) {
        List<UUID> uuids = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (excludeDead && getRole(player) == Role.SPECTATOR) {
                continue;
            }

            if (excludeTaggers && getRole(player) == Role.TAGGER) {
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
     * @return Whether the tagger has won
     */
    public static boolean checkForTaggerWin() {
        List<UUID> uuids = getAllPlayerUUIDs(true, true);
        if (Prag.config.getString("Tag-Type") != null) {
            switch (Objects.requireNonNull(Prag.config.getString("Tag-Type"))) {
                case "FREEZE":
                    return uuids.size() == getTeamFromName(Objects.requireNonNull(Bukkit.getPlayer(uuids.get(0))), Role.FROZEN.name()).getSize();
                case "INFECTION":
                case "ELIMINATION":
                    return uuids.size() == 0;
            }
        }
        return false;
    }

    /**
     * Sets up the scoreboard for the name display
     * @param player The player to set up the scoreboard for
     */
    public static void setupCustomNameDisplay(Player player) {

        for (Role role : Role.values()) {
            Team team = player.getScoreboard().registerNewTeam(role.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', role.getName()+" "));
        }

    }

    /**
     * Gets the team from the player
     * @param scoreHolder The player to get the scoreboard from
     * @param player The player to get the team of
     * @return The team of the player
     */
    public static Team getTeamFromPlayer(Player scoreHolder, Player player) {
        return scoreHolder.getScoreboard().getEntryTeam(player.getName());
    }

    /**
     * Gets the team from the player
     * @param player The player to get the team of
     * @return The team of the player
     */
    public static Team getTeamFromPlayer(Player player) {
        return Prag.PRAG_SCOREBOARD.getEntryTeam(player.getName());
    }

    /**
     * Gets the team from the name
     * @param scoreHolder The player to get the scoreboard from
     * @param name The name of the team
     * @return The team of the player
     */
    public static Team getTeamFromName(Player scoreHolder, String name) {
        return scoreHolder.getScoreboard().getTeam(name);
    }

    /**
     * Gets the team from the name
     * @param name The name of the team
     * @return The team of the player
     */
    public static Team getTeamFromName(String name) {
        return Prag.PRAG_SCOREBOARD.getTeam(name);
    }

    /**
     * Gets the player's role
     * @param player The player to get the role of
     * @return The player's role
     */
    public static Role getRole(Player player) {
        for (Role role : Role.values()) {
            if (getTeamFromPlayer(player, player).getName().equalsIgnoreCase(role.name())) {
                return role;
            }
        }
        return null;
    }

    /**
     * Sets the player's role
     * @param player The player to set the role for
     * @param role The role to set the player to
     */
    public static void setRole(Player player, Role role) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            getTeamFromName(p, role.name()).addEntry(player.getName());
        }

        if (role.equals(Role.SPECTATOR)) {
            player.setGameMode(GameMode.SPECTATOR);
        }

    }

    /**
     * Removes the player's role
     * @param player The player to remove the role from
     */
    public static void removeRole(Player player) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            getTeamFromPlayer(p, player).removeEntry(player.getName());
        }

    }

    /**
     * Ends the game
     * @param taggersWin Whether the taggers won
     */
    public static void endGame(boolean taggersWin) {
        if (taggersWin) {
            for (Player player : Bukkit.getOnlinePlayers()) {

                player.sendTitle(ChatColor.RED+ "The taggers have won!", "", 10, 70, 20);

            }
            announceMessage(ChatColor.RED+ "The taggers have won!");
            Prag.gamePeriod.cancel();
            Prag.preparationPeriod.cancel();
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {

                player.sendTitle(ChatColor.GREEN+ "The runners have won!", "", 10, 70, 20);

            }
            announceMessage(ChatColor.GREEN+ "The runners have won!");
        }
    }

}
