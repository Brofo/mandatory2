package event;

import customer.Customer;

public class StartCheckout extends Event {

    private Customer cus;

    public StartCheckout(Customer cus) {
        super (cus.getStartCheckoutQueueTime() + cus.getCheckoutQueueTime());
        this.cus = cus;
    }

    @Override
    public String getEventInfo() {
        int cusID = cus.getId();
        return "Event type [StartCheckout]       Customer [" + cusID + "]      Current Time: [" + getTime() + "]       " +
                "Checkout Queue Time: [" + cus.getCheckoutQueueTime() + "]";
    }

    @Override
    public Event nextEvent() {
        return new LeaveShop(cus);
    }

    @Override
    public int getTimeToCheckout() {
        return cus.getTimeToCheckout();
    }

    @Override
    public void setCheckoutQueueTime(int queueTime) {
        cus.setCheckoutQueueTime(queueTime);
    }
}
