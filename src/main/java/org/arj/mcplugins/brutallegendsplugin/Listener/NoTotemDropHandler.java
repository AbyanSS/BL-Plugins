package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Evoker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class NoTotemDropHandler implements Listener {
    private boolean isEnabled = true;
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(isEnabled && event.getEntity() instanceof Evoker){
            //Clear all item that Evoker will drop
            event.getDrops().clear();
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args) {
        if (Label.equalsIgnoreCase("toggletotemdrops")) {
            isEnabled = !isEnabled;
            sender.sendMessage("Toggled No Totem Drop " + (isEnabled ? "§aOn" : "§4Off"));
            Bukkit.getLogger().info("Toggle No Totem Drop Listener triggered");
            return true;
        }
        return false;
    }
}
