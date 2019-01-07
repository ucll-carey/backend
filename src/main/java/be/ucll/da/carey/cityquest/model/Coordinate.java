package be.ucll.da.carey.cityquest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class Coordinate {
    private double lat;
    private double lon;

    @java.beans.ConstructorProperties({"lat", "lon"})
    Coordinate(@Min(0) double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
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
            return "Coordinate.CoordinateBuilder(lat=" + this.latitude + ", lon=" + this.longitude + ")";
        }
    }

}
