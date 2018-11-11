package be.ucll.da.carey.cityquest.rest;

import be.ucll.da.carey.cityquest.model.Coordinate;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class GameWithoutQuestionDTO {
    private UUID id;
    private String name;
    private String description;
    private String city;
    private Coordinate cityCoordinate;
}
