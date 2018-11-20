package be.ucll.da.carey.cityquest.rest;

import be.ucll.da.carey.cityquest.model.Coordinate;
import be.ucll.da.carey.cityquest.model.Question;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.UUID;

@Data
@NoArgsConstructor
public class GameInputDTO {
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    @JsonProperty("location")
    private String city;
    @NonNull
    @Valid
    @JsonProperty("coordinates")
    private Coordinate cityCoordinate;
    @NonNull
    private ArrayList<Question> questions;

}
