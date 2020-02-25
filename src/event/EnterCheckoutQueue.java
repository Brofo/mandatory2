package event;

import customer.Customer;

public class EnterCheckoutQueue extends Event{
    private Customer cus;

    public EnterCheckoutQueue(Customer cus) {
        super(cus.getStartCheckoutQueueTime());
        this.cus = cus;
    }

    @Override
    public String getEventInfo() {
        int cusID = cus.getId();
        return "Event type [EnterCheckoutQueue]       Customer [" + cusID + "]      Current Time: [" + getTime() + "]";
    }

    @Override
    public Event nextEvent() {
        return new StartCheckout(cus);
    }

    public Customer returnCustomer() {
        return this.cus;
    }
}
