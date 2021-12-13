package com.appsdeveloperblog.ws.clients.photoappwebclient.services;

import com.appsdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


public class AlbumServiceImpl implements AlbumService {

    private static final String URL = "http://localhost:8082/albums";

    //OAuth2ClientService you get out of the box from spring security
    private final OAuth2AuthorizedClientService oauth2ClientService;

    private final WebClient client;

    public AlbumServiceImpl(OAuth2AuthorizedClientService oauth2ClientService, WebClient client) {
        this.oauth2ClientService = oauth2ClientService;
        this.client = client;
    }

    @Override
    public List<AlbumRest> returnAlbums() {

        /*      Only necessary if you you restTemplate, jwt token is retrieven in config.class
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oAuth2AuthorizedClient = oauth2ClientService
                .loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();

        System.out.println("Autherized token value: " + jwtAccessToken);
        System.out.println("principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);*/

        List<AlbumRest> albums = client.get()
                .uri(URL)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>() {
                })
                .block();
        return albums;
    }
}
