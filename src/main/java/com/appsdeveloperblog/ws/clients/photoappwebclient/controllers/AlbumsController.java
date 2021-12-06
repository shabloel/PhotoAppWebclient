package com.appsdeveloperblog.ws.clients.photoappwebclient.controllers;

import com.appsdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

//its a client webapplication therefore no @Restcontroller annotation
@Controller
public class AlbumsController {

    @GetMapping("/albums")
    public String getAlbums(Model model){

        AlbumRest album = AlbumRest.builder()
                .albumId("albumOne")
                .albumTitle("Album one title")
                .albumUrl("http://localhost:8082/albums/1")
                .build();

        AlbumRest album2 = AlbumRest.builder()
                .albumId("albumTwo")
                .albumTitle("Album two title")
                .albumUrl("http://localhost:8082/albums/2")
                .build();

        model.addAttribute("albums", Arrays.asList(album, album2));

        return "albums";
    }
}
