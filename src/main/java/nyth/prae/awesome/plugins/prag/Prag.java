package nyth.prae.awesome.plugins.prag;

import nyth.prae.awesome.plugins.prag.commands.PragCommand;
import nyth.prae.awesome.plugins.prag.enums.GameState;
import nyth.prae.awesome.plugins.prag.listeners.ConnectionListeners;
import nyth.prae.awesome.plugins.prag.listeners.DamageListeners;
import nyth.prae.awesome.plugins.prag.listeners.WorldLoadListener;
import nyth.prae.awesome.plugins.prag.runnables.GameTimePeriod;
import nyth.prae.awesome.plugins.prag.runnables.PreparationTimePeriod;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public final class Prag extends JavaPlugin {

    public static Prag INSTANCE;

    public static GameTimePeriod gamePeriod;
    public static PreparationTimePeriod preparationPeriod;
    public static Configuration config;
    public static GameState gameState;
    public static SettingsCache settingsCache;
    public static Scoreboard PRAG_SCOREBOARD;

    @Override
    public void onEnable() {
        INSTANCE = this;
        gameState = GameState.LOBBY;
        config = getConfig();
        INSTANCE.getConfig().options().copyDefaults();
        INSTANCE.saveDefaultConfig();

        // Register events
        getServer().getPluginManager().registerEvents(new DamageListeners(), INSTANCE);
        getServer().getPluginManager().registerEvents(new ConnectionListeners(), INSTANCE);
        getServer().getPluginManager().registerEvents(new WorldLoadListener(), INSTANCE);

        // Register commands
        PragCommand.register();

        Util.setAndSaveConfig("config-version","1");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
