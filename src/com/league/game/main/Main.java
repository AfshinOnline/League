package com.league.game.main;


import com.league.factory.GameFactory;
import com.league.game.Game;
import com.league.dao.GameDao;
import com.league.game.utils.Statics;
import com.league.impl.GameDaoImpl;
import java.util.Scanner;


/**
 * This class is the main demo class for the Game application.
 *
 * This is a program that creates a command-line application that will calculate
 * the ranking table for a soccer league.
 *
 * @author Afshin Ghaziasgar Jan 2015
 *
 */
public class Main {

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Scanner sc = new Scanner(System.in);
       System.out.println("Please enter input file name containing League results and press enter. Remember to place the file in the "
                + "project file.");
       String inputFile;
       inputFile = sc.nextLine(); 
       
      if(!inputFile.endsWith(".txt")){
       
           inputFile += ".txt"; 
      }
        Statics.filePaths.file_path_in = inputFile;
       System.out.println(" Please enter output file name and press enter. This will created and populated in the project file");
       String outputFile = sc.nextLine();
       
       if(!outputFile.endsWith(".txt")){
       
           outputFile += ".txt"; 
           
       }
       Statics.filePaths.file_path_out= outputFile;
       
       System.out.println("Please check output file for results.");
       GameFactory gameFactory = new GameFactory();
       Game game = gameFactory.createGame();
       GameDao dao = new GameDaoImpl(game);
       dao.readInput(inputFile);
       dao.writeFile(outputFile);
      
      }
   
}
