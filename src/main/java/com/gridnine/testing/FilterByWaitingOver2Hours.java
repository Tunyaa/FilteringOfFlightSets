package com.gridnine.testing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterByWaitingOver2Hours implements Filter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        if (flights == null) {
            return new ArrayList<>();
        }
        List<Flight> flightsAry = new ArrayList<>(flights);
        Iterator<Flight> flightsIter = flightsAry.iterator();
        while (flightsIter.hasNext()) {
            Flight flight = flightsIter.next();
            List<Segment> segments = flight.getSegments();
            if (segments.size() > 1) {
                for (int i = 1; i < segments.size(); i++) {
                    if (segments.get(i - 1).getArrivalDate().plusHours(2).isBefore(segments.get(i).getDepartureDate())) {
//                        System.out.println("Интервал между прилетом и вылетом более 2 часов" + segments.get(i));
                        flightsIter.remove();
                        break;
                    }
                }

            }

        }
        return flightsAry;
    }

}
