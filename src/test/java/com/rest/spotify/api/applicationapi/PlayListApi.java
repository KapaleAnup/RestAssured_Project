package com.rest.spotify.api.applicationapi;

import com.rest.spotify.pojo.PlayList;
import io.restassured.response.Response;

import static com.rest.spotify.api.SpecBuilder.getRequestSpec;
import static com.rest.spotify.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class PlayListApi {
    static String access_token ="BQDXLBspsOvtJ9IRhT6UDEpFCLy6hbxbjoe1_5xEQ8ZC6fU8e0qpOEXxRt8QfM7Ea_UPnWjllIF2uui0ah_JJfKL5lbCInohmux7q10KiOlumY3evVUM_LvPCPeHBJFP1oDbIas95_i9JiRGOSPnsk_Z-tzVwReCk6QpLnwtPAw_piyYGj9esnYICUdKJnvJsJonqUzA2PGB0MyLVae1xsXilDmcW49Yxm9s1_EB1Sc3";

    public static Response post(PlayList requestPlaylist){
       return  given(getRequestSpec())
                .body ( requestPlaylist )
                .header ( "Authorization","Bearer "+access_token )
                .when().post ("/users/3pjalve5nvp0hwdfxfe6qs1pd/playlists")
                .then ().spec ( getResponseSpec () )
                .extract ()
                .response ();
    }

    public static Response post(PlayList requestPlaylist, String token){
        return  given(getRequestSpec())
                .body ( requestPlaylist )
                .header ( "Authorization", "Bearer "+token )
                .when().post ("/users/3pjalve5nvp0hwdfxfe6qs1pd/playlists")
                .then ().spec ( getResponseSpec () )
                .extract ()
                .response ();
    }

    public static Response get(String playlistid){
   return given(getRequestSpec ())
                .header ( "Authorization","Bearer "+access_token )
                .when().get("/playlists/"+playlistid)
                .then ().spec ( getResponseSpec () )
                .extract ()
                .response ();
    }

    public static Response update ( String s , PlayList requestplayList ){
        return given(getRequestSpec ())
                .body ( requestplayList )
                .header ( "Authorization","Bearer "+access_token )
                .when().put("/playlists/5NZ6Ph2Sm4316HKYFSKwVF")
                .then ().spec ( getResponseSpec () )
                .extract ()
                .response ();
    }
}
