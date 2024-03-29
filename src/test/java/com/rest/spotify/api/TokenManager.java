package com.rest.spotify.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.rest.spotify.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){
        try {
            if(access_token == null || Instant.now ().isAfter ( expiry_time )){
                System.out.println ("Renewing the token" );
                Response response = renewToken ();
                access_token = response.path ( "access_token" );
                int expirydurationInSecodns = response.path ( "expires_in" );
                expiry_time = Instant.now ().plusSeconds ( expirydurationInSecodns - 300 );
            }else {
                System.out.println ("Token is good to use." );
            }

        }catch (Exception e){
            throw new RuntimeException ( "ABBORT !!, Failed to get Token");
        }
        return access_token;
    }

    private static Response renewToken(){

        HashMap<String , String> renewTokenaValues = new HashMap <> (  );
        renewTokenaValues.put ( "client_id", "57e207a234574e37aa7f3473d939278f" );
        renewTokenaValues.put ( "client_secret", "5d0619aac6dc4f9f9aff5734f7d6ebd0" );
        renewTokenaValues.put ( "refresh_token" , "AQBnKV2vIsGTSaeHBMIo_925Z28vkh11NoIw-wgUr7r0X-i3sGiU3B52lc06PiiIwhNARNnWTh9V4LeZKb5dKGV8loWKbmrn0tkr2ZjsRKGaKytXOSwG7UzEjwmIXpUB3mU");
        renewTokenaValues.put ( "grant_type", "refresh_token" );

        Response response =   RestResource.postAccount ( renewTokenaValues );

        if(response.statusCode () !=200){
            throw new RuntimeException ( "ABBORT !!, Renew Token is failed" );
        }

        return response;

    }
}
