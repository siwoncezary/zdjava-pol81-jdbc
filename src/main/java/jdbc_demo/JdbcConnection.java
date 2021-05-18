package jdbc_demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {

    public static final String COM_MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection connect() throws ClassNotFoundException,
            SQLException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        try {
            DriverManager.getDriver(COM_MYSQL_CJ_JDBC_DRIVER);
        } catch (SQLException throwables) {
            Class.forName(COM_MYSQL_CJ_JDBC_DRIVER).getConstructor().newInstance();
        }
        return DriverManager.getConnection("jdbc:mysql://localhost/kurs81", "root", "1234");
    }

    public static Connection connect(String base, String user, String password) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            DriverManager.getDriver(COM_MYSQL_CJ_JDBC_DRIVER);
        } catch (SQLException throwables) {
            Class.forName(COM_MYSQL_CJ_JDBC_DRIVER).getConstructor().newInstance();
        }
        return DriverManager.getConnection(base, user, password);
    }

    public static void createAndInsertPlayers(Statement statement) throws SQLException {
        statement.execute("drop table if exists players");
        boolean isResult = statement.execute("create table players(id int primary key auto_increment, name varchar(15), points int, registered timestamp not null default current_timestamp())");
        System.out.println("Czy polecenie zwróciło wynik? " + isResult);
        statement.execute("insert into players values(1, 'alex', 123, current_timestamp()), " +
                "(2, 'ewa', 45,  current_timestamp())," +
                "(3, 'karol', 415,  current_timestamp())");
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Connection connection = connect();
        Statement statement = connection.createStatement();
        createAndInsertPlayers(statement);
        connection.close();
        statement.close();
    }
}
