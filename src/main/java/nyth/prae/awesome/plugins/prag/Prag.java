package nyth.prae.awesome.plugins.prag;

import nyth.prae.awesome.plugins.prag.commands.PragCommand;
import nyth.prae.awesome.plugins.prag.enums.GameState;
import nyth.prae.awesome.plugins.prag.listeners.DamageListeners;
import nyth.prae.awesome.plugins.prag.runnables.GameTimePeriod;
import nyth.prae.awesome.plugins.prag.runnables.PreparationTimePeriod;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Prag extends JavaPlugin {

    public static Prag instance;

    public static GameTimePeriod gamePeriod;
    public static PreparationTimePeriod preparationPeriod;
    public static Configuration config;
    public static GameState gameState;
    public static SettingsCache settingsCache;
    public static List<UUID> taggers = new ArrayList<>();
    public static List<UUID> frozenPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        gameState = GameState.LOBBY;
        config = getConfig();
        instance.getConfig().options().copyDefaults();
        instance.saveDefaultConfig();

        // Register events
        getServer().getPluginManager().registerEvents(new DamageListeners(), instance);

        // Register commands
        PragCommand.register();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
