package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
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

    public Payment(String id, String method, Map<String,String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = "WAITING";
    }
    public Payment(String id, String method, Map<String,String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        String[] statusList = {"WAITING",
                "SUCCESS",
                "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }
    public void setStatus(String status) {
        String[] statusList = {"WAITING",
                "SUCCESS",
                "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }
}

