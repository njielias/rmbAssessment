package models;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Order implements Comparable<Order> {

    private int id;
    private double price;
    private int quantity;
    private String side;
    private long priority;
    private LocalDateTime createdDate;

    public Order() {

    }

    public Order(int id, double price, int quantity, String side) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.priority = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.createdDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSide() {
        return side;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String toString() {
        return String.format("(%d, %f, %d, %s, %d, %s)", id, price, quantity, side, priority, createdDate);
    }

    @Override
    public int compareTo(Order o) {
        if (this.getPriority() < o.getPriority()) {
            return -1;
        } else if (this.getPriority() > o.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }

}
