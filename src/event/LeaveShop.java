package event;

import customer.Customer;

public class LeaveShop extends Event {

    private Customer cus;

    public LeaveShop(Customer cus) {
        super(cus.getLeaveStoreTime());
        this.cus = cus;
    }


    @Override
    public void setCheckoutQueueTime(int queueTime) {

    }

    @Override
    public String getEventInfo() {
        int cusID = cus.getId();
        return "Event type [LeaveShop]       Customer [" + cusID + "]      Current Time: [" + getTime() + "]";
    }

    @Override
    public int getTimeToCheckout() {
        return cus.getTimeToCheckout();
    }

    @Override
    public Event nextEvent() {
        return null;
    }
}
