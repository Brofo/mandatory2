package simulation;
import customer.Customer;
import event.EnterCheckoutQueue;
import event.EnterShop;
import event.Event;
import event.TimeComparator;
import statistics.Statistics;

import java.util.PriorityQueue;

public class EventSim {
    private PriorityQueue<Event> simEventQueue;
    private Statistics stats;

    private final int AMOUNT_OF_CUSTOMERS = 10;

    public EventSim() {
        simEventQueue = new PriorityQueue<>(new TimeComparator());
        addCustomersToSim(AMOUNT_OF_CUSTOMERS - 1);
        stats = new Statistics(AMOUNT_OF_CUSTOMERS);
    }


    public void runSimulation() {
        while (!simEventQueue.isEmpty()) {
            Event e = simEventQueue.poll();
            if (e instanceof EnterCheckoutQueue) {
                calculateCheckoutQueueTime(e);
            }

            System.out.println(e.getEventInfo());
            addEvent(e.nextEvent());
        }
    }

    public Statistics getSimStats() {
        return this.stats;
    }

    /**
     * Method will calculate the queue time for each customer based on how many customers
     * are checking out.
     */
    private void calculateCheckoutQueueTime(Event e) {
        int totalQueueTime = 0;

        for (Event qe : simEventQueue) {
            int extraTime = qe.getTimeToCheckout(); //Will only return more than 0 if the element is a StartCheckout Event.
                                                    //Or a LeaveStore event.
            totalQueueTime = totalQueueTime + extraTime;
        }
        e.setCheckoutQueueTime(totalQueueTime);
        stats.addCustomerQueueTime(totalQueueTime);
    }


    /**
     * Adds the amount of customers that will participate in the simulation.
     */

    private void addCustomersToSim(int amountOfCustomers) {
        int i = 0;
        while (i <= amountOfCustomers) {
            simEventQueue.add(new EnterShop(new Customer(i))); // The 'i' variable is used to give the Customer an ID.
            i++;
        }
    }

    private void addEvent(Event e) {
        if (null == e)
            return;
        simEventQueue.add(e);
    }
}
