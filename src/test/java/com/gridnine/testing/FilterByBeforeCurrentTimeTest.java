
package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


public class FilterByBeforeCurrentTimeTest {
    
    public FilterByBeforeCurrentTimeTest() {
    }

     @Test//if не срабатывает
    public void testFilterByBeforeCurrentTime_defaultReturn() {
        LocalDateTime Departure = LocalDateTime.now().plusHours(1);
        LocalDateTime Arrival = LocalDateTime.now().plusHours(3);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(Departure, Arrival));
        Flight expected = new Flight(segments);
        Flight actual = new FilterByBeforeCurrentTime().filter(Arrays.asList(expected)).get(0);

        assertEquals(expected, actual);
    }

    @Test//if срабатывает, сегмент становится пустым
    public void testFilterByBeforeCurrentTime_If() {
        LocalDateTime Departure = LocalDateTime.now().minusHours(1);
        LocalDateTime Arrival = LocalDateTime.now().plusHours(3);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(Departure, Arrival));
        Flight flight = new Flight(segments);

        boolean actual = new FilterByBeforeCurrentTime().filter(Arrays.asList(flight)).isEmpty();

        assertEquals(true, actual);
    }

    @Test//if срабатывает, сегмент становится пустым
    public void testFilterByBeforeCurrentTime_removeFlightTriggersIf() {
        LocalDateTime Departure = LocalDateTime.now().plusHours(2);
        LocalDateTime Arrival = LocalDateTime.now().plusHours(3);

        LocalDateTime Departure2 = LocalDateTime.now().minusHours(1);
        LocalDateTime Arrival2 = LocalDateTime.now().plusHours(3);

        LocalDateTime Departure3 = LocalDateTime.now().plusHours(3);
        LocalDateTime Arrival3 = LocalDateTime.now().plusHours(5);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(Departure, Arrival));
        segments.add(new Segment(Departure2, Arrival2));
        segments.add(new Segment(Departure3, Arrival3));
        Flight flight = new Flight(segments);

        boolean actual = new FilterByBeforeCurrentTime().filter(Arrays.asList(flight)).isEmpty();

        assertEquals(true, actual);
    }
    
     @Test
    public void testFilterByBeforeCurrentTime_IfOnLastEl(){
        LocalDateTime dep = LocalDateTime.now().plusHours(1);
        LocalDateTime ari = LocalDateTime.now().plusHours(2);
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(dep, ari));
        for (int i = 0; i < 1000; i++) {
            segments.add(new Segment(dep, ari));
        }
        segments.add(new Segment(dep.minusHours(2), ari));
        
        Flight flight = new Flight(segments);
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            flights.add(flight);
        }
        
        int actual = new FilterByBeforeCurrentTime().filter(flights).size();
        int expected = 0;
        assertEquals(expected, actual);
    }
    
    @Test
    public void filterByBeforeCurrentTime_null() {
//        
        List<Flight> flights = null;
//        
        
        int actual = new FilterByBeforeCurrentTime().filter(flights).size();
        assertEquals(0, actual);

    }
    @Test
        public void testFilterByBeforeCurrentTime_0Elements() {
//        
        List<Flight> flights = new ArrayList<>();
//        
        
        int actual = new FilterByBeforeCurrentTime().filter(flights).size();
        assertEquals(0, actual);

    }
    
}
