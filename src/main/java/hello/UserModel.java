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
    private String queueCode;
    
    public UserModel(String u)
    {
        username = u;
    }
    
    public void setQueueCode(String c)
    {
        queueCode = c;
    }
    
    public String getCode()
    {
        return queueCode;
    }
    
}
