package nyth.prae.awesome.plugins.prag.listeners;

import nyth.prae.awesome.plugins.prag.Util;
import nyth.prae.awesome.plugins.prag.enums.GameState;
import nyth.prae.awesome.plugins.prag.Prag;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class DamageListeners implements Listener {

    @EventHandler
    public void onAttackDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();

            if (Prag.gameState == GameState.INGAME) {
                if (damager.getGameMode().equals(GameMode.SURVIVAL)) {
                    if (Prag.taggers.contains(damager.getUniqueId())) {
                        switch (Prag.config.getString("Tag-Type")) {
                            case "NORMAL":
                                Prag.taggers.remove(damager.getUniqueId());
                                Prag.taggers.add(victim.getUniqueId());
                                Util.announceMessage(ChatColor.RED + victim.getName() + " was tagged by " + damager.getName() + "!");
                                break;
                            case "FREEZE":
                                Prag.frozenPlayers.add(victim.getUniqueId());
                                Util.announceMessage(ChatColor.AQUA + victim.getName() + " is frozen!");
                                break;
                            case "TNT":
                                Prag.taggers.remove(damager.getUniqueId());
                                Prag.taggers.add(victim.getUniqueId());
                                Util.announceMessage(ChatColor.RED + victim.getName() + " was given the bomb from " + damager.getName() + "!");
                                break;
                        }
                    } else if (Objects.equals(Prag.config.getString("Tag-Type"), "FREEZE")) {
                        if (Prag.frozenPlayers.contains(damager.getUniqueId())) {
                            event.setCancelled(true);
                        } else if (Prag.frozenPlayers.contains(victim.getUniqueId())) {
                            Prag.frozenPlayers.remove(victim.getUniqueId());
                            Util.announceMessage(ChatColor.AQUA + victim.getName() + " is no longer frozen!");
                        }
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

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player victim = event.getEntity();
        if (Prag.gameState == GameState.INGAME) {
            if (!Prag.taggers.contains(event.getEntity().getUniqueId())) {
                if (Prag.config.getString("Tag-Type").equals("INFECTION")) {
                    Prag.taggers.add(victim.getUniqueId());
                    Util.announceMessage(ChatColor.RED + victim.getName() + " was infected!");
                }
            }
        }
    }

}
