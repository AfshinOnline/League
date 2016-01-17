package com.league.impl;

import com.league.dao.GameDao;
import com.league.game.Game;
import com.league.game.utils.Statics;
import static com.league.game.utils.Statics.results.DRAW;
import static com.league.game.utils.Statics.results.LOST;
import static com.league.game.utils.Statics.results.WON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.MalformedInputException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the Game factory.
 *
 * @author Afshin Ghaziasgar jsn 2015
 */
public class GameDaoImpl implements GameDao {

    private static final Logger log = Logger.getLogger(GameDaoImpl.class.getSimpleName());

    Game game;
    HashMap<String, Integer> leagueTable;

    /**
     * Args constructor
     *
     * @param game is an object of class Game.
     */
    public GameDaoImpl(Game game) {

        this.game = game;
        leagueTable = new HashMap<String, Integer>();

    }

    /**
     * A method that writes to the specified file the sorted and correctly numbered
     * values from a HashMap containing Clubs and Scores.
     *
     * @param fileName The file name of the output file.
     * @return message Is the message of successful completion.
     */
    @Override
    public String writeFile(String fileName) {

        PrintWriter outputStream = null;

        try {
            File output = new File(getFileAbsolutePath(fileName));
            //output.mkdirs();
            //File file = new File(output, "Output");
            FileWriter filewriter = new FileWriter(output);

            outputStream = new PrintWriter(filewriter);

            Map<String, Integer> map = sortByValues(leagueTable);
            int count = 1;
            int counter = 1;
            int previousScore = 0;
            int relativeCount = 0;
            int n = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {

                int value = entry.getValue();

                if (previousScore == entry.getValue()) {

                    if (n == 0) {
                        relativeCount = counter - 1;
                        n++;
                    }
                    outputStream.println(relativeCount + " " + entry.getKey() + ", " + entry.getValue() + " pts");
                } else {
                    n = 0;
                    outputStream.println(counter + " " + entry.getKey() + ", " + entry.getValue() + " pts");
                    previousScore = entry.getValue();

                }
                counter++;

            }

        } catch (MalformedInputException ex) {

            log.log(Level.SEVERE, ex.toString(), ex.getStackTrace());

        } catch (IOException ex) {
            log.log(Level.SEVERE, ex.toString(), ex.getStackTrace());

        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.toString(), ex.getStackTrace());

        } finally {
            if (outputStream != null) {
                try {

                    outputStream.close();

                    log.log(Level.INFO, "Closed output PrintWriter");
                    return Statics.messages.WRITE_COMPLETE;
                } catch (NullPointerException ex) {
                    log.log(Level.SEVERE, ex.toString(), ex.getStackTrace());
                } catch (Exception ex) {

                    log.log(Level.SEVERE, ex.toString(), ex.getStackTrace());
                }
            } else {
                log.log(Level.SEVERE, "Failed to read file.");
                throw new RuntimeException("FileActions:writeFile:Failed to read file.");

            }
        }
        return "";
    }

    /**
     * A method to read the input from a file and populates data into a container
     * accordingly.
     *
     * @param fileName The file name of the input file.
     * @return message Is the message of successful completion.
     */
    @Override
    public String readInput(String fileName) {
        BufferedReader in = null;
        try {

            File file = new File(getFileAbsolutePath(fileName));
            in = new BufferedReader(new FileReader(file));

            String str;
            int i = 0;
            //Reads each line while a line exists
            while ((str = in.readLine()) != null) {
                System.out.println("");

                //Seperates the lines by splitting using commas 
                String[] instanceArray = str.split(",");
                game.setClub_A_Name(getChars(instanceArray[0]).trim());
                game.setClub_B_Name(getChars(instanceArray[1]).trim());
                game.setScoreTeamA(getNumber(instanceArray[0]));
                game.setScoreTeamB(getNumber(instanceArray[1]));

                //Checks if club already in league table
                checkValidity(game.getClub_A_Name(), game.getClub_B_Name());

                //Adds points to Clubs in Hashmap
                addToList(game.getClub_A_Name(), game.getClub_B_Name());

            }

        } catch (IOException e) {
            log.log(Level.INFO, "Exception input" + e.getMessage());

        } finally {
            if (in != null) {
                try {
                    in.close();
                    log.log(Level.INFO, "Closed input BufferedReader.");
                    return Statics.messages.READ_COMPLETE;
                } catch (IOException ex) {
                    log.log(Level.SEVERE, ex.toString(), ex.getStackTrace());
                }
            } else {

                log.log(Level.SEVERE, "FileActions:readInput:Failed to read file.");
                throw new RuntimeException(" Failed to read file.");
            }

        }
        return "";
    }

    /**
     * A method that takes in a String sequence possibly containing Chars and
     * returns only the converted integer section.
     *
     * @param text
     * @return
     */
    public static int getNumber(String text) {
        return Integer.parseInt(text.replaceAll("\\D", ""));
    }

    /**
     * A method that takes in a String sequence possibly containing Integers and
     * returns only the Char section.
     *
     * @param text
     * @return
     */
    public static String getChars(String text) {
        return text.replaceAll("\\d", "");
    }

    /**
     * A method to determine the absolute handled file path.
     *
     * @param filename of file to get absolute file path.
     * @return String message absolute file path.
     */
    @Override
    public String getFileAbsolutePath(String filename) {

        String workingDirectory = System.getProperty("user.dir");
        String absoluteFilePath = workingDirectory + File.separator + filename;

        if (absoluteFilePath == null) {
            log.log(Level.SEVERE, "Cannot access file path.");
            throw new RuntimeException(" Cannot acess file path: getFileAbsolutePath");
        }
        return absoluteFilePath;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * A Method that calculates and adds the relevant score representation
     * (Draw, Win, Lose) to each club in the form of the value in the HashMap.
     *
     * @param club_A_Name Is the name of the first Club in the fixture.
     * @param club_B_Name Is the name of the second club in the fixture.
     * @return
     */
    @Override
    public HashMap<String, Integer> addToList(String club_A_Name, String club_B_Name) {

        int clubA = leagueTable.get(club_A_Name);
        int clubB = leagueTable.get(club_B_Name);

        if (game.returnWinner() == 0) {

            leagueTable.put(club_A_Name, clubA + DRAW);
            leagueTable.put(club_B_Name, clubB + DRAW);

        } else if (game.returnWinner() == 1) {
            leagueTable.put(club_A_Name, leagueTable.get(club_A_Name) + WON);
            leagueTable.put(club_B_Name, leagueTable.get(game.getClub_B_Name()) + LOST);

        } else {
            leagueTable.put(club_A_Name, leagueTable.get(club_A_Name) + LOST);
            leagueTable.put(club_B_Name, leagueTable.get(game.getClub_B_Name()) + WON);

        }
        return leagueTable;
    }

    /**
     * A method that checks if the HashMap contains the name of the club
     * otherwise adds the name as a new key.
     *
     * @param clubA This is the name of the first club to check.
     * @param clubB This is the name of the second club to check.
     */
    @Override
    public void checkValidity(String clubA, String clubB) {
        if (!leagueTable.containsKey(clubA)) {

            leagueTable.put(clubA, 0);

        }
        if (!leagueTable.containsKey(clubB)) {

            leagueTable.put(clubB, 0);

        }
    }

    /**
     * A comparator that sorts the input Map by value then the keys of similar
     * values alphabetically.
     *
     * @param <K> This is the Key of the Map.
     * @param <V> This is the Value of the Map.
     * @param map The Map is the container to sort.
     * @return Map of sorted values and alphabetically sorted keys of similar
     * values.
     */
    public <K extends Comparable<K>, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k2).compareTo(map.get(k1));
                if (compare == 0) {
                    return k1.compareTo(k2); // <- To sort alphabetically
                } else {
                    return compare;
                }
            }
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    /**
     * A Method that returns the HashMap containing League scores in the form of
     * Key as club names and Value as score.
     *
     * @return HashMap containing league scores.
     */
    public HashMap<String, Integer> getLeagueTable() {
        return leagueTable;
    }

    /**
     * A setter for the HashMap containing league scores.
     *
     * @param leagueTable is the HashMap with league scores.
     */
    public void setLeagueTable(HashMap<String, Integer> leagueTable) {
        this.leagueTable = leagueTable;
    }

}
