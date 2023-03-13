package BLL;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int orderId;
    private int clientId;
    private Date date;
    public static int curId = 0;

    public Order(int orderId, int clientId) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.date = new Date();
    }

    public String toString() {
        return orderId + " " + clientId + " " + date;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public Date getOrderDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order order = (Order) obj;
        return orderId == order.getOrderId() && clientId == order.getClientId() && Objects.equals(date, order.getOrderDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, date);
    }
}
