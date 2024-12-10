package vttp.ssf.PizzaAssessment.services;

import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vttp.ssf.PizzaAssessment.models.Delivery;
import vttp.ssf.PizzaAssessment.models.Order;
import vttp.ssf.PizzaAssessment.repositories.PizzaRepository;

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

}
