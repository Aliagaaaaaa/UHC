package guru.aliaga.uhc.scenarios;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BowLessScenario implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event){
        if(event.getRecipe().getResult().getType() == Material.BOW){
            event.setCancelled(true);
            event.getWhoClicked().sendMessage("Bows are disabled");
        }
    }

    //if for some reason he gets a bow, don't let him use it
    @EventHandler
    public void onUse(PlayerInteractEvent event){
        if(event.getItem() != null && event.getItem().getType() == Material.BOW){
            event.setCancelled(true);
            event.getPlayer().sendMessage("Bows are disabled");
        }
    }

}
