package vttp.ssf.PizzaAssessment.utils;

import vttp.ssf.PizzaAssessment.models.Delivery;
import vttp.ssf.PizzaAssessment.models.Order;

public class PriceCalculator {

    public static double finalCost(Order order, Delivery delivery) {
        int pizzaPrice = 0;
        if ("Bella".equals(order.getPizzaName()) || "Marinara".equals(order.getPizzaName()) || "Spianata Calabrese".equals(order.getPizzaName())) {
            pizzaPrice = 30;
            System.out.println("pizzaPrice: " + pizzaPrice);
        } else if ("Margherita".equals(order.getPizzaName())) {
            pizzaPrice = 22;
            System.out.println("pizzaPrice: " + pizzaPrice);
        } else if ("Trio Formaggio".equals(order.getPizzaName())) {
            pizzaPrice = 25;
            System.out.println("pizzaPrice: " + pizzaPrice);
        }

        double priceMultiplier=0;
        if ("Small".equals(order.getPizzaSize())) {
            priceMultiplier = 1;
            System.out.println("Price Multiplier: " + priceMultiplier + " for pizzaSize:" + order.getPizzaSize());
        } else if ("Medium".equals(order.getPizzaSize())) {
            priceMultiplier = 1.2;
            System.out.println("Price Multiplier: " + priceMultiplier + "f or pizzaSize:" + order.getPizzaSize());
        } else if ("Large".equals(order.getPizzaSize())) {
            priceMultiplier = 1.5;
            System.out.println("Price Multiplier: " + priceMultiplier + " for pizzaSize:" + order.getPizzaSize());
        }

        int rushOrder=0;
        if (delivery.isDeliveryPriority()) {
            rushOrder =  2;
        }

        return (priceMultiplier * pizzaPrice * order.getNumberOfPizzas()) + rushOrder;

    }

    public static double pizzaPrice(Order order) {
        int pizzaPrice = 0;
        if ("Bella".equals(order.getPizzaName()) || "Marinara".equals(order.getPizzaName()) || "Spianata Calabrese".equals(order.getPizzaName())) {
            pizzaPrice = 30;
            System.out.println("pizzaPrice: " + pizzaPrice);
        } else if ("Margherita".equals(order.getPizzaName())) {
            pizzaPrice = 22;
            System.out.println("pizzaPrice: " + pizzaPrice);
        } else if ("Trio Formaggio".equals(order.getPizzaName())) {
            pizzaPrice = 25;
            System.out.println("pizzaPrice: " + pizzaPrice);
        }

        double priceMultiplier = 0;
        if ("Small".equals(order.getPizzaSize())) {
            priceMultiplier = 1;
            System.out.println("Price Multiplier: " + priceMultiplier + " for pizzaSize:" + order.getPizzaSize());
        } else if ("Medium".equals(order.getPizzaSize())) {
            priceMultiplier = 1.2;
            System.out.println("Price Multiplier: " + priceMultiplier + " for pizzaSize:" + order.getPizzaSize());
        } else if ("Large".equals(order.getPizzaSize())) {
            priceMultiplier = 1.5;
            System.out.println("Price Multiplier: " + priceMultiplier + " for pizzaSize:" + order.getPizzaSize());
        }

        return (priceMultiplier * pizzaPrice * order.getNumberOfPizzas());
    }
}

