package jdbc_demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcRowToObject {
    public static void main(String[] args) {
        try(
                Connection connection = JdbcConnection.connect();
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ) {
            JdbcConnection.createAndInsertPlayers(statement);
            statement.executeUpdate("insert into players(name, points) values('karol', NULL)");
            ResultSet set = statement.executeQuery("select * from players");
            List<Player> players = new ArrayList<>();
            while(set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                Timestamp registered = set.getTimestamp("registered");
                int points = set.getInt("points");
                players.add(new Player(id, name, points, registered));
            }
            set.close();
            Collections.sort(players, (a, b) -> Integer.compare(a.getPoints(), b.getPoints()));
            System.out.println(players);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
