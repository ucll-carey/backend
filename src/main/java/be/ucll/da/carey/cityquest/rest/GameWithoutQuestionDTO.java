package be.ucll.da.carey.cityquest.rest;

import be.ucll.da.carey.cityquest.model.Coordinate;
import be.ucll.da.carey.cityquest.model.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class GameWithoutQuestionDTO {
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String city;
    @NonNull @Valid
    private Coordinate cityCoordinate;
}
