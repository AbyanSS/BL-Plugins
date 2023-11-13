package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {
    private boolean isEnabled = true;
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if (isEnabled){
            event.setCancelled(true);
            event.getPlayer().sendMessage("§4Chat disabled");
            Bukkit.getLogger().info("Player tried to send message and been cancelled");
        }

    }

    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args) {
        if (Label.equalsIgnoreCase("togglechat")) {
            isEnabled = !isEnabled;
            sender.sendMessage("Toggled chat " + (isEnabled ? "§4Of" : "§aOn"));
            Bukkit.getLogger().info("Toggle Chat Listener triggered");
            return true;
        }
        return false;
    }
}
