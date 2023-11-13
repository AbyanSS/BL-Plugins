package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class HarderTotemLootHandler implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(event.getEntity() instanceof Evoker){
            Player killer = event.getEntity().getKiller();

            if (killer != null){
                if (hasPlayerLuckyChance()) {
                    ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                    event.getDrops().add(totem);
                    killer.sendMessage("U Got §6Totem");
                    Bukkit.getLogger().info(killer.getDisplayName() + "Got 1 §6Totem, How lucky!");
                } else {
                    killer.sendMessage("§4Never Give up n Never Surentod!");
                    Bukkit.getLogger().info(killer.getDisplayName() + "Didn't have his/her luck today!");
                }
            }
        }
    }

    @EventHandler
    public void onEvokerNoDrop(EntityDeathEvent event){
        if(event.getEntity() instanceof Evoker){
            //Clear all item that Evoker will drop
            event.getDrops().clear();
        }
    }

    private boolean hasPlayerLuckyChance() {
        // Lucky chance 5% | 5 of 100 chance
        int random = new Random().nextInt(100) + 1;
        return random <= 5;
    }
}
