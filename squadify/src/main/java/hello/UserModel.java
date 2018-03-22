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
public class UserModel
{
    private String username;
    private String queuecode;
    
    public UserModel(String u)
    {
        username = u;
    }
    
    public UserModel()
    {
        
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the queueCode
     */
    public String getQueuecode()
    {
        return queuecode;
    }

    /**
     * @param queueCode the queueCode to set
     */
    public void setQueuecode(String queueCode)
    {
        this.queuecode = queueCode;
    }
    
}
