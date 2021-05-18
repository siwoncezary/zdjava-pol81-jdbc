package jdbc_demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUpdate {
    public static void main(String[] args) {
        try(
            Connection connect = JdbcConnection.connect();
            Statement statement = connect.createStatement();
        ){
            JdbcConnection.createAndInsertPlayers(statement);
            int rowsInserted = statement.executeUpdate("insert into players(name, points) values('tom', 100)", Statement.RETURN_GENERATED_KEYS);
            System.out.println("Dodano nowych wierszy: " + rowsInserted);
            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next()) {
                System.out.println("Wygenerowany klucz nowego rekordu: " + keys.getInt(1));
            }
            int rowUpdated = statement.executeUpdate("update players set points = 200 where id = 3");
            System.out.println("Zmieniono rekordów: " + rowUpdated);

            int rowDeleted = statement.executeUpdate("delete from players where id = 3");
            System.out.println("Usunięto rekordów: " + rowDeleted);

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
