package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
        assertEquals(PaymentStatus.WAITING.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentWithStatus() {
        Payment payment = new Payment(
                "payment-1",
                "VoucherCode",
                paymentData,
                PaymentStatus.SUCCESS.getValue()
        );

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
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
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
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
    @Test
    void testSetStatusRejected() {
        Payment payment = new Payment(
                "payment-1",
                "VoucherCode",
                paymentData
        );

        payment.setStatus(PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}