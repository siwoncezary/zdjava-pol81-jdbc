package jdbc_demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Scanner;

public class JdbcTransaction {
    public static void main(String[] args) {
        try (
                Connection connection = JdbcConnection.connect();
                Statement statement = connection.createStatement();
        ) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            Savepoint begin = connection.setSavepoint();
            statement.executeUpdate("update players set points = points - 50 where id = 2");
            statement.executeUpdate("update players set points = points + 50 where id = 3");
            new Scanner(System.in).next();
            ResultSet set = statement.executeQuery("select points from players where id = 2");
            if (set.next()){
                if (set.getInt(1) >= 0) {
                    connection.commit();
                } else {
                    connection.rollback(begin);
                }
            } else {
                connection.rollback(begin);
            }
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
