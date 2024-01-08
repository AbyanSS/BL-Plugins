package org.arj.mcplugins.brutallegendsplugin.Template;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TemplateSectionHandler {
    public String getPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "BRUTAL " + ChatColor.DARK_PURPLE + "/ ";
    }

    public void kirimPesan(Player player, String pesan) {
        String prefix = getPrefix();
        player.sendMessage(prefix + ChatColor.RESET + pesan);
    }
}

