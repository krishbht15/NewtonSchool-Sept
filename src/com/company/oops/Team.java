package com.company.oops;

import java.util.ArrayList;

public class Team {
    public String name;
    public Player coach;
    public Player captain;
    public ArrayList<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Player getCoach() {
        return coach;
    }

    public Player getCaptain() {
        return captain;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void setCoach(Player coach) {
        this.coach = coach;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }
}
