package vttp.ssf.PizzaAssessment.controllers;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vttp.ssf.PizzaAssessment.models.Delivery;
import vttp.ssf.PizzaAssessment.models.Order;
import vttp.ssf.PizzaAssessment.services.PizzaService;
import vttp.ssf.PizzaAssessment.utils.PriceCalculator;

import java.util.UUID;

@Controller
@RequestMapping
public class PizzaController {

    @Autowired
    private PizzaService pizzaSvc;


    @GetMapping
    public String getPizza( Model model ) {
        model.addAttribute("Order", new Order());
        return "view0";
    }

    @PostMapping("/pizza")
    public String postPizza(@Valid @ModelAttribute Order order, BindingResult bindingResult, Model model, Delivery delivery) {

        if( bindingResult.hasErrors() ) {
            model.addAttribute("Order", order);
            return "view0";
        }

        String id = UUID.randomUUID().toString().substring(0, 8);
        if (order.getOrderId() == null) {
            order.setOrderId(id);
        }

        pizzaSvc.savePizzaDetails(order);

        delivery.setOrderId(order.getOrderId()); // Set the orderId for the delivery object
        model.addAttribute("OrderId", order.getOrderId());
        model.addAttribute("Delivery", delivery);
        return "view1";

    }

    @PostMapping("/pizza/order")
    public String postDelivery(@Valid @ModelAttribute Delivery delivery, BindingResult bindingResult, Model model) {

        System.out.println("Received orderId: " + delivery.getOrderId());

        int rush=0;

        if( bindingResult.hasErrors() ) {
            model.addAttribute("Delivery", delivery);
            return "view1";
        }

        System.out.println(delivery.getOrderId());
        Order order = pizzaSvc.getPizzaDetails(delivery.getOrderId());

        double pizzaCost = PriceCalculator.pizzaPrice(order);

        if(delivery.isDeliveryPriority()){
            rush = 2;
        }

        model.addAttribute("OrderId", order.getOrderId());
        model.addAttribute("address", delivery.getDeliveryAddress());
        model.addAttribute("pizzaCost", pizzaCost);
        model.addAttribute("rush", rush);
        model.addAttribute("totalCost", PriceCalculator.finalCost(order, delivery));

        pizzaSvc.saveOrderDetails(order, delivery);
        System.out.println("Order details saved");

        return "view2";
    }

//    @GetMapping("/order/{orderId}")
//    @ResponseBody
//    public ResponseEntity<String> getOrderId(@PathVariable String orderId, Model model) {
//        JsonObject orderDetails = pizzaSvc.getOrderDetails(orderId);
//        if (pizzaSvc.getPizzaDetails(orderId) == null) {
//            JsonObject notFound = Json.createObjectBuilder()
//                    .add("message", "Order " + orderId + " not found")
//                    .build();
//            return ResponseEntity.ok(notFound.toString());
//        }
//        return ResponseEntity.ok(orderDetails.toString());
//    }

}
