package dev.converer.cunit.rest;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FixerGet extends Request {
    // Default currency is the dollar
    public String fetchAllNow() {
        return restTemplate().getForObject(apiHost, String.class);
    }
}
