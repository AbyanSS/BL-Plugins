package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.arj.mcplugins.brutallegendsplugin.Template.TemplateSectionHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemDespawnHandler implements Listener {

    private final JavaPlugin plugin;

    private final TemplateSectionHandler templateSectionHandler;

    public ItemDespawnHandler(JavaPlugin plugin, TemplateSectionHandler templateSectionHandler) {
        this.plugin = plugin;
        this.templateSectionHandler = templateSectionHandler;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);

        // Schedule task to despawn items every 3 minutes
        new BukkitRunnable() {
            @Override
            public void run() {
                despawnItems();
            }
        }.runTaskTimer(plugin, 0, 20 * 60 * 3); // 20 ticks * 60 seconds * 3 minutes
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        // Optional: Tambahkan logika lain jika diperlukan saat item despawn
    }

    private void despawnItems() {
        for (Item item : plugin.getServer().getWorlds().get(0).getEntitiesByClass(Item.class)) {
            item.remove(); // Hapus item
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("clearlag")) {
            despawnItems(); // Hapus item saat perintah /clearlag dipanggil
            sender.sendMessage(templateSectionHandler.getPrefix() + "Item despawned.");
            return true;
        }
        return false;
    }
}
