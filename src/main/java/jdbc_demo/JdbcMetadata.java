package jdbc_demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class JdbcMetadata {
    public static void main(String[] args) {
        try (
                Connection connection = JdbcConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet set = statement.executeQuery("select `id`, `points`, `name` from `players`");
                ) {
            ResultSetMetaData meta = set.getMetaData();
            while(set.next()){
                for(int i = 1;  i <= meta.getColumnCount(); i++){
                    System.out.print(meta.getColumnName(i) + ": " + set.getObject(i)+", ");
                }
                System.out.println();
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
