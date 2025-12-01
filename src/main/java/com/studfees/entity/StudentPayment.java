package com.studfees.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class StudentPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id") // foreign key
    private StudentFees student;

    private Double totalFee;
    private Double paidFee;
    private Double pendingFee;

    private LocalDateTime paymentDate; // DATE+TIME

    // Auto-set date before saving record
    @PrePersist
    public void onCreate() {
        this.paymentDate = LocalDateTime.now();
    }

    // ------------------ Getters & Setters ------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StudentFees getStudent() { return student; }
    public void setStudent(StudentFees student) { this.student = student; }

    public Double getTotalFee() { return totalFee; }
    public void setTotalFee(Double totalFee) { this.totalFee = totalFee; }

    public Double getPaidFee() { return paidFee; }
    public void setPaidFee(Double paidFee) { this.paidFee = paidFee; }

    public Double getPendingFee() { return pendingFee; }
    public void setPendingFee(Double pendingFee) { this.pendingFee = pendingFee; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    // ------------------ Constructor ------------------

    public StudentPayment(Long id, StudentFees student, Double totalFee, Double paidFee, Double pendingFee) {
        this.id = id;
        this.student = student;
        this.totalFee = totalFee;
        this.paidFee = paidFee;
        this.pendingFee = pendingFee;
    }

    public StudentPayment() {}

    @Override
    public String toString() {
        return "StudentPayment [id=" + id + ", student=" + student + ", totalFee=" + totalFee +
               ", paidFee=" + paidFee + ", pendingFee=" + pendingFee + ", paymentDate=" + paymentDate + "]";
    }
}

