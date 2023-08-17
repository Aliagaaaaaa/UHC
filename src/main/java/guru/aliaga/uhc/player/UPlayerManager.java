package guru.aliaga.uhc.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class UPlayerManager {

    private HashMap<UUID, UPlayer> players;

    public UPlayerManager() {
        this.players = new HashMap<>();
    }

    public UPlayer createPlayer(UUID uniqueId, String name) {
        UPlayer player = new UPlayer(uniqueId, name);
        this.players.put(uniqueId, player);
        return player;
    }

    public UPlayer getPlayer(UUID uniqueId) {
        return this.players.get(uniqueId);
    }

    public UPlayer getPlayer(String name){
        for(UPlayer player : this.players.values()){
            if(player.getName().equalsIgnoreCase(name)){
                return player;
            }
        }
        return null;
    }

}
