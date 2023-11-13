package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemStackHandler implements Listener {

    private int maxStackGoldenApple = 12; // Tentukan batasan ukuran tumpukan untuk Golden Apple

    @EventHandler
    public void onPlayerClickGoldenApple(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {
            ItemStack clickedItem = event.getCurrentItem();

            // Pengecekan apakah item yang diklik adalah Golden Apple
            if (clickedItem != null && clickedItem.getType() == Material.GOLDEN_APPLE) {
                // Pengecekan apakah jumlah item melebihi batasan ukuran tumpukan
                if (clickedItem.getAmount() > maxStackGoldenApple) {
                    // Mengganti jumlah item dengan batasan ukuran tumpukan yang diinginkan
                    ItemStack newItem = clickedItem.clone();
                    newItem.setAmount(maxStackGoldenApple);

                    // Mengganti item di dalam inventori pemain dengan item yang telah diatur jumlahnya
                    if (event.getWhoClicked() instanceof Player) {
                        Player player = (Player) event.getWhoClicked();
                        player.getInventory().setItem(event.getSlot(), newItem);
                        event.setCancelled(true);  // Membatalkan event agar item tidak ditambahkan secara normal
                    }
                }
            }
        }
    }
}
