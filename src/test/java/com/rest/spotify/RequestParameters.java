package com.rest.spotify;

import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class RequestParameters {


    @Test
    public void singleQueryparameter(){
        given ()
                .baseUri ( "https://postman-echo.com")
                .queryParam ( "foo1", "bar1" )
                .log ().all ().
        when ().
                get ("/get").
        then ().
                log ().all ().
                assertThat ().
                statusCode ( 200);
    }


    @Test
    public void MultipleQueryparameter(){
        HashMap<String, String> queryparmas = new HashMap <> (  );
        queryparmas.put ( "foo1", "bar1" );
        queryparmas.put ( "foo2", "bar2" );
        queryparmas.put ( "foo3", "bar3" );
        given ()
                .baseUri ( "https://postman-echo.com")
             //   .queryParam ( "foo1", "bar1" )
              //  .queryParam ( "foo2","bar2" )
                .queryParams ( queryparmas )
                .log ().all ().
                when ().
                get ("/get").
                then ().
                log ().all ().
                assertThat ().
                statusCode ( 200);
    }

    @Test
    public void MultipleValueparameter ( ) {
        given ( )
                .baseUri ( "https://postman-echo.com" )
                .queryParams ( "foo1" , "bar1, bar2, bar3" )
                .log ( ).all ( ).
                when ( ).
                get ( "/get" ).
                then ( ).
                log ( ).all ( ).
                assertThat ( ).
                statusCode ( 200 );
    }

    @Test
    public void pathParameter ( ) {
        given ( )
                .baseUri ( "https://reqres.in" )
                .pathParam ( "userId" , "2")
                .log ( ).all ( ).
                when ( ).
                get ( "/api/users/{userId}" ).
                then ( ).
                log ( ).all ( ).
                assertThat ( ).
                statusCode ( 200 );
    }

    @Test
    public void MultipartFormData ( ) {
        given ( )
                .baseUri ( "https://postman-echo.com" )
                .multiPart ( "foo1", "bar1" )
                .log ( ).all ( ).
                when ( ).
                post ( "/post" ).
                then ( ).
                log ( ).all ( ).
                assertThat ( ).
                statusCode ( 200 );
    }


    @Test
    public void uploadFile ( ) {
        String attributes = "{\"name\":\"temp.txt\",\"parent\":{\"id\":\'123456\"}}" ;

        given ( )
                .baseUri ( "https://postman-echo.com" )
                .multiPart ( "file", new File ( "temp.txt" ) )
                .multiPart ( "attributes", attributes,"application/json" )
                .log ( ).all ( ).
                when ( ).
                post ( "/post" ).
                then ( ).
                log ( ).all ( ).
                assertThat ( ).
                statusCode ( 200 );
    }

    //

    @Test
    public void downloadFile ( ) throws IOException {
       byte[] bytes =  given ( )
                .baseUri ( "https://raw.githubusercontent.com" )

                .log ( ).all ( ).
                when ( ).
                get ( "/appium/appium/master/sample-code/apps/ApiDemos-debug.apk" ).
                then ( ).
                log ( ).all ( ).
                extract ().response ().asByteArray ();

        OutputStream outputStream = new FileOutputStream ( new File ( "ApiDemos-debug.apk" ) );
        outputStream.write ( bytes );
        outputStream.close ();
    }

}
