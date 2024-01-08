package org.arj.mcplugins.brutallegendsplugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.arj.mcplugins.brutallegendsplugin.Template.TemplateSectionHandler; // Import class TemplateSectionHandler

import java.util.Random;

public class HarderTotemLootHandler implements Listener {

    private final TemplateSectionHandler templateSectionHandler; // Inisialisasi TemplateSectionHandler

    public HarderTotemLootHandler(TemplateSectionHandler templateSectionHandler) {
        this.templateSectionHandler = templateSectionHandler;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(event.getEntity() instanceof Evoker){
            Player killer = event.getEntity().getKiller();

            if (killer != null){
                if (hasPlayerLuckyChance()) {
                    ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                    event.getDrops().add(totem);
                    sendLootMessage(killer, true); // Mengirim pesan ke player saat mendapat totem
                } else {
                    sendLootMessage(killer, false); // Mengirim pesan ke player saat tidak mendapat totem
                }
            }
        }
    }

    @EventHandler
    public void onEvokerNoDrop(EntityDeathEvent event){
        if(event.getEntity() instanceof Evoker){
            //Clear all item that Evoker will drop
            event.getDrops().clear();
        }
    }

    private void sendLootMessage(Player player, boolean gotTotem) {
        String message = gotTotem ? "U Got §6Totem" : "§4Never Give up n Never Surentod!";
        templateSectionHandler.kirimPesan(player, message); // Mengirim pesan ke player dengan TemplateSectionHandler
        Bukkit.getLogger().info(player.getDisplayName() + (gotTotem ? " Got 1 §6Totem, How lucky!" : " Didn't have his/her luck today!"));
    }

    private boolean hasPlayerLuckyChance() {
        // Lucky chance 5% | 5 of 100 chance
        int random = new Random().nextInt(100) + 1;
        return random <= 5;
    }
}
