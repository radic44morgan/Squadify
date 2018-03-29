/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import java.io.*;
import java.net.*;

/**
 *
 * @author morganradic
 */
public class SpotifyController
{
   
    private final String CLIENT_ID = "f2c88274aae548819b002080cbd7f551";
    private final String CLIENT_SECRET = "71844d6fd73144d3bdb386dadc081468";
    private final URI REDIRECT_ID = SpotifyHttpManager.makeUri("localhost:8080/create");
    private final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUri(REDIRECT_ID)
            .build();
    private final AuthorizationCodeUriRequest authCodeRequest = spotifyApi.authorizationCodeUri()
            .scope("streaming,user-modify-playback-state")
            .show_dialog(true)
            .build();
    
    public SpotifyController()
    {
        
    }
    
    public URI authSync()
    {
        URI uri = authCodeRequest.execute();
        System.out.println(uri.toString());
        return uri;
    }
    
    public String generateAuthView()
    {
        String accessToken = "";
        try
        {
            StringBuilder result = new StringBuilder();
            URL url = new URL(authSync().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
            {
                result.append(line);
            }
            reader.close();
            accessToken = result.toString();
        }
        catch (IOException ex)
        {
            
        }
        return accessToken;
    }
    
    public void setAuth(String authToken)
    {
      //spotifyApi = new SpotifyApi.Builder().setAccessToken(authToken).build();
    }
   
                    
}
