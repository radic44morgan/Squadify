/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.IOException;

/**
 *
 * @author morganradic
 */
public class TestPackage
{

    public static void main(String[] args) throws IOException
    {
        YoutubeController controller = new YoutubeController();
        System.out.println("Testing: searching for ID for 'The Last Text Message'");
        String output = controller.findID("The Last Text Message").get(0);
        System.out.println("Output: " + output);
        System.out.println("Testing: Playlist creation: ");
        String id = controller.createPlaylist("Test Playlist");
        System.out.println("Playlist created. Adding song.");
        controller.insertPlaylistItem(id, output);
    }

}
