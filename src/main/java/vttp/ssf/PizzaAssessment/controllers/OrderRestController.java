package vttp.ssf.PizzaAssessment.controllers;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vttp.ssf.PizzaAssessment.services.PizzaService;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    @Autowired
    private PizzaService pizzaSvc;

    @GetMapping(path = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOrder(@PathVariable String orderId) {
        // Retrieve the order using the service
        JsonObject order = pizzaSvc.getOrderDetails(orderId);

        if (order == null) {
            // Return Not Found with the custom message
            JsonObject notFoundResponse = Json.createObjectBuilder()
                    .add("message", "Order " + orderId + " not found")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse.toString());
        }

        // Return the order as JSON with OK status
        return ResponseEntity.ok(order.toString());
    }

}
