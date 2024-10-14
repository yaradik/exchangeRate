package com.example.exchangeRate.controllers;

import com.example.exchangeRate.entities.Limit;
import com.example.exchangeRate.services.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/limits")
@RequiredArgsConstructor
public class LimitController
{
    private final LimitService limitService;

    @PostMapping
    public ResponseEntity<Limit> createLimit(@RequestBody Limit limit) {
        return ResponseEntity.ok(limitService.saveNewLimit(limit));
    }

    @GetMapping
    public ResponseEntity<List<Limit>> getAllLimits() {
        return ResponseEntity.ok(limitService.getAllLimits());
    }
}
