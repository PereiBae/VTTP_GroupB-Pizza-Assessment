package vttp.ssf.PizzaAssessment.models;

import jakarta.validation.constraints.*;

public class Order {

    @NotEmpty(message = "You must choose a pizza")
    @NotNull(message = "Please choose a pizza")
    private String pizzaName;

    @NotEmpty(message = "You must choose a size for your pizza")
    @NotNull(message = "Please choose the size of your pizza")
    private String pizzaSize;

    @Min(value = 1, message = "You must order at least 1 pizza.")
    @Max(value = 10, message = "You cannot order more than 10 pizzas.")
    @NotNull(message = "Please indicate how many pizza's you would like to order")
    private int numberOfPizzas;

    private String OrderId;

    public Order() {
    }

    public Order(String pizzaName, String pizzaSize, int numberOfPizzas) {
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
        this.numberOfPizzas = numberOfPizzas;
    }


    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public int getNumberOfPizzas() {
        return numberOfPizzas;
    }

    public void setNumberOfPizzas(int numberOfPizzas) {
        this.numberOfPizzas = numberOfPizzas;
    }

    public String getOrderId() {
        return OrderId;
    }
    public void setOrderId(String orderId) {
        OrderId = orderId;
    }
}
