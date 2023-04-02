package dev.converer.cunit.rest;

import lombok.NoArgsConstructor;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@NoArgsConstructor
public class Request {
    private CloseableHttpClient httpClient;

    // API key appended
    @Value("https://data.fixer.io/api/latest?access_key=Z17y06e34qOgOKpN43Af9lkz7JGOpp5u")
    protected String apiHost;

    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(apiHost));
        return restTemplate;
    }
    @ConditionalOnMissingBean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }
}
