
package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main {

    public static void main(String[] args) {
         List<Flight> createFlights = new ArrayList<>(FlightBuilder.createFlights());
        for (int i = 0; i < createFlights.size(); i++) {
            System.out.print("Полёт " + (i + 1) + " ");
            List<Segment> segments = createFlights.get(i).getSegments();

            System.out.println(segments);

        }        

        System.out.println("\nВылет до текущего времени");
        List<Flight> filterByBeforeCurrentTime = new FilterByBeforeCurrentTime().filter(createFlights);
        for (Flight flight : filterByBeforeCurrentTime) {
            System.out.println(flight);
        }
        
        System.out.println("\nПрилёт до вылета");
        List<Flight> filterByArrivalBeforeDeparture = new FilterByArrivalBeforeDeparture().filter(createFlights);
        for (Flight flight : filterByArrivalBeforeDeparture) {
            System.out.println(flight);
        }
        
        System.out.println("\nИнтервал между прилетом и вылетом более 2 часов");
        List<Flight> filterByWaitingOver2Hours = new FilterByWaitingOver2Hours().filter(createFlights);
        for (Flight flight : filterByWaitingOver2Hours) {
            System.out.println(flight);
        }
        
    }
}
