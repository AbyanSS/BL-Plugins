package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

public class CombatLogHandler implements Listener {

    @EventHandler
    public void onPlayerTpWithEPearl(PlayerTeleportEvent event){
        Player player = event.getPlayer();

        // Check if player tp with epearl
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            // Check in player has trident in inventory
            if (hasTridentInInventory(player)) {
                // Set cooldown time to 500 ticks / 25 seconds
                player.setCooldown(Material.TRIDENT, 500);
            }
        }
    }

    @EventHandler
    public void onPlayerHoldDoombringer(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        if (event.getNewSlot() < 9 == hasDoombringerAxe(player)) {
            // Check if Doombringer Axe is on cooldown
            if (hasDoombringerAxe(player) && !player.hasCooldown(Material.DIAMOND_AXE)) {
                setDoombringerCooldown(player);
            }/*else {
                event.setCancelled(true);
            }*/
        }
    }

    @EventHandler
    public void onDoombringerCooldown(PlayerInteractEvent event){
        if (event.getPlayer().hasCooldown(Material.DIAMOND_AXE)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){ //E Pearl
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.hasItem() && event.getItem().getType() == Material.ENDER_PEARL) {
                ItemStack item = event.getItem();
                // Validate if player still on cooldown
                if (!event.getPlayer().hasCooldown(Material.ENDER_PEARL)) {
                    item.setAmount(item.getAmount() - 1);
                    // If player is not on cooldown then set the cooldown for 10 seconds (200 Ticks).
                    event.getPlayer().setCooldown(Material.ENDER_PEARL, 200);
//                    event.getPlayer().setCooldown(Material.TRIDENT, 600);
                    // Make sure that player throws Ender Pearl properly
                    EnderPearl enderPearl = event.getPlayer().launchProjectile(EnderPearl.class);
                    Bukkit.getLogger().info("Successfuly set enderPearl cooldown");
                }
            }
        }
    }

    /*@EventHandler // Player get damage
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            // Iterate through the player's inventory and set cooldown for all Trident items.
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.getType() == Material.TRIDENT) {
                    player.setCooldown(Material.TRIDENT, 200); // 10 seconds (200 Ticks).

                }
            }
        }
    }*/

    @EventHandler // Player damage player
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();
            // Iterate through the attacker's inventory and set cooldown for all Trident items.
            for (ItemStack item : attacker.getInventory().getContents()) {
                if (item != null && item.getType() == Material.TRIDENT) {
                    attacker.setCooldown(Material.TRIDENT, 500); // 25 seconds (500 Ticks).
                }
            }
            // Iterate through the victim's inventory and set cooldown for all Trident items.
            for (ItemStack item : victim.getInventory().getContents()) {
                if (item != null && item.getType() == Material.TRIDENT) {
                    victim.setCooldown(Material.TRIDENT, 500); // 25 seconds (500 Ticks).
                }
            }
        }
    }

    @EventHandler
    public void onTridentCooldown(PlayerInteractEvent event) {
        // Pengecekan apakah event adalah aksi memegang item
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand(); // Ganti dengan item di tangan yang benar jika perlu

            // Pengecekan apakah item yang digunakan adalah trident
            if (item != null && item.getType() == Material.TRIDENT) {
                // Pengecekan apakah trident sedang dalam cooldown
                if (player.hasCooldown(Material.TRIDENT)) {
                    event.setCancelled(true);
                }
            }
        }
    }


    private boolean hasTridentInInventory(Player player) {
        // Iterasi melalui seluruh inventory pemain
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.TRIDENT) {
                return true;
            }
        }
        return false;
    }

    public void setDoombringerCooldown(Player player) {
        for (int i = 0; i < 9; i++) {
            ItemStack item = player.getInventory().getItem(i);

            if (isDoombringerAxe(item)) {
                player.setCooldown(Material.DIAMOND_AXE, 400); // 400 ticks = 20 seconds
                break;
            }
        }
    }

    private boolean isDoombringerAxe(ItemStack item) {
        return item != null && item.getType() == Material.DIAMOND_AXE &&
                item.hasItemMeta() && item.getItemMeta().hasDisplayName() &&
                item.getItemMeta().getDisplayName().contains("DOOMBRINGER AXE");
    }

    public boolean hasDoombringerAxe(Player player) {
        int heldSlot = player.getInventory().getHeldItemSlot();
        ItemStack heldItem = player.getInventory().getItem(heldSlot);

        return isDoombringerAxe(heldItem);
    }
}
