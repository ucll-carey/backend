package be.ucll.da.carey.cityquest.db;

import be.ucll.da.carey.cityquest.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GameRepository extends MongoRepository<Game, UUID> {
}
