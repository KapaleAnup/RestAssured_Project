package com.rest.spotify;

 import java.util.Arrays;

 import static io.restassured.RestAssured.*;
 import static io.restassured.matcher.RestAssuredMatchers.*;
 import static org.hamcrest.Matchers.*;
public class Test {

    @org.testng.annotations.Test
    public void test(){
/*        given ()
                .when ()
                        .then ();*/

        String desc = "How did Ajay became Litle singam";
        String[] desctiption = desc.split ( " " );
        System.out.println ( Arrays.toString ( desctiption) );


    }
}
