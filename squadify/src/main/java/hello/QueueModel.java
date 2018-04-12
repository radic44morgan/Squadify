/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author morganradic
 */
public class QueueModel
{

    private String name;
    private String code;
    private ArrayList<SongModel> songs;
    private ArrayList<UserModel> users;

    public QueueModel(UserModel master, String n)
    {
        users = new ArrayList<UserModel>();
        users.add(master);
        name = n;
        //code = generateCode();
        code = "a";
        songs = new ArrayList<SongModel>();
        SongModel song = new SongModel("Green Eyes","Wavves");
        songs.add(song);
    }
    
    public QueueModel(String n)
    {
        name = n;
        users = new ArrayList<UserModel>();
        //code = generateCode();
        code = "a";
        songs = new ArrayList<SongModel>();
        SongModel song = new SongModel("Green Eyes","Wavves");
        songs.add(song);
    }

    public QueueModel()
    {
        users = new ArrayList<UserModel>();
        //code = generateCode();
        code = "a";
        songs = new ArrayList<SongModel>();
        SongModel song = new SongModel("Green Eyes","Wavves");
        songs.add(song);
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

    public void addUser(UserModel user)
    {
        users.add(user);

    }
    
    public int getUserSize()
    {
        return users.size();
    }
    
    public void addSong(SongModel song)
    {
        songs.add(song);
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
}
