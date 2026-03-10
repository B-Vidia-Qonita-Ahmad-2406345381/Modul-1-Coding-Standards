package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment(
                "payment-1",
                "VoucherCode",
                paymentData
        );

        assertEquals("payment-1", payment.getId());
        assertEquals("VoucherCode", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithStatus() {
        Payment payment = new Payment(
                "payment-1",
                "VoucherCode",
                paymentData,
                "SUCCESS"
        );

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(
                    "payment-1",
                    "VoucherCode",
                    paymentData,
                    "MEOW"
            );
        });
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = new Payment(
                "payment-1",
                "VoucherCode",
                paymentData
        );

        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = new Payment(
                "payment-1",
                "VoucherCode",
                paymentData
        );

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }
}