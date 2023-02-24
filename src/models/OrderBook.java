package models;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class OrderBook {

    private Map<Double, List<Order>> bids;
    private Map<Double, List<Order>> asks;

    public OrderBook() {
        this.bids = new TreeMap<>(Comparator.reverseOrder());
        this.asks = new TreeMap<>();
    }

    public Order addOrder(Order order) {
        Map<Double, List<Order>> book = order.getSide().equals("BUY") ? bids : asks;
        book.computeIfAbsent(order.getPrice(), k -> new LinkedList<>()).add(order);
        updateOrderPriorities(book.get(order.getPrice()));
        return  order;
    }

    public void deleteOrder(Order order) {

        Map<Double, List<Order>> book = order.getSide().equals("BUY") ? bids : asks;
        List<Order> orders = book.get(order.getPrice());
        if (orders != null) {
            orders.remove(order);
            if (orders.isEmpty()) {
                book.remove(order.getPrice());
            } else {
                updateOrderPriorities(orders);
            }
        }
    }

    public Order modifyOrder(Order order, double newPrice, int newQuantity) {
        deleteOrder(order);
        order.setPrice(newPrice);
        order.setQuantity(newQuantity);
        addOrder(order);
        return order;
    }

    private void updateOrderPriorities(List<Order> orders) {
        orders.sort(Comparator.comparingLong(Order::getPriority));
        long priority = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        for (Order order : orders) {
            order.setPriority(priority++);
        }
    }

    public void print() {
        System.out.println("BIDS:");
        bids.forEach((price, orders) -> {
            System.out.printf("%f - %s\n", price, orders);
        });
        System.out.println("ASKS:");
        asks.forEach((price, orders) -> {
            System.out.printf("%f - %s\n", price, orders);
        });
    }

}
