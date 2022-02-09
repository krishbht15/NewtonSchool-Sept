package com.company.oops.cricbuzz;

import java.util.ArrayList;

public class Match {

    public Team A;
    public Team B;
    public MatchType type;
    public String venue;
    public Team tossWonBy;
    public Team firstBatting;
    public ArrayList<Innings> innings;

    public Match(Team a, Team b, MatchType type, String venue) {
        A = a;
        B = b;
        this.type = type;
        this.venue = venue;
        this.innings = new ArrayList<>();
    }

    public Match(Team a, Team b) {
        A = a;
        B = b;
    }

    public Team getA() {
        return A;
    }

    public Team getB() {
        return B;
    }

    public MatchType getType() {
        return type;
    }

    public String getVenue() {
        return venue;
    }

    public void setType(MatchType type) {
        this.type = type;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Team getTossWonBy() {
        return tossWonBy;
    }

    public void setTossWonBy(Team tossWonBy) {
        this.tossWonBy = tossWonBy;
    }

    public Team getFirstBatting() {
        return firstBatting;
    }

    public void setFirstBatting(Team firstBatting) {
        this.firstBatting = firstBatting;
    }
}
