package event;


public abstract class Event {
    public final int time;

    public Event(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public abstract String getEventInfo();

    public abstract Event nextEvent();
}
