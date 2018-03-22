/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author morganradic
 */
public class QueueController
{
    private Map<String, QueueModel> queues;
    
    public QueueController()
    {
        queues = new HashMap<String, QueueModel>();
    }
    
    public String createQueue(UserModel master)
    {
        QueueModel holder = new QueueModel(master, "Test Queue");
        queues.put(holder.getCode(), holder);
        return holder.getCode();
    }
    
    public boolean addUserToQueue(UserModel user, String code)
    {
        if (queues.containsKey(code))
            queues.get(code).addUser(user);
        else
            return false;
        return true;
    }
    
    public void addSong(SongModel song, String code)
    {
        queues.get(code).addSong(song);
    }
    
    public Map<String, QueueModel> getQueues()
    {
        return queues;
    }
}
