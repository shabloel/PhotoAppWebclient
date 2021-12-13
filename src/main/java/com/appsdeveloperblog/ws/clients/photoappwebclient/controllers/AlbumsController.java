package com.appsdeveloperblog.ws.clients.photoappwebclient.controllers;

import com.appsdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;
import com.appsdeveloperblog.ws.clients.photoappwebclient.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

//its a client webapplication therefore no @Restcontroller annotation
@Controller
public class AlbumsController {

    private final AlbumService albumService;

    public AlbumsController(AlbumService albumService) {
        this.albumService = albumService;
    }

    //@AuthenticationPrincipal is used to inject the openid user object
    //OidcUser represents a registered user with openidconnect and who is also a currently authenticated user
    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal){

        List<AlbumRest> albums = albumService.returnAlbums();

        model.addAttribute("albums", albums);

        return "albums";
    }
}
