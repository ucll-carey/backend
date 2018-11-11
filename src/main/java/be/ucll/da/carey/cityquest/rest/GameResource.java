package be.ucll.da.carey.cityquest.rest;

import be.ucll.da.carey.cityquest.db.GameDb;
import be.ucll.da.carey.cityquest.db.GameInMemoryDb;
import be.ucll.da.carey.cityquest.db.GameRepository;
import be.ucll.da.carey.cityquest.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/game")
public class GameResource {
    private GameDb gameDb = new GameInMemoryDb();

    @Autowired
    private GameRepository repository;

    // GET ALL
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Game> getAll() {
        return repository.findAll();
        //return gameDb.getAll();
    }

    // GET ONE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Game> getByUUID(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    // POST
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Game create(@Valid @RequestBody GameInputDTO gameInput) {
        //System.out.println(gameInput);
        //ModelMapper modelMapper = new ModelMapper();
        Game game = Game.builder()
                .id(UUID.randomUUID())
                .name(gameInput.getName())
                .description(gameInput.getDescription())
                .city(gameInput.getCity())
                .cityCoordinate(gameInput.getCityCoordinate())
                .questions(gameInput.getQuestions())
                .build();
        return repository.save(game);
    }
}
