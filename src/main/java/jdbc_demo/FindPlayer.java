package jdbc_demo;

import javax.swing.plaf.nimbus.State;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FindPlayer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        try (
                Connection connection = JdbcConnection.connect("jdbc:mysql://localhost/kurs81?allowMultiQueries=true", "root", "1234");
                Statement statement = connection.createStatement();
        ) {

            JdbcConnection.createAndInsertPlayers(statement);
            //UWAGA!!! SQLInjection
            ResultSet set = statement.executeQuery("SELECT * FROM players WHERE name = " + playerName);
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
