package com.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  ArrayList<String> arr = new ArrayList<String>();
  int count = 0;
  boolean flag = false;
  int ct = 0;
  //ArrayList<VideoLibrary> playlists = new ArrayList<VideoLibrary>();
  private List<String> playlists = new ArrayList<>();
  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
	  
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
	  
	  for(int i = 0; i < videoLibrary.getVideos().size(); i++)
		  System.out.println(videoLibrary.getVideos().get(i).getTitle() + '[' + videoLibrary.getTags().get(i) + ']');
  }

  public void playVideo(String videoId) {
	  
	  if(arr.size() > 0) {
	      System.out.println("Stopping Video: " + arr.get(0));
	      arr.remove(0);
	      arr.add(videoLibrary.getVideo(videoId).getTitle());
	    } else {
	      arr.add(videoLibrary.getVideo(videoId).getTitle());
	    }
	    
	  System.out.println("Playing video: " + videoLibrary.getVideo(videoId).getTitle());
	  Video video = videoLibrary.getVideo(videoId);
	  
      if (video == null) {
          System.out.println("Cannot play video: Video does not exist");
        }
  }

  public void stopVideo() {
	  
	  String videoId = videoLibrary.getVideos().get(0).getVideoId();
	  Video video = videoLibrary.getVideo(videoId);
	  if (video == null) {
		  System.out.println("Cannot play video: Video does not exist");
	  }
	  else if(arr.size() > 0) {
		  System.out.println("Stopping Video: " + arr.get(0));
		  arr.remove(0);
	  }else {
		  System.out.println("Cannot play video: Video does not exist");

	  }
	  count++;
  }

  public void playRandomVideo() {
	  
	  /*we generate a random number with the rand() method
	   * we use the number in a for loop to select a random title
	   * we use the title as the videoId
	   * we use the same lines as for the playVideo functionw
	   */
	  List<Video> vids = videoLibrary.getVideos();
	  ArrayList<String> ID = new ArrayList<String>();

	  for (Video vid: vids) {
	  ID.add(((Video) vid).getVideoId());
	  }

	  Random rand = new Random();
	  String randomElement = ID.get(rand.nextInt(ID.size()));
	  playVideo(randomElement);
  }

  public void pauseVideo() {
	  
	  if((arr.size()>0)&&(count>0))
	  {
		  System.out.println("Video already paused:"+arr.get(0));
		  return;
	  }
	  if(arr.size()>0)
	  {
		  System.out.println("Pausing video: "+arr.get(0));
		  count++;
		  return;
	  }
  }

  public void continueVideo() {
	  
	  if((count==0)&&(flag==false))
	     {
	       System.out.println("Cannot continue video: Video is not paused");
	     }
	     else if((count>0)&&(flag==false))
	     {
	       System.out.println("Continuing video: "+arr.get(0));
	       flag = true;
	     }
	     else if((count>0)&&(flag==true))
	     {
	       System.out.println("Cannot continue video: Video is not paused");
	     }
	     else
	     {
	       System.out.println("Cannot continue video: No video is currently playing");
	     }
  }
  
  public String getVideoString(Video video)
  {
    String tags=String.join(" ",video.getTags());
    return video.getTitle() + " ("+video.getVideoId() + ") ["+tags+"]";
  }

  public void showPlaying() {

	  if(arr.size()>0)
	  {
		  List<Video>  videos= videoLibrary.getVideos();

		  for(Video video: videos)
		  {
			  if(video.getTitle()==arr.get(0)) {
				  System.out.print("Currently playing: ");

				  System.out.println( getVideoString(video)+"/t");
			  }
		  }
	  }
	  else
	  {
		  System.out.println("No video is currently playing");
	  }
  }

  public void createPlaylist(String playlistName) {

	  if(!playlists.stream().anyMatch(playlistName :: equalsIgnoreCase) && !playlistName.isEmpty()){
	      playlists.add(playlistName);
	      System.out.println("Successfully created new playlist: " + playlistName);
	    } else {
	      System.out.println("Cannot create playlist: A playlist with the same name already exists");
	    }
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
	  if(! playlists.contains(playlistName.toUpperCase()) ){
		  System.out.println("Cannot add video to "+ playlistName+": Playlist does not exist");
	  }
	  else{
		  if(videoLibrary.getVideo(videoId)==null ){
			  System.out.println("Cannot add video to "+playlistName+": Video does not exist");
		  }
		  else if(videoLibrary.getVideo(videoId).isFlagged()){
			  System.out.println("Cannot add video to "+playlistName+": "+showFlaggedReason(videoLibrary.getVideo(videoId)));
		  }
		  else if(playlists.get(playlistName.toUpperCase()).getVideos()!=null && playlists.get(playlistName.toUpperCase()).getVideos().contains(videoLibrary.getVideo(videoId))){
			  System.out.println("Cannot add video to "+playlistName+": Video already added");
		  }
		  else{
			  System.out.println("Added video to "+playlistName+": "+videoLibrary.getVideo(videoId).getTitle());
			  playlists.get(playlistName.toUpperCase()).addVideo((videoLibrary.getVideo(videoId)));
		  }
	  }
  }

  private String showFlaggedReason(Video video) {
	// TODO Auto-generated method stub
	return null;
}

public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
	  //we create an arrayList<ArrayList> in which we store the videoId and a counter;
	  //if the ct is 0 it means it has never been flagged before so we show the error message;
	  //else we do the following
    System.out.println("Successfully flagged video: " + videoLibrary.getVideo(videoId).getTitle() + '('
    		+ "reason: " + reason);
    
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}