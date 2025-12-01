package com.studfees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.studfees.entity.StudentFees;              
import com.studfees.entity.StudentPayment;
import com.studfees.repository.StudentRepository;  

@Service
public class StudentPaymentService {

    @Autowired
    com.studfees.repository.StudentPaymentREpository paymentRepo;

    @Autowired
    StudentRepository studentRepo; 
    public String addPayment(StudentPayment data) {

        Long id = data.getStudent().getId();

        StudentFees student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found!"));

        data.setStudent(student);

        
        double pending = data.getTotalFee() - data.getPaidFee();
        data.setPendingFee(pending);

        paymentRepo.save(data);

        return "Payment recorded, pending fee = " + pending;
    }
 
    public String updatePayment(Long paymentId, double newPaidFee) {
        StudentPayment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment record not found!"));

        payment.setPaidFee(newPaidFee);
        payment.setPendingFee(payment.getTotalFee() - newPaidFee);

        paymentRepo.save(payment);

        return "Payment updated, pending fee = " + payment.getPendingFee();
    }

    public String addPartialPayment(Long paymentId, double amount) {
        StudentPayment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment record not found!"));

        double newPaid = payment.getPaidFee() + amount;
        payment.setPaidFee(newPaid);
        payment.setPendingFee(payment.getTotalFee() - newPaid);

        paymentRepo.save(payment);

        return "Partial payment added, pending fee = " + payment.getPendingFee();
    }

    public List<StudentPayment> getPaymentsByStudent(Long studentId) {
        StudentFees student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found!"));

        return paymentRepo.findByStudent(student);
    }
    public List<StudentPayment> getAllPayments() {
        return paymentRepo.findAll();
    }

    // ======================
    // 6️⃣ Get Pending Payments
    // ======================
    public List<StudentPayment> getPendingPayments() {
        return paymentRepo.findAll().stream()
                .filter(p -> p.getPendingFee() > 0)
                .toList();
    }
}
    
    

