package com.appsdeveloperblog.ws.clients.photoappwebclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    //ClientRegistrationRepository is a repository of the registered Oauth clients
    //OAuth2AuthorizedClientRepository stores information about the clients that have been authorized

    //this configuration will make an access token to be included into every single http request,
    //that is sent using this webclient object.
    @Bean
    public WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
                               OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository){
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository,
                        oAuth2AuthorizedClientRepository);

        //this makes it to automatically detect the client config it needs to use,
        // based on the current authentication object
        oauth2.setDefaultOAuth2AuthorizedClient(true);
        return WebClient.builder().
                apply(oauth2.oauth2Configuration()).build();
    }
}
