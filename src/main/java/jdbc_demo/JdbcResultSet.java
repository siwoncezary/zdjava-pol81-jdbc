package jdbc_demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class JdbcResultSet {
    public static void main(String[] args) {
        try(
                Connection connection = JdbcConnection.connect();
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ) {
            JdbcConnection.createAndInsertPlayers(statement);
            statement.executeUpdate("insert into players(name, points) values('karol', NULL)");
            ResultSet set = statement.executeQuery("select * from players");
            while(set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                Timestamp registered = set.getTimestamp("registered");
                int points = set.getInt("points");
                if (set.wasNull()){
                    System.out.println("id: " + id +", name: " + name +", points: null" +", registered: " + registered);
                } else {
                    System.out.println("id: " + id +", name: " + name +", points: " + points +", registered: " + registered);
                }
                if (set.isLast()){
                    set.moveToInsertRow();
                    set.updateString("name", "player");
                    set.updateInt("points", 500);
                    set.insertRow();
                    break;
                }
            }
            set.beforeFirst();
            while(set.next()){
                System.out.println("name: " + set.getString("name"));
            }
            set.close();
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
