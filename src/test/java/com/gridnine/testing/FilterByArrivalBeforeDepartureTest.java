
package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


public class FilterByArrivalBeforeDepartureTest {
    
    public FilterByArrivalBeforeDepartureTest() {
    }

    @Test
    public void TestFilterByArrivalBeforeDeparture_If() {
        LocalDateTime dep = LocalDateTime.now().plusHours(3);
        LocalDateTime ari = LocalDateTime.now().plusHours(1);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(dep, ari));

        Flight flight = new Flight(segments);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        boolean actual = new FilterByArrivalBeforeDeparture().filter(flights).isEmpty();

        assertEquals(true, actual);
    }

    @Test
    public void TestFilterByArrivalBeforeDeparture_removeFlightTriggersIf() {
        LocalDateTime dep = LocalDateTime.now().plusHours(1);
        LocalDateTime ari = LocalDateTime.now().plusHours(2);

        LocalDateTime dep2 = LocalDateTime.now().plusHours(4);
        LocalDateTime ari2 = LocalDateTime.now().plusHours(2);

        LocalDateTime dep3 = LocalDateTime.now().plusHours(5);
        LocalDateTime ari3 = LocalDateTime.now().plusHours(6);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(dep, ari));
        segments.add(new Segment(dep2, ari2));
        segments.add(new Segment(dep3, ari3));

        Flight flight = new Flight(segments);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        
        boolean actual = new FilterByArrivalBeforeDeparture().filter(flights).isEmpty();

        assertEquals(true, actual);
    }

   
    
    @Test
    public void testFilterByArrivalBeforeDeparture_IfOnLastEl(){
        LocalDateTime dep = LocalDateTime.now().plusHours(1);
        LocalDateTime ari = LocalDateTime.now().plusHours(2);
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(dep, ari));
        for (int i = 0; i < 1000; i++) {
            segments.add(new Segment(dep, ari));
        }
        segments.add(new Segment(dep.plusHours(2), ari.minusHours(7)));
        
        Flight flight = new Flight(segments);
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            flights.add(flight);
        }
        
        int actual = new FilterByArrivalBeforeDeparture().filter(flights).size();
        int expected = 0;
        assertEquals(expected, actual);
    }
    
    
    
    @Test
    public void TestFilterByArrivalBeforeDeparture_defaultReturn() {
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime ari = LocalDateTime.now().plusHours(2);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(dep, ari));

        Flight flight = new Flight(segments);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        Flight actual = new FilterByArrivalBeforeDeparture().filter(flights).get(0);
        assertEquals(flight, actual);

    }
    
    @Test
    public void TestFilterByArrivalBeforeDeparture_0Elements() {
//        
        List<Flight> flights = new ArrayList<>();
//        
        
        int actual = new FilterByArrivalBeforeDeparture().filter(flights).size();
        assertEquals(0, actual);

    }
    
    
    
    @Test
    public void filterByArrivalBeforeDeparture_null() {
//        
        List<Flight> flights = null;
//        
        
        int actual = new FilterByArrivalBeforeDeparture().filter(flights).size();
        assertEquals(0, actual);

    }
    
}
