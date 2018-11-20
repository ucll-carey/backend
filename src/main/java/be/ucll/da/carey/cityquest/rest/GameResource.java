package be.ucll.da.carey.cityquest.rest;

import be.ucll.da.carey.cityquest.db.GameDb;
import be.ucll.da.carey.cityquest.db.GameInMemoryDb;
import be.ucll.da.carey.cityquest.db.GameRepository;
import be.ucll.da.carey.cityquest.model.Game;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/games")
public class GameResource {
    private GameDb gameDb = new GameInMemoryDb();

    @Autowired
    private GameRepository repository;

    // GET ALL
    @GetMapping("")
    public List<GameWithoutQuestionDTO> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        return repository.findAll().stream()
                .map(game -> modelMapper.map(game, GameWithoutQuestionDTO.class))
                .collect(Collectors.toList());
        //return gameDb.getAll();
    }

    // GET ONE
    @GetMapping("/{id}")
    public Optional<Game> getByUUID(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    // POST
    @PostMapping("")
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

    // PUT
    @PutMapping("")
    public Game update(@Valid @RequestBody GameInputDTO gameInput) {
        Optional<Game> optionalGame = repository.findById(gameInput.getId());
        if (optionalGame.isPresent()) {
            val game = optionalGame.get();
            game.setName(gameInput.getName());
            game.setDescription(gameInput.getDescription());
            game.setCity(gameInput.getCity());
            game.setCityCoordinate(gameInput.getCityCoordinate());
            game.setQuestions(gameInput.getQuestions());
            return repository.insert(game);
        } else {
            throw new GameNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public Game delete(@PathVariable("id") UUID id) {
        Optional<Game> optionalGame = repository.findById(id);
        if (optionalGame.isPresent()) {
            val game = optionalGame.get();
            repository.delete(game);
            return game;
        } else {
            throw new GameNotFoundException();
        }
    }

}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Game not found")
class GameNotFoundException extends RuntimeException { }
