package test;

import models.Order;
import models.OrderBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderBookTest {

    Order order;
    long oldPriority;
    @Before
    public void loadOrder() {
        order = new Order(1, 30, 300, "BIDS");
    }

    @Test
    public void addOrder() {
        OrderBook orderBook = new OrderBook();
        Order order = orderBook.addOrder(this.order);
        oldPriority = order.getPriority();
        orderBook.print();
        Assert.assertEquals(this.order.getId(), 1l);
    }

    @Test
    public void updateOrder() {
        OrderBook orderBook = new OrderBook();
        Order modifyOrder = orderBook.modifyOrder(order, 500, 5);
        Assert.assertNotEquals(oldPriority, modifyOrder.getPriority());
    }
   @Test
    public void deleteOrder() {
        OrderBook orderBook = new OrderBook();
       orderBook.deleteOrder(order);
}
}
