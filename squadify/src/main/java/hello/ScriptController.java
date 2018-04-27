/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 *
 * @author morganradic
 */
public class ScriptController
{

    private final String script = "ytsearch.py";

    public String runScript(String searchTerm)
    {
        String videoURL;
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(/*Paths.get(script).toAbsolutePath().toString()*/script, searchTerm);
        try (Scanner scan = new Scanner(pb.start().getInputStream()))
        {
            String line = scan.nextLine().substring(3);
            String[] a = line.split("', u'");
            videoURL = a[1];
        } catch (Exception ex)
        {
            videoURL = ex.getMessage();
        }
        return videoURL;
    }

    public String urlQuery(String query)
    {
        String videoURL = "";
        try
        {
            String u = "https://www.youtube.com/results?search_query=" + URLEncoder.encode(query, "UTF-8");
            System.out.println("Input: " + u);
            URL url = new URL(u);
            URLConnection connection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            videoURL = br.readLine();
        } catch (Exception ex)
        {
            videoURL = ex.getMessage();
        }

        return videoURL;
    }
}
