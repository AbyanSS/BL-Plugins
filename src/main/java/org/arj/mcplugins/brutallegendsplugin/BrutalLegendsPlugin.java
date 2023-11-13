package org.arj.mcplugins.brutallegendsplugin;

import org.arj.mcplugins.brutallegendsplugin.Listener.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class BrutalLegendsPlugin extends JavaPlugin {

    /**
     * Plugin Custom Brutal legends by Arj
     * 1. Disable Chat ✓
     * 2. Disable NameTag
     * 3. Heart Reduction ✓
     * 4. No Totem Drop ✓
     * 5. Totem Use ✓
     * 6. Harder Totem Loot ✓
     * 7. Harder Mending Loot ✓
     * 8. Combat Log ✓
     * 9. Nerf Doombringer Axe
     * 10. Item Stack Limit (BUG)
     * **/

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ChatHandler(), this);
        getServer().getPluginManager().registerEvents(new CombatLogHandler(), this);
        getServer().getPluginManager().registerEvents(new HarderMendingLootHandler(), this);
        getServer().getPluginManager().registerEvents(new HarderTotemLootHandler(), this);
        getServer().getPluginManager().registerEvents(new HealthHandler(), this);
        getServer().getPluginManager().registerEvents(new ItemStackHandler(), this);
        getServer().getPluginManager().registerEvents(new TotemUseHandler(), this);

        getCommand("toggletotemdrops").setExecutor(this);
        getCommand("togglechat").setExecutor(this);

        getLogger().info("All plugins successfully enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("All plugins are disabled");
    }
}
