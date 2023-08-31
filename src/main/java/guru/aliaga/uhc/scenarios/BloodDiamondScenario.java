package guru.aliaga.uhc.scenarios;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BloodDiamondScenario implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(event.getBlock().getType() == Material.DIAMOND_ORE){
            event.getPlayer().damage(1);
        }
    }
}
