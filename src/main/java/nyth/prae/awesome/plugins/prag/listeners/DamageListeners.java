package nyth.prae.awesome.plugins.prag.listeners;

import nyth.prae.awesome.plugins.prag.Prag;
import nyth.prae.awesome.plugins.prag.Util;
import nyth.prae.awesome.plugins.prag.enums.GameState;
import nyth.prae.awesome.plugins.prag.enums.GameType;
import nyth.prae.awesome.plugins.prag.enums.Role;
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
                if (Util.getRole(damager) == Role.TAGGER) {
                    switch (Prag.settingsCache.tagType) {
                        case NORMAL:
                            Util.announceMessage(ChatColor.RED + victim.getName() + " was tagged by " + damager.getName() + "!");
                            Util.setRole(damager, Role.RUNNER);
                            Util.setRole(victim, Role.TAGGER);
                            break;
                        case FREEZE:
                            Util.announceMessage(ChatColor.AQUA + victim.getName() + " is frozen!");
                            Util.setRole(victim, Role.FROZEN);
                            if (Util.checkForTaggerWin()) {
                                Util.endGame(true);
                            }
                            break;
                        case TNT:
                            Util.announceMessage(ChatColor.RED + victim.getName() + " was given the bomb from " + damager.getName() + "!");
                            Util.setRole(damager, Role.RUNNER);
                            Util.setRole(victim, Role.TAGGER);
                            break;
                    }
                } else if (Prag.settingsCache.tagType.equals(GameType.FREEZE)) {
                    if (Util.getRole(damager) == Role.FROZEN) {
                        event.setCancelled(true);
                    } else if (Util.getRole(victim) == Role.FROZEN) {
                        Util.setRole(victim, Role.RUNNER);
                        Util.announceMessage(ChatColor.AQUA + victim.getName() + " is no longer frozen!");
                    }
                }
            }
        }

    }

    @EventHandler
    public void onOtherDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {
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
            if (!Objects.equals(Util.getRole(victim), Role.TAGGER)) {
                if (Prag.settingsCache.tagType.equals(GameType.INFECTION)) {
                    Util.setRole(victim, Role.TAGGER);
                    Util.announceMessage(ChatColor.RED + victim.getName() + " was infected!");
                    if (Util.checkForTaggerWin()) {
                        Util.endGame(true);
                    }
                }
            }
        }
    }

}
