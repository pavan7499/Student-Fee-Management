package com.studfees.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.studfees.entity.StudentPayment;
import com.studfees.paymentpdf.PdfGeneratorService;

import com.studfees.service.StudentPaymentService;
 
@RestController


public class StudentPaymentController {

@Autowired
StudentPaymentService service;


	@PostMapping("/payment/add")
	public String addPayment(@RequestBody StudentPayment data)
	{
		
		return service.addPayment(data);
	
	}
	
	@PutMapping("/payment/update/{id}")
	public String updatePayment(@PathVariable Long id,
	                            @RequestParam Double amount) {
	    return service.updatePayment(id, amount);
	}
	@PutMapping("/payment/addPartial/{id}")
	public String addPartialPayment(@PathVariable Long id,
	                                     @RequestParam Double amount) {
	    return service.addPartialPayment(id, amount);
	}

	@GetMapping("/payment/{id}")
	public  List<StudentPayment> getPayment(@PathVariable Long id) {
	    return service.getPaymentsByStudent(id);
	}
	@GetMapping("/payment/all")
	public List<StudentPayment> getAllPayments() {
	    return service.getAllPayments();
	}
	
	@GetMapping("/payment/pending")
	public List<StudentPayment> getPendingPayments() {
	    return service.getPendingPayments();
	}

	
	@Autowired
	PdfGeneratorService pdfService;

	@GetMapping("/payment/pdf/{studentId}")
	public ResponseEntity<byte[]> generatePDF(@PathVariable Long studentId) throws Exception {

	    byte[] pdf = pdfService.generateFeeReceipt(studentId);

	    if (pdf == null) {
	        return ResponseEntity.notFound().build(); 
	    }

	    return ResponseEntity.ok()
	            .header("Content-Disposition", "attachment; filename=fee-receipt.pdf")
	            .body(pdf);
	}




}
