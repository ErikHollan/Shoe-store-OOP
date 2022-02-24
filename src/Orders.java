public class Orders {

    int id;
    int customerId;
    String orderDate;

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public Orders(int id, int customerId, String orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
