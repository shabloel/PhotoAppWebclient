package com.appsdeveloperblog.ws.clients.photoappwebclient.services;


import com.appsdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AlbumService {

    List<AlbumRest> returnAlbums();
}
