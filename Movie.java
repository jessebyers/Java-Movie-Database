//Name: Mr. Jesse Byers
//Student Number: c3162857
//Date: 29/05/17
//Class Name: Movie.java
//Description: This class is used for creating movie objects in the database.
import java.util.*;

public class Movie 
{

    private String name;
    private String director;
    private int fileSize;
    private int duration;
    private static int numberOfMovies;

    /*CONTSTRUCTOR*/
    public Movie()
    {
        name = null;
        director=null;
        fileSize=-1; //in MB
        duration=-1; //in mins

    }

    /*Set and Get for Name*/
    public void setName (String newName)
    {
        name = newName;
        numberOfMovies++;

    }

    public String getName ()
    {
        return name;

    } 

    /*Set and Get for Director*/
    public void setDirector (String newDirector)
    {
        director = newDirector;

    }

    public String getDirector ()
    {
        return director;

    } 

    /*Set and Get for fileSize*/
    public void setFileSize (int newFileSize)
    {
        fileSize = newFileSize;

    }

    public int getFileSize ()
    {
        return (fileSize);

    } 

    /*Set and Get for duration*/
    public void setDuration (int newDuration)
    {
        duration = newDuration;

    }

    public int getDuration ()
    {
        return (duration);

    } 
    //get for number of movies
    public int getNumberOfMovies ()
    {       
        return (numberOfMovies);

    } 
    //used to decrease the number of movies when a movie is deleted
    public void decreaseNumberOfMovies()
    {
        numberOfMovies--;

    }

}