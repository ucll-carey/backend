package be.ucll.da.carey.cityquest.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.UUID;

@Data
@Builder
public class Game {
    @Id private UUID id;
    private String name;
    private String description;
    private String city;
    private Coordinate cityCoordinate;
    private ArrayList<Question> questions;
}
