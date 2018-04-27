/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author morganradic
 */
public class QueueModel
{

    private String name;
    private String code;
    private String playlistID;
    private String url;
    private ArrayList<SongModel> songs;
    private ArrayList<UserModel> users;

    public QueueModel(UserModel master, String n, String p)
    {
        playlistID = p;
        users = new ArrayList<UserModel>();
        users.add(master);
        name = n;
        code = generateCode();
        songs = new ArrayList<SongModel>();
    }
    
    public QueueModel(String n)
    {
        name = n;
        users = new ArrayList<UserModel>();
        code = generateCode();
        songs = new ArrayList<SongModel>();
    }

    public QueueModel()
    {
        users = new ArrayList<UserModel>();
        code = generateCode();
        songs = new ArrayList<SongModel>();
    }
    
    public String generateCode()
    {
        Random r = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++)
        {
            builder.append((char) (r.nextInt(26) + 'a'));
        }
        return builder.toString();

    }
    
    public String generateURL()
    {
        return "https://www.youtube.com/playlist?list=" + playlistID;
    }

    public void addUser(UserModel user)
    {
        users.add(user);

    }
    
    public int getUserSize()
    {
        return users.size();
    }
    
    public void addSong(YoutubeController yc, SongModel song)
    {
        songs.add(song);
        try
        {
            yc.insertPlaylistItem(playlistID, song.getUrl());
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());        
        }
    }
    
    public void removeSong(SongModel song)
    {
        songs.remove(song);
    }

    public String getCode()
    {
        return code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public ArrayList<SongModel> getSongs()
    {
        return songs;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the playlistID
     */
    public String getPlaylistID()
    {
        return playlistID;
    }

    /**
     * @param playlistID the playlistID to set
     */
    public void setPlaylistID(String playlistID)
    {
        this.playlistID = playlistID;
        url = generateURL();
    }

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url)
    {
        this.url = url;
    }
}
