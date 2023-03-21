package nyth.prae.awesome.plugins.prag.listeners;

import nyth.prae.awesome.plugins.prag.enums.GameState;
import nyth.prae.awesome.plugins.prag.Prag;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListeners implements Listener {

    @EventHandler
    public void onAttackDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();

            if (Prag.gameState == GameState.INGAME) {
                if (damager.getGameMode().equals(GameMode.SURVIVAL)) {
                    if (Prag.taggers.contains(damager.getUniqueId())) {
                        event.setCancelled(true);
                    }
                }
            }
        }

    }

    @EventHandler
    public void onOtherDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                if (Prag.gameState == GameState.INGAME) {
                    event.setCancelled(true);
                }
            }
        }

    }

}
