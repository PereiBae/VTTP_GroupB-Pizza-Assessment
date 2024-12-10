package vttp.ssf.PizzaAssessment.repositories;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import vttp.ssf.PizzaAssessment.models.Delivery;
import vttp.ssf.PizzaAssessment.models.Order;
import vttp.ssf.PizzaAssessment.utils.PriceCalculator;

import java.io.StringReader;

@Repository
public class PizzaRepository {

    @Autowired
    @Qualifier("redisStringTemplate")
    private RedisTemplate<String, String> redisTemplate;

    public void saveOrderDetails(Order order, Delivery delivery) {
        System.out.println("Saving order details");
        String orderDetails = Json.createObjectBuilder()
                .add("orderId", order.getOrderId())
                .add("name", delivery.getDeliveryName())
                .add("address", delivery.getDeliveryAddress())
                .add("phone", delivery.getDeliveryPhoneNumber())
                .add("rush", delivery.isDeliveryPriority())
                .add("comments", delivery.getDeliveryComments())
                .add("pizza", order.getPizzaName())
                .add("size", order.getPizzaSize())
                .add("quantity", order.getNumberOfPizzas())
                .add("total", PriceCalculator.finalCost(order,delivery))
                .build().toString();
        System.out.println(orderDetails);
        redisTemplate.opsForHash().put("Final Orders",order.getOrderId(), orderDetails);
        System.out.println("Order details saved");
    }

    public void savePizzaDetails(Order order) {
        System.out.println("Saving pizza details");
        String pizzaDetails = Json.createObjectBuilder()
                .add("orderId", order.getOrderId())
                .add("pizza", order.getPizzaName())
                .add("size", order.getPizzaSize())
                .add("quantity", order.getNumberOfPizzas())
                .build().toString();
        System.out.println("Saving order: " + order.getOrderId());
        redisTemplate.opsForHash().put("Pizza Orders",order.getOrderId(), pizzaDetails);
        System.out.println("Pizza details saved");
    }

    public Order getPizzaById(String orderId) {
        System.out.printf("Getting pizza details for orderId: %s\n", orderId);
        String fetchedData = (String) redisTemplate.opsForHash().get("Pizza Orders",orderId);

        if (fetchedData == null) {
            System.out.printf("No listing found for id: %s%n", orderId);
            return null;
        }

        System.out.println("JsonString fetched: " + fetchedData);

        JsonObject jsonObject = Json.createReader(new StringReader(fetchedData)).readObject();

        Order pizzaOrder = new Order();
        pizzaOrder.setOrderId(jsonObject.getString("orderId"));
        pizzaOrder.setPizzaName(jsonObject.getString("pizza"));
        pizzaOrder.setPizzaSize(jsonObject.getString("size"));
        pizzaOrder.setNumberOfPizzas(jsonObject.getInt("quantity"));

        System.out.println("Pizza details fetched: " + pizzaOrder.toString());

        return pizzaOrder;
    }

    public JsonObject getOrderDetails(String orderId) {
        System.out.printf("Getting order details for orderId: %s\n", orderId);
        String fetchedData = (String) redisTemplate.opsForHash().get("Final Orders",orderId);
        if (fetchedData == null) {
            System.out.printf("No listing found for id: %s%n", orderId);
            return null;
        }

        return Json.createReader(new StringReader(fetchedData)).readObject();
    }

}
