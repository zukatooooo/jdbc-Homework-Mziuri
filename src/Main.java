import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test_JDBC", "root", "paroli");

        Statement createTableStatement = connection.createStatement();
        createTableStatement.executeUpdate("CREATE TABLE person (id INT, name VARCHAR(45), last_name VARCHAR(45), age INT, sqesi VARCHAR(255))");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id, firstname, lastname, age, and sqesi:");

        int id = scanner.nextInt();
        String name = scanner.next();
        String lastName = scanner.next();
        int age = scanner.nextInt();
        String sqesi = scanner.next();

        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Person VALUES (?, ?, ?, ?, ?)");
        insertStatement.setInt(1, id);
        insertStatement.setString(2, name);
        insertStatement.setString(3, lastName);
        insertStatement.setInt(4, age);
        insertStatement.setString(5, sqesi);
        insertStatement.executeUpdate();

        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery("SELECT * FROM Person");

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id") + ", Firstname: " + resultSet.getString("name") + ", LastName: " + resultSet.getString("last_name") + ", Age: " + resultSet.getInt("age") + ", Sqesi: " + resultSet.getString("sqesi"));
        }

        ResultSet ageResultSet = selectStatement.executeQuery("SELECT * FROM Person WHERE age > 10");

        while (ageResultSet.next()) {
            System.out.println("ID: " + ageResultSet.getInt("id") + ", Firstname: " + ageResultSet.getString("name") + ", LastName: " + ageResultSet.getString("last_name") + ", Age: " + ageResultSet.getInt("age") + ", Sqesi: " + ageResultSet.getString("sqesi"));
        }
    }
}
