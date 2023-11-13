package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class HarderMendingLootHandler implements Listener {
    @EventHandler
    public void OnPlayerFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH){
            Player player = event.getPlayer();
            Entity caughtEntity = event.getCaught();

            if (caughtEntity instanceof Item){
                Item itemEntity = (Item) caughtEntity;
                ItemStack caughtItem = itemEntity.getItemStack();

                if (isMendingBook(caughtItem)){
                    if (hasPlayerLuckyChance()){
                        player.getWorld().dropItem(player.getLocation(), caughtItem);
                        player.sendMessage("§aYou got Mending!");
                        Bukkit.getLogger().info(player.getDisplayName() + "Got mending, how lucky!");
                    } else {
                        player.sendMessage("§cNever GIve Up n Never Surentod!");
                        Bukkit.getLogger().info(player.getDisplayName() + "Didn't have his/here luck today.");
                    }
                } else {
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.PUFFERFISH));
                }
            }
        }
    }

    private boolean isMendingBook(ItemStack item) {
        return item != null && item.getType() == Material.ENCHANTED_BOOK && item.getEnchantments().containsKey(Enchantment.MENDING);
    }

    private boolean hasPlayerLuckyChance() {
        // Lucky chance 5% | 5 of 100 chance
        int random = new Random().nextInt(100) + 1;
        return random <= 5;
    }
}
