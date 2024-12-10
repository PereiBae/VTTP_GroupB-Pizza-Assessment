package vttp.ssf.PizzaAssessment.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vttp.ssf.PizzaAssessment.models.Delivery;
import vttp.ssf.PizzaAssessment.models.Order;
import vttp.ssf.PizzaAssessment.services.PizzaService;
import vttp.ssf.PizzaAssessment.utils.PriceCalculator;


@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PizzaService pizzaSvc;

    @GetMapping()
    public String getPizza(Model model, HttpSession session) {
        Order order = pizzaSvc.createOrderFromSession(session);
        model.addAttribute("Order", order);
        return "pizzaView0";
    }

    @PostMapping("/pizza")
    public String postPizza(@Valid @ModelAttribute Order order, BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            model.addAttribute("Order", order); // Re-bind Order to the model
            return "pizzaView0"; // Return the view with errors
        }

        if (order.getOrderId() == null) {
            order.setOrderId(session.getAttribute("orderId").toString());
        }

        session.setAttribute("orderDetails", order);
        System.out.println("Order details have been saved in session");
        System.out.println(session.getAttribute("orderDetails").toString());
        Delivery delivery = new Delivery();
        delivery.setOrderId(order.getOrderId());
        model.addAttribute("Delivery", delivery);

        return "pizzaView1";
    }

    @PostMapping("/pizza/order")
    public String postDelivery(@Valid @ModelAttribute Delivery delivery, BindingResult bindingResult, Model model, HttpSession session) {

        int rush=0;

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            model.addAttribute("Delivery", delivery); // Re-bind Delivery to the model
            return "pizzaView1"; // Return the view with errors
        }

        // Retrieve Order from session
        Order order = (Order) session.getAttribute("orderDetails");
        if (order == null) {
            return "redirect:/test"; // Redirect to the first step to restart the process if session expires
        }

        System.out.println(order);

        // Assign Delivery to session
        session.setAttribute("deliveryDetails", delivery);

        // Check if delivery is rush and assigning a value to calculate cost
        if(delivery.isDeliveryPriority()){
            rush =2;
        }

        model.addAttribute("orderId", session.getAttribute("orderId").toString() );
        model.addAttribute("address", delivery.getDeliveryAddress());
        model.addAttribute("rush", rush);
        model.addAttribute("pizzaCost", PriceCalculator.pizzaPrice(order));
        model.addAttribute("totalCost", PriceCalculator.finalCost(order, delivery));
        model.addAttribute("deliveryPriority", delivery.isDeliveryPriority());

        pizzaSvc.saveOrderDetails(order,delivery);
        System.out.println("Order details saved");

        return "pizzaView2";
    }

}
