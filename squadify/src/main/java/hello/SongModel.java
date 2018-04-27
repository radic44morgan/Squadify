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
    private String url;
    
    public SongModel(String n, String u)
    {
        name = n;
        url = u;
    }
    
    public SongModel(String n)
    {
        name = n;
    }
    
    public SongModel()
    {
        
    }
    

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }


    

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
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
