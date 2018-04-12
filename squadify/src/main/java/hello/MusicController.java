/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author morganradic
 * class for GreetingController to work with to play music
 * modular so that other APIs can be used with it
 */
public class MusicController
{
    private QueueModel queue;
    
    private MusicController(QueueModel q)
    {
        queue = q;
    }
    
    private void addSong(SongModel song)
    {
        queue.addSong(song);
    }
    
    private void play()
    {
        
    }
    
    private void pause()
    {
        
    }
    
    private void removeSong(SongModel song)
    {
        queue.removeSong(song);
    }
}
