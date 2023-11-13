package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.arj.mcplugins.brutallegendsplugin.BrutalLegendsPlugin;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class HealthHandler implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDroppedExp(0);
        event.getEntity().setMaxHealth(event.getEntity().getMaxHealth() - 2.0);
        Bukkit.getLogger().info("Player death and Max health decrease by 2.0");
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        // Memeriksa apakah maxHealth setelah respawn adalah 10
        if (event.getPlayer().getMaxHealth() == 0) {
            String playerName = event.getPlayer().getName();
            String reason = "Karaktermu mati, butuh 5 orang untuk menjalankan ritual pembangkitan arwah";

            // Membanned pemain dengan alasan yang diberikan
            Bukkit.getBanList(BanList.Type.NAME).addBan(playerName, reason, null, null);

            // Menjadwalkan tugas untuk menendang pemain setelah respawn penuh
            Bukkit.getScheduler().runTaskLater(
                    BrutalLegendsPlugin.getPlugin(BrutalLegendsPlugin.class),  // Ganti dengan kelas plugin Anda
                    () -> {
                        // Cek apakah pemain terhubung sebelum mencoba kickPlayer
                        if (Bukkit.getServer().getPlayer(playerName) != null) {
                            Bukkit.getServer().getPlayer(playerName).kickPlayer(reason);
                            Bukkit.getLogger().info("Player respawned and Max health checked for being 0, player kicked.");
                        }
                    },
                    20  // Menunggu 20 ticks setelah respawn sebelum menendang pemain
            );
        }
    }


}
