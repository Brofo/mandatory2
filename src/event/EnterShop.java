package event;
import customer.Customer;



public class EnterShop extends Event {
    private Customer cus;

    public EnterShop(Customer cus) {
        super(cus.getStartTime());
        this.cus = cus;
    }

    @Override
    public String getEventInfo() {
        int cusID = cus.getId();
        return "Event type [EnterShop]       Customer [" + cusID + "]      Current Time: [" + getTime() + "]";
    }

    @Override
    public Event nextEvent() {
        return new FinishShopping(cus);
    }

    @Override
    public void setCheckoutQueueTime(int queueTime) {
        cus.setCheckoutQueueTime(queueTime);
    }
}
