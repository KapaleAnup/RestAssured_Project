package com.rest.spotify.api.applicationapi;

import com.rest.spotify.api.RestResource;
import com.rest.spotify.pojo.PlayList;
import io.restassured.response.Response;

import static com.rest.spotify.api.Route.PLAYLISTS;
import static com.rest.spotify.api.Route.USERS;
import static com.rest.spotify.api.TokenManager.getToken;


public class PlayListApi {

    public static Response post(PlayList requestPlaylist){
        return RestResource.post ( USERS +"/3pjalve5nvp0hwdfxfe6qs1pd"+ PLAYLISTS,
                getToken (), requestPlaylist );
    }

    public static Response post(PlayList requestPlaylist, String token){
        return RestResource.post ( USERS +"/3pjalve5nvp0hwdfxfe6qs1pd" + PLAYLISTS,
                token, requestPlaylist );
    }

    public static Response get(String playlistid){
        return RestResource.get ( PLAYLISTS +"/" +playlistid, getToken () );
    }

    public static Response update ( String playlistid , PlayList requestplayList ){
        return RestResource.update (PLAYLISTS +"/" + playlistid, getToken (),
                requestplayList);
    }
}
