package be.ucll.da.carey.cityquest.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {

    private static final double latitude = 50.8795315;
    private static final double longitude = 4.701221700000019;

    @Test
    public void coordinateBuilderWorks() {
        Coordinate newCoordinate = Coordinate.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();

        assertEquals(latitude, newCoordinate.getLat(), 0.0);
        assertEquals(longitude, newCoordinate.getLon(), 0.0);
    }

    @Test(expected = AssertionError.class)
    public void emptyLatitudeThrowsException() {
        Coordinate.builder()
                .longitude(longitude)
                .build();
    }

    @Test(expected = AssertionError.class)
    public void emptyLongitudeThrowsException() {
        Coordinate.builder()
                .latitude(latitude)
                .build();
    }

    @Test(expected = AssertionError.class)
    public void negativeLatitudeThrowsException() {
        Coordinate.builder()
                .latitude(-1.0)
                .longitude(longitude)
                .build();
    }

    @Test(expected = AssertionError.class)
    public void negativeLongitudeThrowsException() {
        Coordinate.builder()
                .latitude(latitude)
                .longitude(-1.0)
                .build();
    }

}
