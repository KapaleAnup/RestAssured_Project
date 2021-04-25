package com.rest.spotify.api.applicationapi;

import com.rest.spotify.api.RestResource;
import com.rest.spotify.pojo.PlayList;
import io.restassured.response.Response;

import static com.rest.spotify.api.TokenManager.getToken;


public class PlayListApi {

    public static Response post(PlayList requestPlaylist){
        return RestResource.post ( "/users/3pjalve5nvp0hwdfxfe6qs1pd/playlists",
                getToken (), requestPlaylist );
    }

    public static Response post(PlayList requestPlaylist, String token){
        return RestResource.post ( "/users/3pjalve5nvp0hwdfxfe6qs1pd/playlists",
                token, requestPlaylist );
    }

    public static Response get(String playlistid){
        return RestResource.get ( "/playlists/"+playlistid, getToken () );
    }

    public static Response update ( String s , PlayList requestplayList ){
        return RestResource.update ("/playlists/5NZ6Ph2Sm4316HKYFSKwVF", getToken (),
                requestplayList);
    }
}
