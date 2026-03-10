package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {

        Payment payment = new Payment(order.getId(), method, paymentData);

        if (method.equalsIgnoreCase("Voucher")) {

            String code = paymentData.get("voucherCode");

            if (code != null &&
                    code.length() == 16 &&
                    code.startsWith("ESHOP") &&
                    countDigits(code) == 8) {

                payment.setStatus(PaymentStatus.SUCCESS.getValue());
            } else {
                payment.setStatus(PaymentStatus.REJECTED.getValue());
            }
        }

        else if (method.equalsIgnoreCase("COD")) {

            String address = paymentData.get("address");
            String fee = paymentData.get("deliveryFee");

            if (isEmpty(address) || isEmpty(fee)) {
                payment.setStatus(PaymentStatus.REJECTED.getValue());
            } else {
                payment.setStatus(PaymentStatus.WAITING.getValue());
            }
        }

        else if (method.equalsIgnoreCase("BankTransfer")) {

            String bank = paymentData.get("bankName");
            String ref = paymentData.get("referenceCode");

            if (isEmpty(bank) || isEmpty(ref)) {
                payment.setStatus(PaymentStatus.REJECTED.getValue());
            } else {
                payment.setStatus(PaymentStatus.WAITING.getValue());
            }
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {

        payment.setStatus(status);

        Order order = payment.getOrder();

        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            order.setStatus(OrderStatus.SUCCESS.getValue());
        }

        else if (status.equals(PaymentStatus.REJECTED.getValue())) {
            order.setStatus(OrderStatus.FAILED.getValue());
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private int countDigits(String value) {
        int count = 0;
        for (char c : value.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }
}