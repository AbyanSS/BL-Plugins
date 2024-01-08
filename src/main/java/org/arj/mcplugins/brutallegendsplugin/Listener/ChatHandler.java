package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.arj.mcplugins.brutallegendsplugin.Template.TemplateSectionHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatHandler implements Listener {
    private boolean isEnabled = true;
    private final JavaPlugin plugin;
    private final TemplateSectionHandler templateSectionHandler;

    public ChatHandler(JavaPlugin plugin, TemplateSectionHandler templateSectionHandler) {
        this.plugin = plugin;
        this.templateSectionHandler = templateSectionHandler;

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            // Perform your task here
            saveGame();
        }, 0L, 20L * 60L * 60L * 6L); // Run every 1 minute

    }

    public void saveGame() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            templateSectionHandler.kirimPesan(player, "Saved the game");
            Bukkit.getLogger().info("Ini log saved the game");
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if (isEnabled){
            event.setCancelled(true);
            templateSectionHandler.kirimPesan(event.getPlayer(), "Chat disabled");
            plugin.getLogger().info("Player tried to send message and been cancelled");
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args) {
        if (Label.equalsIgnoreCase("togglechat")) {
            isEnabled = !isEnabled;
            sender.sendMessage(templateSectionHandler.getPrefix() + "Toggled chat " + (isEnabled ? "§4Of" : "§aOn"));
            Bukkit.getLogger().info("Toggle Chat Listener triggered");
            return true;
        }
        return false;
    }
}
