package guru.aliaga.uhc.team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//named this class UTeam because Team is already a class in the Bukkit API
public class UTeam {

    private final UUID uniqueId;
    private UUID leader;
    private List<UUID> members;
    private List<UUID> invitedMembers;

    public UTeam(UUID leader) {
        this.uniqueId = UUID.randomUUID();
        this.leader = leader;
        this.members = new ArrayList<>();
        this.invitedMembers = new ArrayList<>();

        this.members.add(leader);
    }


    public UUID getUniqueId() {
        return uniqueId;
    }

    public UUID getLeader() {
        return leader;
    }

    public void setLeader(UUID leader) {
        this.leader = leader;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public void sendMessage(String message) {
        for (UUID member : this.members) {
            Player player = Bukkit.getPlayer(member);
            if (player != null) {
                player.sendMessage(message);
            }
        }
    }

    public void invite(UUID player) {
        this.invitedMembers.add(player);
    }

    public List<UUID> getInvitedMembers() {
        return invitedMembers;
    }



}
