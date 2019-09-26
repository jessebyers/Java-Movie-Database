
//Class Name: Interface.java
//Description: This class is used as the main interface for the movie database. Other classes needed for this to work are MovieDatabase.java, Movie.Java and Playlist.java.
//This program allows users to input and store movie information to a database and allow them to create playlists and search directors.
//Note: below you will see alot of user inputs that have a -1 in the code. This is used so the user doesnt have to select 0 for movies indexes etc. The input will start at 1.

import java.util.*;
import java.io.*;

public class Interface {

    final private int MAX = 2;
    public Playlist[] playlists;
    private int playlistsTotal = 0;
    int logicalSize = 0;

    private void run() {

        Scanner console = new Scanner(System.in);
        MovieDatabase database = new MovieDatabase();

        playlists = new Playlist[MAX]; 

        int useroption;
        int position;
        int indexMovie;
        int indexMovieEdit;
        String name;
        String director;
        int fileSize;
        int duration;
        //Below is the main menu for the program where the user will input a number and the program will take them to the required function. 9 is exit.
        do {
            //Resizes playlist so more can be added
            if (playlistsTotal >= playlists.length){
                playlists = resize(playlists, 5);
            }

            //below is the main menu for the program
            System.out.println("#############################################################################################################################################################");        
            System.out.println("Total Movies Stored: " + database.getLogicalSize()+ "            " + "Total Playlists Stored: " + getLogicalSize()); //shows sizes of the databases
            System.out.println("Import Movies (0), Enter New Movies(1), Show All Movies(2), Delete Movie(3), Edit Movies(4), Create Playlist(5), Show All Playlists(6), Delete Playlists(7)");
            System.out.println("Search Movie Database (8), Export Database (9), Exit (10): ");  
            useroption = console.nextInt();

            switch(useroption)
            {
                case 0: System.out.println("IMPORT MOVIE"); 
                importMovies(database);  //This imports all movies from a text file, as long as its in the correct format
                break;

                case 1: System.out.println(""); //Enters movies into the database,
                System.out.println("ENTER MOVIES");
                System.out.println("");
                EnterMovies(database);
                System.out.println("------------------------------------------");
                break; 

                case 2: System.out.println(""); //Shows all the movies currently stored
                System.out.println("SHOW ALL MOVIES");
                System.out.println("");
                showAllMovies(database);
                System.out.println("------------------------------------------");
                break; 

                case 3: System.out.println(""); //This allows a user to delete a movie from the database and also decrease the size of the array
                System.out.println("DELETE MOVIES");
                showAllMovies(database);
                System.out.println("Please enter what index you want deleted");
                indexMovie = console.nextInt();
                indexMovie = indexMovie - 1;
                database.deleteMovie(indexMovie);
                System.out.println("------------------------------------------");
                break; 

                case 4: System.out.println(""); //this allows the user to edit specific movies information
                System.out.println("EDIT MOVIES");
                System.out.println("");
                showAllMovies(database);
                System.out.println("");
                editMovie(database);
                System.out.println("------------------------------------------");
                break;

                case 5: System.out.println("Create Playlist" + (playlistsTotal + 1)); //this allows a user to create a playlist
                System.out.println("");
                createPlaylist(playlists, database);

                System.out.println("------------------------------------------");
                break;

                case 6: System.out.println("SHOW ALL PLAYLISTS"); //shows a user all the playlists they have created and gives them the option to view what movies are in them
                showPlaylists(playlists);    
                System.out.println("------------------------------------------");
                break;

                case 7:System.out.println("DELETE PLAYLISTS"); //deletes a playlist
                deletePlaylists(playlists);
                playlistsTotal--;
                System.out.println("------------------------------------------");
                break;

                case 8: System.out.println("SEARCH DATABASE"); //this searches the database for all movies that are under a certain duration
                System.out.println("");
                System.out.println("Press 1 to search database by duration");
                System.out.println("Press 2 to search database by movie director");	
                int searchopt = console.nextInt();

                if (searchopt == 1){
                    searchMovieDuration(database);
                }
                else if (searchopt ==2){
                    searchMovieDirector(database);
                }
                else {
                    System.out.println("Not an option");

                }
                System.out.println("------------------------------------------");
                break;

                case 9:System.out.println("EXPORT DATABASE"); //saves the database back into the textFile
                exportMovies(database);
                System.out.println("------------------------------------------");
                break;

                case 10:break;
                default: System.out.println("Please input one of the options above and press enter");
            }
        }
        while(useroption!=10);
    }

    public void exportMovies(MovieDatabase database)   //influenced by lecture code samples
    {
        Scanner console = new Scanner(System.in);
        String fileName = "movieDatabase.txt";
        int mcount;
        mcount = 0;
        PrintWriter outputStream = null;

        try
        {
            outputStream = new PrintWriter (fileName);
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("Error opening the file " + fileName);
            System.exit (0);
        }

        for (int i = 0; i < database.allMovies.length; i++)
        {
            if (database.allMovies[i] != null) // && database.allMovies[i] != database.allMovies[i+1] )
            {
                outputStream.println(database.showMovies(i));

                mcount++;
            }
        }

        outputStream.close ();
        System.out.println ("Export Successful");
    }

    //below is the import movies method. This reads a formatted text file and inputs the movie information into the database.
    public  void importMovies(MovieDatabase database)   //influenced by lecture code samples
    {

        String movieName ="";
        String movieDirector="";
        int movieFileSize=0;
        int movieDuration=0;

        System.out.print ("Enter file name: ");    //must include extension
        Scanner keyboard = new Scanner (System.in);
        String fileName = keyboard.next ();        
        Scanner inputStream = null;

        try
        {
            inputStream = new Scanner (new File (fileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("Error opening the file " + fileName );
            System.exit (0); //exits system if the movie file isnt found
        }

        int count = 0;
        while (inputStream.hasNextLine ())
        {   

            String lineInput = inputStream.nextLine(); 
            //below is how the text file is read 

            if (lineInput.length() != 0 ){
                String startLetter = lineInput.substring(0,1);

                if (startLetter.equals("M")) {
                    String movieInput = lineInput.substring(13);   
                    movieName = movieInput;
                    count++;

                } else if (startLetter.equals("D")) {
                    String movieInput = lineInput.substring(10);
                    movieDirector = movieInput;
                    count++;

                } else if (startLetter.equals("f")){
                    String movieInput = lineInput.substring(10);
                    movieFileSize = Integer.parseInt(movieInput);
                    count++;

                }else if (startLetter.equals("d")) {
                    String movieInput = lineInput.substring(10);
                    movieDuration = Integer.parseInt(movieInput);
                    count++;

                } 
                if (count == 4) {   
                    database.addData(movieName, movieDirector, movieFileSize, movieDuration);
                    count = 0;
                }

            }
        }
        System.out.println("IMPORT SUCCESSFUL");
        inputStream.close ();

    }
    //the below method gets the user to enter the movie details one by one. 
    //If there are duplicates, another method will be called which prevents the user from adding the movie
    private static void EnterMovies(MovieDatabase database)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Press Enter to add a new movie to the database");
        String movieName;
        String movieDirector;
        int movieFileSize;
        int movieDuration;

        console.nextLine();
        System.out.print("Movie Name: ");
        movieName = console.nextLine();   

        System.out.print("Movie Director: ");
        movieDirector = console.nextLine();             
        System.out.print("Movie FileSize: ");
        movieFileSize = console.nextInt();
        System.out.print("Movie Duration: ");
        movieDuration = console.nextInt();

        database.addData(movieName,movieDirector,movieFileSize,movieDuration);
    }

    public void searchMovieDirector(MovieDatabase database){
        Scanner console = new Scanner(System.in);
        String userDirector;
        System.out.println("Please press enter"); 
        console.nextLine(); 

        System.out.println("Please type in the director you want to search:"); 

        userDirector = console.nextLine();
        System.out.println("All movies with " + userDirector + " as a director: ");
        int count = 1;

        for (int i = 0; i < database.allMovies.length; i++)
        {
            if (database.allMovies[i] != null && userDirector.equalsIgnoreCase(database.allMovies[i].getDirector()))
            {
                System.out.println((count) + ". " + database.allMovies[i].getName());
                count++;
            }
        }

    }

    public void searchMovieDuration(MovieDatabase database){
        Scanner console = new Scanner(System.in);
        int userDuration;

        System.out.println("Please type in the duration in minutes:"); 

        userDuration = console.nextInt();

        System.out.println("All movies under " + userDuration + "mins: "); 

        int count = 1;
        for (int i = 0; i < database.getLogicalSize(); i++)
        {
            if (database.allMovies[i].getDuration() < userDuration)
            {
                System.out.println((count) + ". " + database.allMovies[i].getName());
                count++;
            }
        }

    }

    private void showAllMovies(MovieDatabase database)
    {
        int count;
        count = 0;
        for (int i = 0; i < database.allMovies.length; i++)
        {
            if (database.allMovies[i] != null)
            {
                System.out.println((count + 1) + ". ");
                System.out.println(database.showMovies(i));
                count++;
            }
        }
    }

    public void editMovie(MovieDatabase database){
        Scanner console = new Scanner(System.in);

        int indexMovie;
        int indexMovieEdit;
        String name;
        String director;
        int fileSize;
        int duration;

        System.out.println("Please enter what index you want to edit:");
        indexMovieEdit = console.nextInt();
        indexMovieEdit = indexMovieEdit - 1; 

        console.nextLine();
        System.out.print("Name: ");
        name =console.nextLine() ;
        System.out.print("Director: ");
        director = console.nextLine() ;
        System.out.print("FileSize: ");
        fileSize = console.nextInt() ;
        System.out.print("Duration:");
        duration = console.nextInt() ;

        database.editMovie(indexMovieEdit, name, director, fileSize, duration);

    }

    public void  createPlaylist(Playlist playlists[], MovieDatabase database)
    {
        int userinput;
        int movieTotal; 
        Scanner console = new Scanner(System.in);

        playlists[playlistsTotal] = new Playlist();

        movieTotal = 0;

        int count;
        count = 0;
        for (int i = 0; i < database.allMovies.length; i++)
        {
            if (database.allMovies[i] != null) // && database.allMovies[i] != database.allMovies[i+1] )
            {
                System.out.println("Current Movies");
                System.out.println((count + 1) + ". " + database.allMovies[i].getName());
                count++;
                System.out.println("");
            }
        }

        do {
            System.out.println("Please enter the movie number from above, 9 To Exit:");
            userinput = console.nextInt();

            if (userinput !=9 ){

                int currentSize = database.allMovies[userinput - 1].getFileSize() + playlists[playlistsTotal].getPlaylistSize(); //current size of the playlist
                int currentTime = database.allMovies[userinput - 1].getDuration() + playlists[playlistsTotal].getTotalTime(); //current total time of the playlist

                if (currentSize < playlists[playlistsTotal].getMaxSize()){
                    if (currentTime < playlists[playlistsTotal].getMaxTime()){

                        playlists[playlistsTotal].movie[movieTotal] = database.allMovies[userinput - 1];

                        //for total time of playlist
                        int timeAdd = playlists[playlistsTotal].movie[movieTotal].getDuration();
                        int addSize = playlists[playlistsTotal].movie[movieTotal].getFileSize();
                        playlists[playlistsTotal].setTotalTime(timeAdd);
                        playlists[playlistsTotal].setPlaylistNum();
                        playlists[playlistsTotal].setPlaylistSize(addSize);

                        System.out.println("Added!");
                        movieTotal++;

                        if (playlists[playlistsTotal].movie.length <= movieTotal){

                            playlists[playlistsTotal].movie = playlists[playlistsTotal].resize(playlists[playlistsTotal].movie, true);

                        }
                        else if (playlists[playlistsTotal].movie.length > movieTotal) {
                            playlists[playlistsTotal].movie = playlists[playlistsTotal].resize(playlists[playlistsTotal].movie, false);
                        }
                    }
                    else{
                        System.out.println("You have reached the maximum amount of playing time you can have in this playlist.");
                        System.out.println("");
                    }
                }
                else{
                    System.out.println("No more space in this playlist too add this movie");
                    System.out.println("");

                }
            }
        }while(userinput != 9);

        System.out.println("Playlist " + (playlistsTotal + 1) + " created!");
        System.out.println("");
        playlistsTotal++;

    } 

    public void showPlaylists(Playlist playlists[]) //shows all the playlists that have been created and lets the user choose which one to view.
    {
        Scanner console = new Scanner(System.in);
        int playlistSelect;
        int i;

        for (i = 0; i < playlists.length && playlists[i] != null; i++){
            System.out.println("Playlist " + i + 1);
            System.out.println("total time: " + playlists[i].getTotalTime()+"mins");
            System.out.println("number of movies: " + playlists[i].getPlaylistNum());
            System.out.println("total size: " + playlists[i].getPlaylistSize()+"mb");
            System.out.println("");    

        }

        System.out.println("Please enter a playlist number:");
        System.out.println("");

        playlistSelect = console.nextInt();
        System.out.println("");
        System.out.println("Playlist " + (playlistSelect));

        for (i =0; i < playlists[playlistSelect-1].movie.length && playlists[playlistSelect-1].movie[i] != null; i++)
        {

            System.out.println("");
            System.out.println("Name: " + playlists[playlistSelect-1].movie[i].getName());
            System.out.println("Director: " + playlists[playlistSelect-1].movie[i].getDirector());
            System.out.println("Duration: " + playlists[playlistSelect-1].movie[i].getDuration());
            System.out.println("FileSize: " + playlists[playlistSelect-1].movie[i].getFileSize());

        }  

    }

    public void deletePlaylists(Playlist[] playlists){ //deletes the entire playlist
        Scanner console = new Scanner(System.in);
        int userselect;
        int i;

        for (i = 0; i < playlists.length && playlists[i] != null; i++){
            System.out.println("Playlist " + i + 1);
        }
        System.out.println("Please enter what playlist you want to delete:"); 

        userselect = console.nextInt();

        playlists[userselect -1] = null;

    }

    public Playlist[] resize(Playlist[] playlists, int plus){
        Playlist[] playlistsResize = new Playlist[playlists.length+plus];

        for(int i=0; i< playlists.length; i++)
            playlistsResize[i] = playlists[i];
        return(playlistsResize);
    }

    public int getLogicalSize()
    {  
        logicalSize = playlistsTotal ;
        return(logicalSize);

    } 

    public static void main(String[] args)
    {
        Interface intFace = new Interface();
        intFace.run();
    }
}