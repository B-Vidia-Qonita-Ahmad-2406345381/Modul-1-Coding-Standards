package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    Order order;

    @BeforeEach
    void setUp() {
        order = new Order(
                "order1",
                new ArrayList<>(),
                1708570000L,
                "Safira"
        );
    }

    @Test
    void testAddPaymentVoucherValid() {

        Map<String,String> data = new HashMap<>();
        data.put("voucherCode","ESHOP1234ABC5678");

        Payment payment = new Payment("1","Voucher",data,
                PaymentStatus.SUCCESS.getValue());

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order,"Voucher",data);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
    }

    @Test
    void testAddPaymentVoucherInvalid() {

        Map<String,String> data = new HashMap<>();
        data.put("voucherCode","INVALID");

        Payment payment = new Payment("1","Voucher",data,
                PaymentStatus.REJECTED.getValue());

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order,"Voucher",data);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
    }

    @Test
    void testSetStatusSuccess() {

        Payment payment = new Payment("1","Voucher",new HashMap<>());

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        paymentService.setStatus(payment, order, PaymentStatus.SUCCESS.getValue());

        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testSetStatusRejected() {

        Payment payment = new Payment("1","Voucher",new HashMap<>());

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        paymentService.setStatus(payment, order, PaymentStatus.REJECTED.getValue());

        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testGetPaymentIfFound() {

        Payment payment = new Payment("1","Voucher",new HashMap<>());

        doReturn(payment).when(paymentRepository).findById("1");

        Payment result = paymentService.getPayment("1");

        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfNotFound() {

        doReturn(null).when(paymentRepository).findById("MEOW");

        Payment result = paymentService.getPayment("MEOW");

        assertNull(result);
    }

    @Test
    void testGetAllPayments() {

        List<Payment> payments = new ArrayList<>();

        payments.add(new Payment("1","Voucher",new HashMap<>()));
        payments.add(new Payment("2","Voucher",new HashMap<>()));

        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();

        assertEquals(2, results.size());
    }
}