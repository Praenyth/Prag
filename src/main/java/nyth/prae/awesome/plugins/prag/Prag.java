package nyth.prae.awesome.plugins.prag;

import nyth.prae.awesome.plugins.prag.commands.PragCommand;
import nyth.prae.awesome.plugins.prag.listeners.DamageListeners;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class Prag extends JavaPlugin {

    public static Prag instance;
    public static GameState gameState;
    public static UUID taggerUUID;

    @Override
    public void onEnable() {
        instance = this;
        gameState = GameState.LOBBY;

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
