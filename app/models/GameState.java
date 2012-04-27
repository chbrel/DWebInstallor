package models;

import java.util.HashMap;

public enum GameState {
	OK("Valider pour le CD"), MISSING_COMPLETIONS("Manque des finitions");
	
	public static final HashMap<String, GameState> validGameState = createValidGameState();

    // The game state string.
    private String gstate;

    /**
     * Initialise with the corresponding game states.
     * 
     * @param gstate
     *            The game state string.
     */
    GameState(String gstate) {
        this.gstate = gstate;
    }

    private static HashMap<String, GameState> createValidGameState() {
        HashMap<String, GameState> map = new HashMap<String, GameState>();
        for (GameState gstate : GameState.values()) {
            map.put(gstate.toString(), gstate);
        }
        return map;
    }

    /**
     * @return The public as a string.
     */
    public String toString() {
        return gstate;
    }
	
}
