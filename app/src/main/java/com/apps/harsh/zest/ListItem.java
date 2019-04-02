package com.apps.harsh.zest;

public class ListItem {

    private String head;
    private String desc;
    private String sport;
    private String description;
    private String date;
    private String time;
    private String match_place;
    private String match_status;
    private String winner;
    private String runner;
    private String score1;
    private String score2;

    private String set;
    private String team1_set;
    private String team2_set;


    private String over;
    private String ball;
    private String status1;
    private String status2;
    private String wicket;
    private String inning;


    private String target;


    public ListItem(String head, String desc, String sport, String description, String date, String time, String match_place, String match_status, String winner, String runner, String score1, String score2, String set, String team1_set, String team2_set, String over, String ball, String status1, String status2, String wicket, String inning, String target) {
        this.head = head;
        this.desc = desc;
        this.sport = sport;
        this.description = description;
        this.time = time;
        this.date = date;
        this.match_place = match_place;
        this.match_status = match_status;
        this.winner = winner;
        this.runner = runner;
        this.score1 = score1;
        this.set = set;
        this.team1_set = team1_set;
        this.team2_set = team2_set;
        this.score2 = score2;
        this.over = over;
        this.ball = ball;
        this.status1 = status1;
        this.status2 = status2;
        this.wicket = wicket;
        this.inning = inning;
        this.target = target;
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

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getSport() {
        return sport;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMatch_place() {
        return match_place;
    }

    public void setMatch_place(String match_place) {
        this.match_place = match_place;
    }
    public String getMatch_status() {
        return match_status;
    }

    public void setMatch_status(String match_status) {
        this.match_status = match_status;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getRunner() {
        return runner;
    }

    public void setRunner(String runner) {
        this.runner = runner;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getTeam1_set() {
        return team1_set;
    }

    public void setTeam1_set(String team1_set) {
        this.team1_set = team1_set;
    }

    public String getTeam2_set() {
        return team2_set;
    }

    public void setTeam2_set(String team2_set) {
        this.team2_set = team2_set;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
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

    public String getWicket() {
        return wicket;
    }

    public void setWicket(String wicket) {
        this.wicket = wicket;
    }

    public String getInning() {
        return inning;
    }

    public void setInning(String inning) {
        this.inning = inning;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
