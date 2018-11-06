package be.ucll.da.carey.cityquest.rest;

import be.ucll.da.carey.cityquest.db.GameDb;
import be.ucll.da.carey.cityquest.db.GameInMemoryDb;
import be.ucll.da.carey.cityquest.model.Game;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/game")
public class GameResource {
    private GameDb gameDb = new GameInMemoryDb();

    @RequestMapping("/")
    public String index() {
        return "This is an index page";
    }

    @RequestMapping("/all")
    public ArrayList<Game> getAll() {
        return gameDb.getAll();
    }

}
