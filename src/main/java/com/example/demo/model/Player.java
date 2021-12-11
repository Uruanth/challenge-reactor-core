package com.example.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jugadores")
public class Player implements Comparable<Player>{

    @Id
    public String id;
    private int codePlayer;
    public String name;
    public int age;
    public String icon;
    public String national;
    public int winners;
    public int games;
    public String club;

    public Player() {

    }

    public Player(int codePlayer, String name, int age, String icon, String national, int winners, int games, String club) {

        this.codePlayer = codePlayer;
        this.name = name;
        this.age = age;
        this.icon = icon;
        this.national = national;
        this.winners = winners;
        this.games = games;
        this.club = club;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", codePlayer=" + codePlayer +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", icon='" + icon + '\'' +
                ", national='" + national + '\'' +
                ", winners=" + winners +
                ", games=" + games +
                ", club='" + club + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCodePlayer() {
        return codePlayer;
    }

    public void setCodePlayer(int codePlayer) {
        this.codePlayer = codePlayer;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public int getWinners() {
        return winners;
    }

    public void setWinners(int winners) {
        this.winners = winners;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    @Override
    public int compareTo(Player o) {
        if((o.getWinners() - o.getGames())<0) return -1;
        else if((o.getWinners() - o.getGames()) == o.getWinners()) return 0;
        else  return 1;
    }
}
