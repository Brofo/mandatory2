package event;


public abstract class Event {
    public int time;

    public Event(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    /**
     * Only overridden in StartCheckout class.
     */
    public int getTimeToCheckout() {
        return 0;
    }

    public abstract void setCheckoutQueueTime(int queueTime);

    public abstract String getEventInfo();

    public abstract Event nextEvent();
}
