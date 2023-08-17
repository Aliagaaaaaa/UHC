package guru.aliaga.uhc;

import guru.aliaga.uhc.game.GameState;
import guru.aliaga.uhc.player.UPlayerManager;
import guru.aliaga.uhc.team.UTeamManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UHC extends JavaPlugin {

    private static UHC instance;

    private UPlayerManager playerManager;
    private UTeamManager teamManager;


    @Override
    public void onEnable(){
        instance = this;
        GameState.setCurrentGameState(GameState.PRE_GAME);

        this.playerManager = new UPlayerManager();
        this.teamManager = new UTeamManager();
    }

    public static UHC getInstance() {
        return instance;
    }

    public UPlayerManager getPlayerManager() {
        return playerManager;
    }

    public UTeamManager getTeamManager() {
        return teamManager;
    }
}
