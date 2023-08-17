package guru.aliaga.uhc.player;

import guru.aliaga.uhc.team.UTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

//named this class UPlayer because Player is already a class in the Bukkit API
public class UPlayer {

    private UUID uniqueId;
    private UTeam team;
    private String name;

    public UPlayer(UUID uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public UTeam getTeam() {
        return team;
    }

    public void setTeam(UTeam team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message){
        Player player = Bukkit.getPlayer(this.uniqueId);
        if(player != null){
            player.sendMessage(message);
        }

    }
}
