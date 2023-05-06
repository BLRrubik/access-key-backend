package com.example.accesskeybackend.support.service;

import com.example.accesskeybackend.exception.IllegalArgumentException;
import com.example.accesskeybackend.support.dto.SupportIp6Dto;
import org.springframework.stereotype.Service;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SupportService {
    public SupportIp6Dto checkSupportOfIpV6(String siteUrl) {
        List<InetAddress> addresses;

        try {
             addresses = Arrays.stream(InetAddress.getAllByName(getDomainFromUrl(siteUrl))).toList();
        } catch (UnknownHostException | NullPointerException e) {
            throw new IllegalArgumentException("Unknown host");
        }

        for (var address: addresses) {
            if (address instanceof Inet6Address) {
                return new SupportIp6Dto(true);
            }
        }

        return new SupportIp6Dto(false);
    }

    private String getDomainFromUrl(String siteUrl) {
        String domain = null;

        Pattern pattern = Pattern.compile("^(?:https?:\\/\\/)?(?:[^@\\/\\n]+@)?(?:www\\.)?([^:\\/\\n]+)");
        Matcher matcher = pattern.matcher(siteUrl);
        while (matcher.find()) {
            domain = matcher.group(1);
        }

        return domain;
    }
}
