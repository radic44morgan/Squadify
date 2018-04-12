/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author morganradic
 */
public class SongModel
{
    private String name;
    private String artist;
    private String filename;
    private UserModel userAdded;
    
    public SongModel(String n, String a)
    {
        name = n;
        artist = a;
    }
    
    public SongModel(String n)
    {
        name = n;
    }
    
    public SongModel()
    {
        
    }
    
    
    //playback method

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the artist
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * @return the filename
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * @return the userAdded
     */
    public UserModel getUserAdded()
    {
        return userAdded;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist)
    {
        this.artist = artist;
    }
}
