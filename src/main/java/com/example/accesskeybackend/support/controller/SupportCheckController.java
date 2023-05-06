package com.example.accesskeybackend.support.controller;

import com.example.accesskeybackend.support.dto.SupportIp6Dto;
import com.example.accesskeybackend.support.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/checkIpv6Support")
public class SupportCheckController {
    private final SupportService service;

    @Autowired
    public SupportCheckController(SupportService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<SupportIp6Dto> checkSupportOfIpV6(@RequestParam("siteUrl") String siteUrl) {
        return ResponseEntity.ok(service.checkSupportOfIpV6(siteUrl));
    }
}
