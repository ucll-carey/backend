package be.ucll.da.carey.cityquest.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @NonNull
    private String location;
    @NonNull
    private Coordinate coordinates;
    private ArrayList<Question> questions;
}
