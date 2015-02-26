package com.albion.linkshorten.health;

import com.codahale.metrics.health.HealthCheck;

public class LinkHealthCheck extends HealthCheck {

    private final String original;
    private final String shortened;

    public LinkHealthCheck(String original, String shortened) {
        this.original = original;
        this.shortened = shortened;
    }

    @Override
    protected Result check() throws Exception {

        if (original.length() < 1) {
            return Result.unhealthy("Original link is missing.");
        }

        if (shortened.length() < 1) {
            return Result.unhealthy("Shortened link is missing.");
        }

        return Result.healthy();
    }
}