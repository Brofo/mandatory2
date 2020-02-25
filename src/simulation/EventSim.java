package simulation;
import customer.Customer;
import event.EnterCheckoutQueue;
import event.EnterShop;
import event.Event;
import event.TimeComparator;

import java.util.PriorityQueue;

public class EventSim {
    private PriorityQueue<Event> simEventQueue;
    private PriorityQueue<Event> checkoutQueue;

    private int AMOUNT_OF_CUSTOMERS = 5;

    public EventSim() {
        simEventQueue = new PriorityQueue<>(new TimeComparator());
        addCustomersToSim(AMOUNT_OF_CUSTOMERS - 1);
    }


    public void runSimulation() {
        while (!simEventQueue.isEmpty()) {
            Event e = simEventQueue.poll();
            if (e instanceof EnterCheckoutQueue) {
                runCheckoutSimulation(e);
            } else {
                System.out.println(e.getEventInfo());
                addEvent(e.nextEvent(), simEventQueue);
            }
        }
    }

    private void runCheckoutSimulation(Event e) {
        if (checkoutQueue.isEmpty()) {
            addEvent(e.nextEvent(), checkoutQueue);
        }
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

    private void addEvent(Event e, PriorityQueue q) {
        if (null == e)
            return;
        q.add(e);
    }
}
