package statistics;

import java.util.ArrayList;

public class Statistics {
    private int amountOfCustomers;
    private int customerQueueTime;
    private int maxQueueTime;
    private int amountOfCheckouts;

    public Statistics(int amountOfCustomers, int amountOfCheckouts) {
        this.amountOfCheckouts = amountOfCheckouts;
        this.amountOfCustomers = amountOfCustomers;
        customerQueueTime = 0;
        maxQueueTime = 0;
    }

    public void addCustomerQueueTime(int time) {
        customerQueueTime = customerQueueTime + time;
        if (time > maxQueueTime) {
            maxQueueTime = time;
        }
    }

    public void printStats() {
        int averageQueueTime = (customerQueueTime / amountOfCustomers);

        System.out.println("\n----------------------------Supermarket Statistics----------------------------");
        System.out.println("Number of customers in simulation:  [" + amountOfCustomers + "]");
        System.out.println("Number of checkout points available:  [" + amountOfCheckouts + "]");
        System.out.println("Average checkout queue time:  [" + averageQueueTime + "] seconds");
        System.out.println("Longest checkout queue time:  [" + maxQueueTime + "] seconds");
        System.out.println("--------------------------------------------------------------------------------");

    }
}
