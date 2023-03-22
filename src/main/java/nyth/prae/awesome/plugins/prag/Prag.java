package nyth.prae.awesome.plugins.prag;

import nyth.prae.awesome.plugins.prag.commands.PragCommand;
import nyth.prae.awesome.plugins.prag.enums.GameState;
import nyth.prae.awesome.plugins.prag.listeners.DamageListeners;
import nyth.prae.awesome.plugins.prag.runnables.GameTimePeriod;
import nyth.prae.awesome.plugins.prag.runnables.PreparationTimePeriod;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Prag extends JavaPlugin {

    public static Prag INSTANCE;

    public static GameTimePeriod gamePeriod;
    public static PreparationTimePeriod preparationPeriod;
    public static Configuration config;
    public static GameState gameState;
    public static SettingsCache settingsCache;

    public static Scoreboard PRAG_SCOREBOARD = Bukkit.getScoreboardManager().getNewScoreboard();
    public static Team TAGGERS = PRAG_SCOREBOARD.registerNewTeam("taggers");
    public static Team FROZEN = PRAG_SCOREBOARD.registerNewTeam("frozen");
    public static Team PLAYERS = PRAG_SCOREBOARD.registerNewTeam("players");
    public static List<UUID> taggers = new ArrayList<>();
    public static List<UUID> frozenPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        INSTANCE = this;
        gameState = GameState.LOBBY;
        config = getConfig();
        INSTANCE.getConfig().options().copyDefaults();
        INSTANCE.saveDefaultConfig();

        // Register events
        getServer().getPluginManager().registerEvents(new DamageListeners(), INSTANCE);

        // Register commands
        PragCommand.register();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
