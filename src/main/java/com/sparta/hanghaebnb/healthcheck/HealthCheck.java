package com.sparta.hanghaebnb.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        if (isHealthy()) {
            return Health.up().build();
        }
        return Health.down().build();
    }
    private boolean isHealthy() {
        // TODO: Check if the service is healthy.
        return true;
    }
}
