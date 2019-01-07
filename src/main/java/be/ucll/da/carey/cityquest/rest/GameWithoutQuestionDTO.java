package be.ucll.da.carey.cityquest.rest;

import be.ucll.da.carey.cityquest.model.Coordinate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder( {
        "id",
        "name",
        "location",
        "coordinates",
        "description"
})
public class GameWithoutQuestionDTO {
    private UUID id;
    private String name;
    private String description;
    private String location;
    private Coordinate coordinates;
    private double score;
}
