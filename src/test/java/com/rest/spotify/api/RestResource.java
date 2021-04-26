package com.rest.spotify.api;

import com.rest.spotify.pojo.PlayList;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.rest.spotify.api.Route.API;
import static com.rest.spotify.api.Route.TOKEN;
import static com.rest.spotify.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {
   // static String access_token ="BQDXLBspsOvtJ9IRhT6UDEpFCLy6hbxbjoe1_5xEQ8ZC6fU8e0qpOEXxRt8QfM7Ea_UPnWjllIF2uui0ah_JJfKL5lbCInohmux7q10KiOlumY3evVUM_LvPCPeHBJFP1oDbIas95_i9JiRGOSPnsk_Z-tzVwReCk6QpLnwtPAw_piyYGj9esnYICUdKJnvJsJonqUzA2PGB0MyLVae1xsXilDmcW49Yxm9s1_EB1Sc3";

    public static Response post(String path, String token , Object requestPayload){
       return  given(getRequestSpec())
                .body ( requestPayload )
                .header ( "Authorization","Bearer "+token )
                .when().post (path)
                .then ().spec ( getResponseSpec () )
                .extract ()
                .response ();
    }

    public static Response postAccount( HashMap<String ,String> renewTokenaValues ){
       return  given(getAccountRequestSpec ())
                .formParams ( renewTokenaValues )
                .log ().all ()
                .when ().post (API + TOKEN)
                .then ().spec ( getResponseSpec())
                .extract ()
                .response ();
    }

    public static Response get(String path, String token){
   return given(getRequestSpec ())
                .header ( "Authorization","Bearer "+ token )
                .when().get(path)
                .then ().spec ( getResponseSpec () )
                .extract ()
                .response ();
    }

    public static Response update ( String path, String token , Object requestPayload){
        return given(getRequestSpec ())
                .body ( requestPayload )
                .header ( "Authorization","Bearer "+ token )
                .when().put(path)
                .then ().spec ( getResponseSpec () )
                .extract ()
                .response ();
    }
}
