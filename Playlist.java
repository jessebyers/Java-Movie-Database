//Name: Mr. Jesse Byers
//Student Number: c3162857
//Date: 29/05/17
//Class Name: Playlist.java
//Description: This class is used for showing, editing and deleting playlists in the program. This class also shows the total duration and size of each playlist object.

import java.util.*;
public class Playlist 
{ 

    public Movie[] movie;
    final private int MAX = 4;
    private int total;
    private int totalTime = 0;
    final private int MAX_TIME = 1000; //mins
    private int totalSize = 0;
    final private int MAX_SIZE = 2000; //mb
    private int logicalSize = 0;

    //Contstructor for the playlist
    public Playlist()
    {
        movie = new Movie[MAX];
        total = 0;        
    }

    public Movie[] resize(Movie[] movie, boolean bool)
    {

        if (bool == true){
            Movie[] movieResize = new Movie[movie.length+4];
            for(int i=0; i< movie.length; i++)
                movieResize[i] = movie[i];
            return(movieResize);
        }
        else { 
            Movie[] movieResize = new Movie[movie.length];
            for(int i=0; i< movie.length; i++)
                movieResize[i] = movie[i];
            return(movieResize);

        }

    }

    public void setTotalTime(int timeAdd)
    {
        totalTime = totalTime + timeAdd;

    }

    public int getTotalTime()
    {  
        return(totalTime);
    } 

    public void setPlaylistNum()
    {
        logicalSize = logicalSize + 1;

    }

    public int getPlaylistNum()
    {  
        return(logicalSize);
    } 

    public void setPlaylistSize(int addSize)
    {
        totalSize = totalSize + addSize;

    }

    public int getPlaylistSize()
    {  
        return(totalSize);
    } 

    public int getMaxSize(){
        return(MAX_SIZE) ;  
    }

    public int getMaxTime(){
        return(MAX_TIME);
    }
}
