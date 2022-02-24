public class OrderContent {
    int id;
    int shoeId;
    int orderId;
    int quantity;

    OrderContent(int id, int shoeId, int orderId, int quantity){
        this.id = id;
        this.shoeId = shoeId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getShoeId() {
        return shoeId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderContent{" +
                "id=" + id +
                ", shoeId=" + shoeId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                '}';
    }
}
