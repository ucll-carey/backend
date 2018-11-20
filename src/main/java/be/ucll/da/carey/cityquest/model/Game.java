package be.ucll.da.carey.cityquest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.UUID;

@Data
@Builder
@JsonPropertyOrder( {
        "id",
        "name",
        "location",
        "coordinates",
        "description",
        "questions"
})
public class Game {
    @Id private UUID id;
    private String name;
    private String description;
    @JsonProperty("location")
    private String city;
    @JsonProperty("coordinates")
    private Coordinate cityCoordinate;
    private ArrayList<Question> questions;
}
