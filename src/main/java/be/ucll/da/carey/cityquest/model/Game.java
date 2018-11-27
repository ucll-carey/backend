package be.ucll.da.carey.cityquest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NonNull
    private String name;
    @NonNull
    private String description;
    @JsonProperty("location")
    @NonNull
    private String city;
    @JsonProperty("coordinates")
    @NonNull
    private Coordinate cityCoordinate;
    private ArrayList<Question> questions;
}
