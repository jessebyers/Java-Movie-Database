
//Class Name: MovieDatabase.java
//Description: This class is used to store Movie objects and also to show movies, create movies and delete movies.

import java.util.*;

public class MovieDatabase

{

    final private int MAX = 4;
    public Movie[] allMovies;
    private int logicalSize = 0;
    private int total;
    private boolean boolDuplicate;

    public MovieDatabase(){
        allMovies = new Movie[MAX];  
        total = 0;

    }

    //the below methods are influenced by the lecture slides. it allows us to create movie objects and set their data by inputting the movies object info one by one.
    //movie information are started with null values which forces the movie to at least have a name.
    public void addData(String name, String director, int fileSize, int duration)
    {
        findDuplicate(name, director, fileSize, duration);
        if (boolDuplicate == false){

            if ( total < allMovies.length )
            {
                allMovies = resize(allMovies, false);
                allMovies[total] = new Movie(); //movie arrays
                setData1(allMovies[total], name,director,fileSize,duration);
                total++; 
                setLogicalSize();
            } else 
            {
                allMovies = resize(allMovies, true);
                allMovies[total] = new Movie(); //movie arrays
                setData1(allMovies[total], name,director,fileSize,duration);
                total++;
                setLogicalSize();
            }
        }
        else {   
            boolDuplicate = false;
        }
    } 

    private void findDuplicate(String name, String director, int fileSize, int duration){
        boolean returnBool; //boolean is used so we can pass the correct booleans value without it changing when we increment

        for (int i = 0; i < allMovies.length; i++){

            if (allMovies[i] != null &&
            allMovies[i].getName().equalsIgnoreCase(name) == true && 
            allMovies[i].getDirector().equalsIgnoreCase(director) == true && 
            fileSize == allMovies[i].getFileSize() && 
            duration == allMovies[i].getDuration())
            {
                returnBool = true;
                boolDuplicate = returnBool;
            } else {
                returnBool = false;

            }
        }
    }

    private void setData1(Movie m, String name, String director, int fileSize, int duration)
    {
        m.setName(name);
        m.setDirector(director);
        m.setFileSize(fileSize);
        m.setDuration(duration);
    }

    public String showMovies(int current) 
    {
        String stringName;
        String stringDirector;
        String stringFileSize;
        String stringDuration;

        stringName = "Movie title: " + allMovies[current].getName() + "\n";
        stringDirector = "Director: " + allMovies[current].getDirector() +"\n";
        stringFileSize = "fileSize: " + allMovies[current].getFileSize() + "\n";
        stringDuration = "duration: " + allMovies[current].getDuration() + "\n";   

        return(stringName + stringDirector + stringFileSize + stringDuration) ;
    }

    private Movie[] resize(Movie[] allMovies, boolean bool)
    {
        if (bool == true){
            Movie[] allMoviesResize = new Movie[allMovies.length+4];     
            for(int i=0; i< allMovies.length; i++)
                allMoviesResize[i] = allMovies[i];
            return(allMoviesResize);
        }
        else { 
            Movie[] allMoviesResize = new Movie[allMovies.length];     
            for(int i=0; i< allMovies.length; i++)
                allMoviesResize[i] = allMovies[i];
            return(allMoviesResize);
        }
    }

    public void deleteMovie(int indexMovie)
    {
        for(int i=indexMovie; i < total - 1; i++){
            allMovies[i] =  allMovies[i+1];
            allMovies[i+1]  = null;
            total--;
            setLogicalSize();
            allMovies[i].decreaseNumberOfMovies();
        }

    }

    public void editMovie(int indexMovieEdit, String name, String director, int fileSize, int duration)
    {          
        allMovies[indexMovieEdit].setName(name);
        allMovies[indexMovieEdit].setDirector(director);
        allMovies[indexMovieEdit].setFileSize(fileSize);
        allMovies[indexMovieEdit].setDuration(duration);

    }

    public int getLogicalSize(){
        return(logicalSize);
    }

    public void setLogicalSize(){
        logicalSize = total;
    }

}

        
         