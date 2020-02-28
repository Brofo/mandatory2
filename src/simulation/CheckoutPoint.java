package simulation;

import event.Event;
import event.TimeComparator;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * This class handles the checkout points a customer can "choose" when
 * they want to scan and pay for their products.
 */
public class CheckoutPoint {

    private ArrayList<PriorityQueue<Event>> checkoutsInStore;
    private int numberOfCheckouts;

    public CheckoutPoint(int numberOfCheckouts) {
        this.numberOfCheckouts = numberOfCheckouts;
        this.checkoutsInStore = new ArrayList<>();

        //Add Priority Queues to the ArrayList, depending on how many checkouts we want.
        int i = 0;
        while(i < numberOfCheckouts) {
            assert false;
            checkoutsInStore.add(new PriorityQueue<>(new TimeComparator()));
            i++;
        }
    }


    /**
     * Count how many customers are currently checking out.
     * When running the simulation in EventSim, we need to know if there are any
     * customers currently checking out, to know if the simulation should continue.
     * @return - Total amount of customers checking out.
     */
    public int customersInCheckout() {
        int amountOfCustomers = 0;
        for (PriorityQueue<Event> pq : checkoutsInStore) {
            for (Event e : pq) {
                amountOfCustomers++;
            }
        }
        return amountOfCustomers;
    }

    public PriorityQueue<Event> findShortestQueue() {
        int i = 0;
        int shortestCheckoutQueue = 0;
        int lowestAmountOfCustomers = Integer.MAX_VALUE;
        while (i < checkoutsInStore.size()) {
            PriorityQueue<Event> pq = checkoutsInStore.get(i);
            int customersInQueue = pq.size();
            if (customersInQueue < lowestAmountOfCustomers) {
                lowestAmountOfCustomers = customersInQueue;
                shortestCheckoutQueue = i;
            }
            i++;
        }
        return checkoutsInStore.get(shortestCheckoutQueue);

    }

    /**
     * Adds the next event to the shortest checkout queue.
     * @param e - The next event to add.
     */
    public void addEventToCheckoutQueue(Event e) {
        if (null == e)
            return;

         PriorityQueue<Event> pq = findShortestQueue();
         pq.add(e);
    }

    public ArrayList<PriorityQueue<Event>> getCheckoutsInStore() {
        return checkoutsInStore;
    }
}
