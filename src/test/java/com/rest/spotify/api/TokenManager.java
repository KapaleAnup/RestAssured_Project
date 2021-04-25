package com.rest.spotify.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.rest.spotify.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    public static String renewToken(){

        HashMap<String , String> renewTokenaValues = new HashMap <> (  );
        renewTokenaValues.put ( "client_id", "57e207a234574e37aa7f3473d939278f" );
        renewTokenaValues.put ( "client_secret", "5d0619aac6dc4f9f9aff5734f7d6ebd0" );
        renewTokenaValues.put ( "refresh_token" , "AQBnKV2vIsGTSaeHBMIo_925Z28vkh11NoIw-wgUr7r0X-i3sGiU3B52lc06PiiIwhNARNnWTh9V4LeZKb5dKGV8loWKbmrn0tkr2ZjsRKGaKytXOSwG7UzEjwmIXpUB3mU");
        renewTokenaValues.put ( "grant_type", "refresh_token" );

       Response response =  given()
                .baseUri ( "https://accounts.spotify.com")
                .contentType ( ContentType.URLENC )
                .formParams ( renewTokenaValues )
                .when ().post ("/api/token")
                .then ().spec ( getResponseSpec())
                .extract ()
                .response ();

        if(response.statusCode () !=200){
            throw new RuntimeException ( "ABBORT !!, Renew Token is failed" );
        }

        return response.path ( "access_token" );

    }
}
