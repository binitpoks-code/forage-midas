package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IncentiveClient {
    private final RestTemplate restTemplate;
    private final String incentiveApiUrl;

    public IncentiveClient(RestTemplateBuilder builder,
                           @Value("${general.incentive-api-url}") String incentiveApiUrl) {
        this.restTemplate = builder.build();
        this.incentiveApiUrl = incentiveApiUrl;
    }

    public float getIncentive(Transaction transaction) {
        Incentive incentive = restTemplate.postForObject(
                incentiveApiUrl + "/incentive", transaction, Incentive.class);
        return incentive != null ? incentive.getAmount() : 0f;
    }
}
