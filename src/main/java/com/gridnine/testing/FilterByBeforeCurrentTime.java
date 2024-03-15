package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterByBeforeCurrentTime implements Filter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        if (flights == null) {
            return new ArrayList<>();
        }
        ArrayList<Flight> flightsAry = new ArrayList<>(flights);
        Iterator<Flight> flightsIterator = flightsAry.iterator();
        while (flightsIterator.hasNext()) {
            List<Segment> segments = flightsIterator.next().getSegments();
            Iterator<Segment> iterator = segments.iterator();
            while (iterator.hasNext()) {
                Segment next = iterator.next();
                if (next.getDepartureDate().isBefore(LocalDateTime.now())) {
//                    System.out.println("Вылет до текущего времени -" + next);
                    flightsIterator.remove();
                    break;
                }
            }
        }
        return flightsAry;
    }

}
