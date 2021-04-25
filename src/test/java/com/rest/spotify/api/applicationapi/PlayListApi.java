package com.rest.spotify.api.applicationapi;

import com.rest.spotify.api.RestResource;
import com.rest.spotify.pojo.PlayList;
import io.restassured.response.Response;

import static com.rest.spotify.api.SpecBuilder.getRequestSpec;
import static com.rest.spotify.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class PlayListApi {
    static String access_token ="BQBDVPXEblLZz5qc2WV7MUH6sGe7DbuBPpZstj_ztoTE0dfd7e6RhD50X9PhRrrNNvTsZJ3ZvdbWEl7q9wctpw3ubCS4xVn9DVsdf83m7i--12w1zoyDDI87TzkchUO_NbxUmUQgTsc3R3sdA6AC56m2Xnlm0L7v6SEyVj8nxI8fF7IXNbrmmtoGV76rjQbLsHnVGiCyiDEHePlLaFWn6PUSVgpQbzRP46wI8W45Cb1r";

    public static Response post(PlayList requestPlaylist){
        return RestResource.post ( "/users/3pjalve5nvp0hwdfxfe6qs1pd/playlists",
                access_token, requestPlaylist );

    }

    public static Response post(PlayList requestPlaylist, String token){
        return RestResource.post ( "/users/3pjalve5nvp0hwdfxfe6qs1pd/playlists",
                token, requestPlaylist );
    }

    public static Response get(String playlistid){
        return RestResource.get ( "/playlists/"+playlistid, access_token );
    }

    public static Response update ( String s , PlayList requestplayList ){
        return RestResource.update ("/playlists/5NZ6Ph2Sm4316HKYFSKwVF", access_token,
                requestplayList);
    }
}
