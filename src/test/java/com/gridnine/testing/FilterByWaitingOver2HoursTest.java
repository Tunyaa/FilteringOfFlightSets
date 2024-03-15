
package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


public class FilterByWaitingOver2HoursTest {
    
    public FilterByWaitingOver2HoursTest() {
    }
    
    @Test//if не срабатывает
    public void testfilterByWaitingOver2Hours_defaultReturn() {
        LocalDateTime Departure = LocalDateTime.now().plusHours(1);
        LocalDateTime Arrival = LocalDateTime.now().plusHours(3);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(Departure, Arrival));
        Flight expected = new Flight(segments);
        Flight actual = new FilterByWaitingOver2Hours().filter(Arrays.asList(expected)).get(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testfilterByWaitingOver2Hours_IfOnLastEl(){
        LocalDateTime dep = LocalDateTime.now().plusHours(1);
        LocalDateTime ari = LocalDateTime.now().plusHours(2);
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(dep, ari));
        for (int i = 0; i < 1000; i++) {
            segments.add(new Segment(dep, ari));
        }
        segments.add(new Segment(dep.plusHours(6), ari));
        
        Flight flight = new Flight(segments);
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            flights.add(flight);
        }
        
        int actual = new FilterByWaitingOver2Hours().filter(flights).size();
        int expected = 0;
        assertEquals(expected, actual);
    }
    
    @Test
    public void filterByWaitingOver2Hours_null() {
//        
        List<Flight> flights = null;
//        
        
        int actual = new FilterByWaitingOver2Hours().filter(flights).size();
        assertEquals(0, actual);

    }
    
    @Test
        public void filterByWaitingOver2Hours_0Elements() {
//        
        List<Flight> flights = new ArrayList<>();
//        
        
        int actual = new FilterByWaitingOver2Hours().filter(flights).size();
        assertEquals(0, actual);

    }
    
}
