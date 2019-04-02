package com.apps.harsh.zest.model;

import java.util.HashMap;
import java.util.Map;

public class Match {

    private String id, gameType, matchType, team1, team2, date, time, place, score1, score2, status, set, team1Win, team2Win, result, ball, inning, over, target, wicket, run, away1, away2, status1, status2, desc;

    public Match() {
    }

    public Match(String gameType, String matchType, String team1, String team2, String date, String time, String place, String score1, String score2, String status, String desc) {
        this.gameType = gameType;
        this.matchType = matchType;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
        this.place = place;
        this.score1 = score1;
        this.score2 = score2;
        this.status = status;
        this.desc = desc;
    }

    public Match(String id, String gameType, String matchType, String team1, String team2, String date, String time, String place, String score1, String score2, String status, String desc) {
        this.id = id;
        this.gameType = gameType;
        this.matchType = matchType;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;

        this.time = time;
        this.place = place;
        this.score1 = score1;
        this.score2 = score2;
        this.status = status;
        this.desc = desc;
    }

    //Set
    public Match(String gameType, String matchType, String team1, String team2, String date, String time, String place, String score1, String score2, String status, String set, String team1Win, String team2Win, String result) {
        this.gameType = gameType;
        this.matchType = matchType;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
        this.place = place;
        this.score1 = score1;
        this.score2 = score2;
        this.status = status;
        this.set = set;
        this.team1Win = team1Win;
        this.team2Win = team2Win;
        this.result = result;

    }

    /*public Match(String id, String gameType, String matchType, String team1, String team2, String date, String time, String place, String score1, String score2, String status, String set, String team1Win, String team2Win, String result) {
        this.id = id;
        this.gameType = gameType;
        this.matchType = matchType;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
        this.place = place;
        this.score1 = score1;
        this.score2 = score2;
        this.status = status;
        this.set = set;
        this.team1Win = team1Win;
        this.team2Win = team2Win;
        this.result = result;
    }*/

    //Cricket
    public Match(String gameType, String matchType, String team1, String team2, String date, String time, String place, String score1, String score2, String status, String ball, String inning, String over, String target, String wicket, String result, String run) {
        this.gameType = gameType;
        this.matchType = matchType;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
        this.place = place;
        this.score1 = score1;
        this.score2 = score2;
        this.status = status;
        this.ball = ball;
        this.inning = inning;
        this.over = over;
        this.target = target;
        this.wicket = wicket;
        this.result = result;
        this.run = run;
    }

    /*public Match(String id, String gameType, String matchType, String team1, String team2, String date, String time, String place, String score1, String score2, String status, String ball, String inning, String over, String target, String wicket, String result, String run) {
        this.id = id;
        this.gameType = gameType;
        this.matchType = matchType;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
        this.place = place;
        this.score1 = score1;
        this.score2 = score2;
        this.status = status;
        this.ball = ball;
        this.inning = inning;
        this.over = over;
        this.target = target;
        this.wicket = wicket;
        this.result = result;
        this.run = run;
    }*/

    //Baseball
    public Match(String gameType, String matchType, String team1, String team2, String date, String time, String place, String score1, String score2, String status, String away1, String away2, String status1, String status2, String inning, String result) {
        this.gameType = gameType;
        this.matchType = matchType;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
        this.place = place;
        this.score1 = score1;
        this.score2 = score2;
        this.status = status;
        this.away1 = away1;
        this.away2 = away2;
        this.status1 = status1;
        this.status2 = status2;
        this.inning = inning;
        this.result = result;
    }

    /*public Match(String id, String gameType, String matchType, String team1, String team2, String date, String time, String place, String score1, String score2, String status, String away1, String away2, String status1, String status2, String inning, String result) {
        this.id = id;
        this.gameType = gameType;
        this.matchType = matchType;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
        this.place = place;
        this.score1 = score1;
        this.score2 = score2;
        this.status = status;
        this.away1 = away1;
        this.away2 = away2;
        this.status1 = status1;
        this.status2 = status2;
        this.inning = inning;
        this.result = result;
    }*/

    public String getSet() {
        return set;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getTeam1Win() {
        return team1Win;
    }

    public void setTeam1Win(String team1Win) {
        this.team1Win = team1Win;
    }

    public String getTeam2Win() {
        return team2Win;
    }

    public void setTeam2Win(String team2Win) {
        this.team2Win = team2Win;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
    }

    public String getInning() {
        return inning;
    }

    public void setInning(String inning) {
        this.inning = inning;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getWicket() {
        return wicket;
    }

    public void setWicket(String wicket) {
        this.wicket = wicket;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getAway1() {
        return away1;
    }

    public void setAway1(String away1) {
        this.away1 = away1;
    }

    public String getAway2() {
        return away2;
    }

    public void setAway2(String away2) {
        this.away2 = away2;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("GameType", this.gameType);
        result.put("MatchType", this.matchType);
        result.put("Team1", this.team1);
        result.put("Team2", this.team2);
        result.put("Date", this.date);
        result.put("Time", this.time);
        result.put("Place", this.place);
        result.put("Score1", this.score1);
        result.put("Score2", this.score2);
        result.put("Status", this.status);
        return result;
    }
}
