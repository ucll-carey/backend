package be.ucll.da.carey.cityquest.model;

import java.util.HashMap;
import java.util.UUID;
public class GamePreferences extends HashMap<UUID, Float> {
    public Float getRatingForGame(UUID gameId) {
        return super.get(gameId);
    }
}
