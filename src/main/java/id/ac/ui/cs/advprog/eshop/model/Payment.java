package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    private String orderId;
    public Payment(String id, String method, Map<String,String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = PaymentStatus.WAITING.getValue();
    }
    public Payment(String id, String method, Map<String,String> paymentData, String status) {
        this(id, method, paymentData);
        this.setStatus(status);
    }
    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

}

