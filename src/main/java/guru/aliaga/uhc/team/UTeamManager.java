package guru.aliaga.uhc.team;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class UTeamManager {

    private HashMap<UUID, UTeam> teams;

    public UTeamManager() {
        this.teams = new HashMap<>();
    }

    public UTeam createTeam(UUID leader) {
        UTeam team = new UTeam(leader);
        this.teams.put(team.getUniqueId(), team);
        return team;
    }

    public void disbandTeam(UUID uniqueId) {
        this.teams.remove(uniqueId);
    }




}
