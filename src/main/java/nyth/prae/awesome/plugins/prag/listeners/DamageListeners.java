package nyth.prae.awesome.plugins.prag.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListeners implements Listener {

    @EventHandler
    public void onAttackDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();

            damager.sendMessage("You attacked " + victim.getName());
            victim.sendMessage(damager.getName() + " attacked you");
        }

    }

}
