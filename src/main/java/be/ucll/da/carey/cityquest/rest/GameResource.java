package be.ucll.da.carey.cityquest.rest;

import be.ucll.da.carey.cityquest.db.GameRepository;
import be.ucll.da.carey.cityquest.model.Game;
import be.ucll.da.carey.cityquest.model.GamePreferences;
import be.ucll.da.carey.cityquest.model.RecommendConfig;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/games")
public class GameResource {

    private final Logger log = LoggerFactory.getLogger(GameResource.class);

    @Autowired
    private GameRepository repository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RecommendConfig recommendConfig;
    // GET ALL
    @GetMapping("")
    public List<GameWithoutQuestionDTO> getAll(@RequestParam(value = "username", required = false) String username) {
        ModelMapper modelMapper = new ModelMapper();
        List<GameWithoutQuestionDTO> games = repository.findAll().stream()
                .map(game -> modelMapper.map(game, GameWithoutQuestionDTO.class))
                .collect(Collectors.toList());

        // circuit breaker for score
        if (username != null) {
            log.trace(String.format("Retreiving recommendations for %s", username));
            try {
                GamePreferences userGamePreferences = getPreferencesForUser(username);
                for (GameWithoutQuestionDTO game : games) {
                    game.setScore(userGamePreferences.getRatingForGame(game.getId()));
                }
            } catch (Exception e) {
                // Do nothing
            }
        }
        return games;
    }

    // GET ALL with preference
    @GetMapping("/getpreferences/{username}")
    public GamePreferences getPreferencesForUser(@PathVariable String username) throws ServiceUnavailableException {
        //val games = getAll();
        URI service = recommendationServiceUrl()
                .map(s -> s.resolve("/recommendation/recommend/" + username))
                .orElseThrow(ServiceUnavailableException::new);
        log.trace("recommendservice url:" + service);
        return restTemplate
                .getForEntity(service, GamePreferences.class)
                .getBody();
        //return games;
    }

    // GET ONE
    @GetMapping("/{id}")
    public Optional<Game> getByUUID(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    // POST
    @PostMapping("")
    public Game create(@Valid @RequestBody Game game) {
        // give the new game a uuid
        System.out.println(game);
        game.setId(UUID.randomUUID());
        System.out.println(game);
        return repository.save(game);
    }

    // PUT
    @PutMapping("")
    public Game update(@Valid @RequestBody Game gameInput) {
        Optional<Game> optionalGame = repository.findById(gameInput.getId());
        if (optionalGame.isPresent()) {
            val game = optionalGame.get();
            game.setName(gameInput.getName());
            game.setDescription(gameInput.getDescription());
            game.setLocation(gameInput.getLocation());
            game.setCoordinates(gameInput.getCoordinates());
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

    // for debuggin purposes
    @GetMapping("/debug")
    public Optional<URI> recommendationServiceUrl() {
        if (recommendConfig.getRecommendserver() != null)
            return Optional.ofNullable(recommendConfig.getRecommendserver());
        return discoveryClient.getInstances("recommendation")
                .stream()
                .map(si -> si.getUri())
                .findFirst();
    }

}

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Game not found")
class GameNotFoundException extends RuntimeException { }
