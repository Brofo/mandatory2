package event;

import customer.Customer;

public class FinishShopping extends Event {
    private Customer cus;

    public FinishShopping(Customer cus) {
        super(cus.getFinishedShoppingTime());
        this.cus = cus;
    }

    @Override
    public String getEventInfo() {
        int cusID = cus.getId();
        return "Event type [FinishShopping]       Customer [" + cusID + "]      Current Time: [" + getTime() + "]";
    }

    @Override
    public Event nextEvent() {
        return new EnterCheckoutQueue(cus);
    }

    @Override
    public void setCheckoutQueueTime(int queueTime) {
        cus.setCheckoutQueueTime(queueTime);
    }
}
