package event;

import java.util.Comparator;

public class TimeComparator
            implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        return e1.time - e2.time;
    }
}
