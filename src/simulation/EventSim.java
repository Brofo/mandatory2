package simulation;
import customer.Customer;
import event.*;
import statistics.Statistics;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class EventSim {
    private PriorityQueue<Event> simEventQueue;
    private Statistics stats;
    private CheckoutPoint checkoutPoint;

    //Change these values to test for different amount of customers and checkout points.
    private final int AMOUNT_OF_CUSTOMERS = 20;
    private final int AMOUNT_OF_CHECKOUT_POINTS = 3;

    public EventSim() {
        simEventQueue = new PriorityQueue<>(new TimeComparator());
        addCustomersToSim(AMOUNT_OF_CUSTOMERS - 1);
        stats = new Statistics(AMOUNT_OF_CUSTOMERS, AMOUNT_OF_CHECKOUT_POINTS);
        checkoutPoint = new CheckoutPoint(AMOUNT_OF_CHECKOUT_POINTS);

    }

    /**
     * Runs the simulation.
     */
    public void runSim() {
        while (!simEventQueue.isEmpty() || checkoutPoint.customersInCheckout() > 0) {
            Event e = pollEvent();
            if (e instanceof EnterShop || e instanceof FinishShopping) {
                System.out.println(e.getEventInfo());
                addEvent(e.nextEvent());
            }
            if (e instanceof EnterCheckoutQueue) {
                calculateCheckoutQueueTime(e);
                System.out.println(e.getEventInfo());
                checkoutPoint.addEventToCheckoutQueue(e.nextEvent());
            }
            if (e instanceof StartCheckout || e instanceof LeaveShop) {
                System.out.println(e.getEventInfo());
                checkoutPoint.addEventToCheckoutQueue(e.nextEvent());
            }
        }
    }

    /**
     * In order to use the poll() method for the simEventQueue and the queue
     * for the checkout points, it is necessary to temporarily put all events into
     * a singular queue, in order to find which event is the next.
     * @return The next event that happens in the correct chronological order.
     */
    private Event pollEvent() {
        ArrayList<PriorityQueue<Event>> allQueues = new ArrayList<>();
        allQueues.add(simEventQueue);
        allQueues.addAll(checkoutPoint.getCheckoutsInStore());

        PriorityQueue<Event> temporaryComparatorQueue = new PriorityQueue<>(new TimeComparator());
        for (PriorityQueue<Event> pq : allQueues) {
            temporaryComparatorQueue.addAll(pq);
        }
        Event tempEvent = temporaryComparatorQueue.poll();
        for (PriorityQueue<Event> pq : allQueues) {
            for (Event e : pq) {
                if (e.equals(tempEvent)) {
                    return pq.poll();
                }
            }
        }
        return null;
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
        PriorityQueue<Event> shortestQueue = checkoutPoint.findShortestQueue();

        for (Event qe : shortestQueue) {
            int extraTime = qe.getTimeToCheckout();
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
