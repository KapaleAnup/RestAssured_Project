package com.rest.spotify.testcases;

import com.rest.spotify.api.applicationapi.PlayListApi;
import com.rest.spotify.pojo.ErrorRoot;
import com.rest.spotify.pojo.PlayList;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class PlaylistTests {


    @Test
    public void shouldbeAbleToCreatePlaylist(){
        PlayList requestplayList = playListBuilder ("New Playlist" , "New playlist description" , false );
      Response response = PlayListApi.post ( requestplayList );
      assertStatusCode ( response.statusCode (), 201 );
      assertPlaylistEqual (response.as ( PlayList.class ),requestplayList );

    }


    @Test
    public void shouldbeAbleToGetAPlaylist(){
        PlayList requestplayList = playListBuilder ( "Updated Playlist Name" , "Updated playlist description" ,false );

       Response response = PlayListApi.get ( "6PcuTENfipYKkTwxSwOIL7");
       assertStatusCode ( response.statusCode (), 200 );
       assertPlaylistEqual (response.as ( PlayList.class ),requestplayList );

    }

    @Test()
    public void shouldbeAbleToUpdateAPlaylist(){

        PlayList requestplayList = playListBuilder ( "New Playlist" , "New playlist description" , false);
        Response response = PlayListApi.update ("5NZ6Ph2Sm4316HKYFSKwVF",requestplayList );
        assertStatusCode ( response.statusCode (),200);

    }

    @Test
    public void shouldbeAbleToCreatePlaylistWithoutName(){

        PlayList requestplayList = playListBuilder ( "" , "New playlist description" , false);

        Response response = PlayListApi.post ( requestplayList );
        assertThat ( response.statusCode (),equalTo ( 400 ) );
        asserError ( response.as ( ErrorRoot.class ), 400, "Missing required field: name" );
    }

    @Test
    public void shouldbeAbleToCreatePlaylistWithoutToken(){

        String invalid_token = "12345";
        PlayList requestplayList = playListBuilder ( "New Playlist" , "New playlist description" , false);

        Response response = PlayListApi.post ( requestplayList, invalid_token );
        assertThat ( response.statusCode (),equalTo ( 401 ) );
        asserError ( response.as ( ErrorRoot.class ), 401, "Invalid access token" );

    }

    public PlayList playListBuilder(String name, String description, boolean _public){
        return  new PlayList ()
                .setName ( name )
                .setDescription ( description )
                .setPublic ( _public );
    }

    public void assertPlaylistEqual(PlayList responsePlayllist, PlayList requestPlaylist){
        assertThat(responsePlayllist.getName (), equalTo ( requestPlaylist.getName () ));
        assertThat(responsePlayllist.getDescription (), equalTo ( requestPlaylist.getDescription ()));
        assertThat(responsePlayllist.getPublic (), equalTo ( requestPlaylist.getPublic () ));
    }

    public void assertStatusCode(int actualStatusCode, int expectedStatusCode){
        assertThat ( actualStatusCode, equalTo ( expectedStatusCode ) );
    }

    public void asserError(ErrorRoot errorRoot, int expectedStatusCode, String expected_msg){
        assertThat(errorRoot.getError ().getStatus (), equalTo ( expectedStatusCode ));
        assertThat(errorRoot.getError ().getMessage (), equalTo ( expected_msg ));

    }



}
