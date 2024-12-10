package vttp.ssf.PizzaAssessment.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Delivery {

    @NotEmpty(message = "Your name must be at least 3 characters long")
    @Size(min = 3, message = "Your name must be at least 3 characters long")
    private String deliveryName;
    @NotEmpty (message = "Address cannot be empty")
    private String deliveryAddress;
    @Size(min = 8, max = 8, message = "Your phone number must be 8 digits")
    private String deliveryPhoneNumber;
    private boolean deliveryPriority;
    private String deliveryComments;
    private String orderId;

    public Delivery() {
    }

    public Delivery(String deliveryName, String deliveryAddress, String deliveryPhoneNumber, boolean deliveryPriority) {
        this.deliveryName = deliveryName;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPhoneNumber = deliveryPhoneNumber;
        this.deliveryPriority = deliveryPriority;
    }

    public Delivery(String deliveryName, String deliveryAddress, String deliveryPhoneNumber, boolean deliveryPriority, String deliveryComments) {
        this.deliveryName = deliveryName;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPhoneNumber = deliveryPhoneNumber;
        this.deliveryPriority = deliveryPriority;
        this.deliveryComments = deliveryComments;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPhoneNumber() {
        return deliveryPhoneNumber;
    }

    public void setDeliveryPhoneNumber(String deliveryPhoneNumber) {
        this.deliveryPhoneNumber = deliveryPhoneNumber;
    }

    public boolean isDeliveryPriority() {
        return deliveryPriority;
    }

    public void setDeliveryPriority(boolean deliveryPriority) {
        this.deliveryPriority = deliveryPriority;
    }

    public String getDeliveryComments() {
        return deliveryComments;
    }

    public void setDeliveryComments(String deliveryComments) {
        this.deliveryComments = deliveryComments;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryName='" + deliveryName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryPhoneNumber='" + deliveryPhoneNumber + '\'' +
                ", deliveryPriority=" + deliveryPriority +
                ", deliveryComments='" + deliveryComments + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
