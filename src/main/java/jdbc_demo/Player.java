package jdbc_demo;

import java.sql.Timestamp;

public class Player {
    private int id;
    private String name;
    private int points;
    private Timestamp registered;

    public Player(int id, String name, int points, Timestamp registered) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.registered = registered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Timestamp getRegistered() {
        return registered;
    }

    public void setRegistered(Timestamp registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", registered=" + registered +
                '}';
    }
}
