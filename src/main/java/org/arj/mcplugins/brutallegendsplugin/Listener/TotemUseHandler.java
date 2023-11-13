package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TotemUseHandler implements Listener {
    @EventHandler // Totem recover life by 2 health points / 1 heart
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item != null && item.getType() == Material.TOTEM_OF_UNDYING){
            item.setAmount(item.getAmount() - 1);
            player.playEffect(EntityEffect.TOTEM_RESURRECT);
            if (player.getMaxHealth() < 20){
                player.setMaxHealth(player.getMaxHealth() + 2.0);
                Bukkit.getLogger().info("Player use totem to heal themselves");
            }
        }
    }

    @EventHandler // Disable totem common function
    public void onPlayerDeath(EntityResurrectEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack rightHand = player.getInventory().getItemInMainHand();
            ItemStack leftHand = player.getInventory().getItemInOffHand();

            if (rightHand != null && rightHand.getType() == Material.TOTEM_OF_UNDYING) {
                event.setCancelled(true);
                Bukkit.getLogger().info("Player tried to use totem and has been cancelled");
            } else if (leftHand != null && leftHand.getType() == Material.TOTEM_OF_UNDYING) {
                event.setCancelled(true);
                Bukkit.getLogger().info("Player tried to use totem and has been cancelled");
            }
        }
    }

}
