package com.league.game;

import com.league.game.interfaces.GameInterface;

/**
 * The Game class containing definitions and methods for the Game object.
 *
 * @author Afshin Ghaziasgar Jan 2015
 *
 *
 */
public class Game implements GameInterface {

    private int scoreTeamA;
    private int scoreTeamB;
    private String club_A_Name;
    private String club_B_Name;

    /**
     * No Arg-Contructor
     */
    public Game() {

        this.scoreTeamA = this.scoreTeamB = 0;
        this.club_A_Name = this.club_B_Name = "";

    }

    public int getScoreTeamA() {
        return scoreTeamA;
    }

    public void setScoreTeamA(int scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }

    public int getScoreTeamB() {
        return scoreTeamB;
    }

    public void setScoreTeamB(int scoreTeamB) {
        this.scoreTeamB = scoreTeamB;
    }

    public String getClub_A_Name() {
        return club_A_Name;
    }

    public void setClub_A_Name(String club_A_Name) {
        this.club_A_Name = club_A_Name;
    }

    public String getClub_B_Name() {
        return club_B_Name;
    }

    public void setClub_B_Name(String club_B_Name) {
        this.club_B_Name = club_B_Name;
    }

    public int returnWinner() {

        if (scoreTeamA == scoreTeamB) {

            return 0;

        } else if (scoreTeamA > scoreTeamB) {

            return 1;

        } else {

            return 2;
        }

    }

}
