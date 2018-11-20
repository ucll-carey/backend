package be.ucll.da.carey.cityquest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class Coordinate {
    @JsonProperty("lat")
    private double latitude;
    @JsonProperty("lon")
    private double longitude;

    @java.beans.ConstructorProperties({"latitude", "longitude"})
    Coordinate(@Min(0) double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static CoordinateBuilder builder() {
        return new CoordinateBuilder();
    }

    public static class CoordinateBuilder {
        private double latitude;
        private double longitude;

        CoordinateBuilder() {
        }

        public CoordinateBuilder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public CoordinateBuilder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Coordinate build() {
            assert (latitude > 0.0);
            assert (longitude > 0.0);
            return new Coordinate(latitude, longitude);
        }

        public String toString() {
            return "Coordinate.CoordinateBuilder(latitude=" + this.latitude + ", longitude=" + this.longitude + ")";
        }
    }

}
