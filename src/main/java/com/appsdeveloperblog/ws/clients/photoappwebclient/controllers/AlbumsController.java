package com.appsdeveloperblog.ws.clients.photoappwebclient.controllers;

import com.appsdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

//its a client webapplication therefore no @Restcontroller annotation
@Controller
public class AlbumsController {

    //OAuth2ClientService you get out of the box from spring security
    @Autowired
    OAuth2AuthorizedClientService oauth2ClientService;

    //@AuthenticationPrincipal is used to inject the openid user object
    //OidcUser represents a registered user with openidconnect and who is also a currently authenticated user

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oAuth2AuthorizedClient = oauth2ClientService
                .loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();

        System.out.println("Autherized token value: " + jwtAccessToken);
        System.out.println("principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);



       /* AlbumRest album = AlbumRest.builder()
                .albumId("albumOne")
                .albumTitle("Album one title")
                .albumUrl("http://localhost:8082/albums/1")
                .build();

        AlbumRest album2 = AlbumRest.builder()
                .albumId("albumTwo")
                .albumTitle("Album two title")
                .albumUrl("http://localhost:8082/albums/2")
                .build();*/

        model.addAttribute("albums", Arrays.asList(album, album2));

        return "albums";
    }
}
