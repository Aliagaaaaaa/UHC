package guru.aliaga.uhc.game;

public enum GameState {

    PRE_GAME,
    IN_GAME,
    POST_GAME;

    private static GameState currentGameState;

    public static GameState getCurrentGameState() {
        return currentGameState;
    }

    public static void setCurrentGameState(GameState currentGameState) {
        GameState.currentGameState = currentGameState;
    }
}
