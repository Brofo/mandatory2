package customer;
import java.util.Random;

/**
 * The customer will go through 5 events:
 * - Entering a shop, start shopping products.
 * - Finish shopping products.
 * - Find the checkoutpoint and join the checkout Queue.
 * - Have the products scanned, and pay for the products.
 * - Leave the store.
 */
public class Customer {
    private Random r;

    //Customer's ID / Name:
    private int id;

    //Product and action related variables
    private static final int MIN_NUM_PRODUCTS = 1;
    private static final int MAX_NUM_PRODUCTS = 100;
    private int numProducts;
    private static final int PICK_PRODUCT_TIME = 15;
    private static final int MIN_WALK_TO_CHECKOUT_QUEUE_TIME = 5;
    private static final int MAX_WALK_TO_CHECKOUT_QUEUE_TIME = 50;
    private int walkToCheckoutQueueTime;
    private static final int SCAN_TIME = 2;
    private static final int PAY_TIME = 10;

    //Timestamp variables
    private static final int START_TIME = 0; //The timer starts after a customer enters the shop.
    private int finishedShoppingTime; //The time it took for a customer to shop products.
    private int startCheckoutQueueTime; //The time the customer enters a checkout queue.
    private int startCheckoutTime; //The customer is at the head of the queue and products are getting scanned and paid for.
    private int leaveStoreTime; //The customer has finished scanning and paying for products, and leaves the store.


    public Customer(int id) {
        this.id = id;
        r = new Random();
        numProducts = getRandomNumberBetween(MIN_NUM_PRODUCTS, MAX_NUM_PRODUCTS);
        walkToCheckoutQueueTime = getRandomNumberBetween(MIN_WALK_TO_CHECKOUT_QUEUE_TIME, MAX_WALK_TO_CHECKOUT_QUEUE_TIME);
        finishedShoppingTime = START_TIME + (numProducts * PICK_PRODUCT_TIME);
        startCheckoutQueueTime = finishedShoppingTime + walkToCheckoutQueueTime;

    }

    public int getStartTime() {
        return START_TIME;
    }

    public int getFinishedShoppingTime() {
        return finishedShoppingTime;
    }

    public int getStartCheckoutQueueTime() {
        return startCheckoutQueueTime;
    }

    public int getStartCheckoutTime() {
        return startCheckoutTime;
    }

    public int getLeaveStoreTime() {
        return leaveStoreTime;
    }

    public int getId() {
        return id;
    }

    private int getRandomNumberBetween(int lowestValue, int highestValue) {
        return r.nextInt(highestValue) + lowestValue;
    }
}
