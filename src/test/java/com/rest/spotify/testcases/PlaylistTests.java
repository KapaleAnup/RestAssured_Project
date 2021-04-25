package com.rest.spotify.testcases;

import com.rest.spotify.api.applicationapi.PlayListApi;
import com.rest.spotify.pojo.ErrorRoot;
import com.rest.spotify.pojo.PlayList;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class PlaylistTests {

    @Test
    public void shouldbeAbleToCreatePlaylist(){

        PlayList requestplayList = new PlayList ();
        requestplayList.setName ("New Playlist" );
        requestplayList.setDescription ( "New playlist description" );
        requestplayList.setPublic ( false );

      Response response = PlayListApi.post ( requestplayList );
      assertThat ( response.statusCode (), equalTo ( 201 ) );

      PlayList responsePlayList = response.as ( PlayList.class );

        assertThat(responsePlayList.getName (), equalTo ( requestplayList.getName () ));
        assertThat(responsePlayList.getDescription (), equalTo ( requestplayList.getDescription ()));
        assertThat(responsePlayList.getPublic (), equalTo ( requestplayList.getPublic () ));


    }


    @Test
    public void shouldbeAbleToGetAPlaylist(){
        PlayList requestplayList = new PlayList ();
        requestplayList.setName ("Updated Playlist Name" );
        requestplayList.setDescription ( "Updated playlist description" );
        requestplayList.setPublic ( false );

       Response response = PlayListApi.get ( "6PcuTENfipYKkTwxSwOIL7");
       assertThat ( response.statusCode (),equalTo ( 200 ) );

       PlayList responsePlaylist = response.as ( PlayList.class );

        assertThat(responsePlaylist.getName (), equalTo ( requestplayList.getName () ));
        assertThat(responsePlaylist.getDescription (), equalTo ( requestplayList.getDescription ()));
        assertThat(responsePlaylist.getPublic (), equalTo ( requestplayList.getPublic () ));

    }

    @Test()
    public void shouldbeAbleToUpdateAPlaylist(){

        PlayList requestplayList = new PlayList ();
        requestplayList.setName ("New Playlist" );
        requestplayList.setDescription ( "New playlist description" );
        requestplayList.setPublic ( false );

        Response response = PlayListApi.update ("5NZ6Ph2Sm4316HKYFSKwVF",requestplayList );
        assertThat ( response.statusCode (),equalTo ( 200 ) );

    }

    @Test
    public void shouldbeAbleToCreatePlaylistWithoutName(){

        PlayList requestPlaylist = new PlayList ();
        requestPlaylist.setName ( "" );
        requestPlaylist.setDescription ( "New playlist description" );
        requestPlaylist.setPublic ( false );

        Response response = PlayListApi.post ( requestPlaylist );
        assertThat ( response.statusCode (),equalTo ( 400 ) );

        ErrorRoot errorRoot = response.as ( ErrorRoot.class );


       assertThat(errorRoot.getError ().getStatus (), equalTo ( 400 ));
        assertThat(errorRoot.getError ().getMessage (), equalTo ( "Missing required field: name" ));

    }

    @Test
    public void shouldbeAbleToCreatePlaylistWithoutToken(){

        String invalid_token = "12345";
        PlayList requestPlaylist = new PlayList ();
        requestPlaylist.setName ( "New Playlist"  );
        requestPlaylist.setDescription ( "New playlist description" );
        requestPlaylist.setPublic ( false );

        Response response = PlayListApi.post ( requestPlaylist, invalid_token );
        assertThat ( response.statusCode (),equalTo ( 401 ) );

        ErrorRoot errorRoot = response.as ( ErrorRoot.class );

        assertThat ( errorRoot.getError ().getStatus (), equalTo ( 401 ) );
        assertThat ( errorRoot.getError ().getMessage (), equalTo ( "Invalid access token" ) );
    }


}
