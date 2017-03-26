package com.company;

/**
 * Created by gregtar on 25.03.17.
 */
import com.company.Customer.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public DBManager(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driverr");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (id, name, surname, birthDate, phone) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, customer.getId());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getSurname());
        statement.setDate(4, Date.valueOf(customer.getBirthDate().toString()));
        statement.setString(5, customer.getPhone());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Customer> listAllCustomers() throws SQLException {
        List<Customer> listCustomers = new ArrayList<>();

        String sql = "SELECT * FROM Autoservice";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate birthDate = LocalDate.parse("birthDate");
            String phone = resultSet.getString("phone");

            Customer customer = new Customer(id, name, surname, phone, birthDate);
            listCustomers.add(customer);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCustomers;
    }

    public boolean deleteCustomer(Customer customer) throws SQLException {
        String sql = "DELETE FROM Customer where id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, customer.getId());
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name = ?, surname = ?, phone = ?, birthDate = ? ";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, customer.getId());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getSurname());
        statement.setString(4, customer.getPhone());
        statement.setDate(5, Date.valueOf(customer.getBirthDate().toString()));


        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;


    }
    public Customer getCustomer(String id) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String phone = resultSet.getString("phone");
            LocalDate birthDate = LocalDate.parse(resultSet.getString("birthDate"));

            customer = new Customer(id, name, surname, phone, birthDate);
        }

        resultSet.close();
        statement.close();

        return customer;
    }
}

