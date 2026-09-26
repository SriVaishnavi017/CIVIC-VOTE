package com.civicvote.voting.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "RESULT-SERVICE")
public interface ResultServiceClient {

    @PostMapping("/api/results/refresh")
    void refreshResult(@RequestParam("electionId") Long electionId,
                       @RequestHeader("X-Service-Key") String serviceKey);
}
