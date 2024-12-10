package vttp.ssf.PizzaAssessment.services;

import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vttp.ssf.PizzaAssessment.models.Delivery;
import vttp.ssf.PizzaAssessment.models.Order;
import vttp.ssf.PizzaAssessment.repositories.PizzaRepository;

import java.util.UUID;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepo;

    public void saveOrderDetails(Order order, Delivery delivery) {
        pizzaRepo.saveOrderDetails(order, delivery);
    }

    public void savePizzaDetails(Order order){
        pizzaRepo.savePizzaDetails(order);
    }

    public Order getPizzaDetails(String orderId){
        return pizzaRepo.getPizzaById(orderId);
    }

    public JsonObject getOrderDetails(String orderId){
        return pizzaRepo.getOrderDetails(orderId);
    }

    public Order createOrderFromSession(HttpSession session) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        Order order = new Order();
        order.setOrderId(orderId);
        session.setAttribute("orderId", orderId);
        return order;
    }

}
