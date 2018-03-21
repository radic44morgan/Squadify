/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.wrapper.spotify.SpotifyApi;
import java.io.*;
import java.net.*;

/**
 *
 * @author morganradic
 */
public class SpotifyController
{
    private SpotifyApi spotifyapi;
    private final String CLIENT_ID = "f2c88274aae548819b002080cbd7f551";
    private final String CLIENT_SECRET = "71844d6fd73144d3bdb386dadc081468";
    private final String REDIRECT_ID = "https://radic44morgan.github.io/callback/";
    
    public SpotifyController()
    {
       spotifyapi = new SpotifyApi.Builder().setAccessToken(retrieveAccessToken()).build();
    }
    
    public String retrieveAccessToken()
    {
        String accessToken = "";
        String urlbuild = "https://accounts.spotify.com/authorize/?client_id="+CLIENT_ID+"&response_type=code&redirect_uri="+REDIRECT_ID;
        try
        {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlbuild);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line);
                result.append(line);
            }
            reader.close();
            accessToken = reader.toString();
        }
        catch (IOException ex)
        {
            
        }
        
        return accessToken;
    }
}
