package guru.aliaga.uhc.scenarios;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class RodLessScenario implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent event){
        if(event.getItem() != null && event.getItem().getType() == Material.FISHING_ROD){
            event.setCancelled(true);
            event.getPlayer().sendMessage("Rods are disabled");
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event){
        if(event.getRecipe().getResult().getType() == Material.FISHING_ROD){
            event.setCancelled(true);
            event.getWhoClicked().sendMessage("Rods are disabled");
        }
    }
}