package jdbc_demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Scanner;

public class JdbcPreparedStatement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        try (
                Connection connection = JdbcConnection.connect();
                //Znak zapytania jest parametrem
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE name = ?");
        ) {
            //Tutaj pod znak zapytania wstawianym wartość parametru
            statement.setString(1, playerName);
            ResultSet set = statement.executeQuery();
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
