package com.example.applicationtelecom.controller;

import com.example.applicationtelecom.entity.Bill;
import com.example.applicationtelecom.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        return billService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billService.save(bill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill billDetails) {
        return billService.findById(id)
                .map(bill -> {
                    bill.setBillNumber(billDetails.getBillNumber());
                    bill.setAmount(billDetails.getAmount());
                    bill.setIssueDate(billDetails.getIssueDate());
                    bill.setDueDate(billDetails.getDueDate());
                    bill.setStatus(billDetails.getStatus());
                    bill.setMeter(billDetails.getMeter());
                    return ResponseEntity.ok(billService.save(bill));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        if (billService.findById(id).isPresent()) {
            billService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
