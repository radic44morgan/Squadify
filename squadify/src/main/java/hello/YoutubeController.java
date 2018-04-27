package hello;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemSnippet;
import com.google.api.services.youtube.model.PlaylistSnippet;
import com.google.api.services.youtube.model.PlaylistStatus;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class YoutubeController
{
    private final String KEY = "AIzaSyASJIXLhGsvTON3egUcHlwB4Xg5hJc2qW8";
    private YouTube youtube;
    List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

    
    public String findURL(String query)
    {
        String videoURL = "";
        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(),
                new HttpRequestInitializer()
                {
                    public void initialize(HttpRequest request) throws IOException
                    {
                    }
                }).setApplicationName("video-test").build();
        try
        {
            YouTube.Search.List videoSearch = youtube.search().list("snippet");
            videoSearch.setKey(KEY);
            videoSearch.setQ(query);
            SearchListResponse searchResponse = videoSearch.execute();
            List<SearchResult> searchList = searchResponse.getItems();

            SearchResult result = searchList.iterator().next();
            ResourceId id = result.getId();
            
            videoURL = "youtube.com/watch?v=" + id.getVideoId();

        } catch (IOException ex)
        {
            videoURL = ex.getMessage();
        }
        
        return videoURL;
        
    }
    
    public ArrayList<String> findID(String query)
    {
        String videoID = "";
        String videoTitle = "";
        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(),
                new HttpRequestInitializer()
                {
                    public void initialize(HttpRequest request) throws IOException
                    {
                    }
                }).setApplicationName("video-test").build();
        try
        {
            YouTube.Search.List videoSearch = youtube.search().list("snippet");
            videoSearch.setKey(KEY);
            videoSearch.setQ(query);
            SearchListResponse searchResponse = videoSearch.execute();
            List<SearchResult> searchList = searchResponse.getItems();

            SearchResult result = searchList.iterator().next();
            ResourceId id = result.getId();
            videoID = id.getVideoId();
            videoTitle = result.getSnippet().getTitle();

        } catch (IOException ex)
        {
            videoID = ex.getMessage();
        }
        
        ArrayList<String> results = new ArrayList<String>();
        results.add(videoID);
        results.add(videoTitle);
        return results;
        
    }
    
  
    
    public String createPlaylist(String playlistName)
    {
        String playlistID = "";
        try {
            // Authorize the request.
            Credential credential = Auth.authorize(scopes, "playlistupdates");

            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
                    .setApplicationName("youtube-cmdline-playlistupdates-sample")
                    .build();

            // Create a new, private playlist in the authorized user's channel.
            playlistID = insertPlaylist(playlistName);


        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
        
        return playlistID;
    }
    
    private String insertPlaylist(String playlistName) throws IOException {

        // This code constructs the playlist resource that is being inserted.
        // It defines the playlist's title, description, and privacy status.
        PlaylistSnippet playlistSnippet = new PlaylistSnippet();
        playlistSnippet.setTitle(playlistName + ": " + Calendar.getInstance().getTime());
        playlistSnippet.setDescription("A private playlist created with Squadify");
        PlaylistStatus playlistStatus = new PlaylistStatus();
        playlistStatus.setPrivacyStatus("private");

        Playlist youTubePlaylist = new Playlist();
        youTubePlaylist.setSnippet(playlistSnippet);
        youTubePlaylist.setStatus(playlistStatus);

        // Call the API to insert the new playlist. In the API call, the first
        // argument identifies the resource parts that the API response should
        // contain, and the second argument is the playlist being inserted.
        YouTube.Playlists.Insert playlistInsertCommand =
                youtube.playlists().insert("snippet,status", youTubePlaylist);
        Playlist playlistInserted = playlistInsertCommand.execute();

        // Print data from the API response and return the new playlist's
        // unique playlist ID.
        System.out.println("New Playlist name: " + playlistInserted.getSnippet().getTitle());
        System.out.println(" - Privacy: " + playlistInserted.getStatus().getPrivacyStatus());
        System.out.println(" - Description: " + playlistInserted.getSnippet().getDescription());
        System.out.println(" - Posted: " + playlistInserted.getSnippet().getPublishedAt());
        System.out.println(" - Channel: " + playlistInserted.getSnippet().getChannelId() + "\n");
        return playlistInserted.getId();

    }
    
    public String insertPlaylistItem(String playlistId, String videoId) throws IOException {
        Credential credential = Auth.authorize(scopes, "playlistupdates");

            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                    .setApplicationName("youtube-cmdline-playlistupdates-sample")
                    .build();
        // Define a resourceId that identifies the video being added to the
        // playlist.
        ResourceId resourceId = new ResourceId();
        resourceId.setKind("youtube#video");
        resourceId.setVideoId(videoId);

        // Set fields included in the playlistItem resource's "snippet" part.
        PlaylistItemSnippet playlistItemSnippet = new PlaylistItemSnippet();
        playlistItemSnippet.setPlaylistId(playlistId);
        playlistItemSnippet.setResourceId(resourceId);

        // Create the playlistItem resource and set its snippet to the
        // object created above.
        PlaylistItem playlistItem = new PlaylistItem();
        playlistItem.setSnippet(playlistItemSnippet);

        // Call the API to add the playlist item to the specified playlist.
        // In the API call, the first argument identifies the resource parts
        // that the API response should contain, and the second argument is
        // the playlist item being inserted.
        YouTube.PlaylistItems.Insert playlistItemsInsertCommand =
                youtube.playlistItems().insert("snippet,contentDetails", playlistItem);
        PlaylistItem returnedPlaylistItem = playlistItemsInsertCommand.execute();

        // Print data from the API response and return the new playlist
        // item's unique playlistItem ID.

        System.out.println("New PlaylistItem name: " + returnedPlaylistItem.getSnippet().getTitle());
        System.out.println(" - Video id: " + returnedPlaylistItem.getSnippet().getResourceId().getVideoId());
        System.out.println(" - Posted: " + returnedPlaylistItem.getSnippet().getPublishedAt());
        System.out.println(" - Channel: " + returnedPlaylistItem.getSnippet().getChannelId());
        return returnedPlaylistItem.getId();

    }
}
