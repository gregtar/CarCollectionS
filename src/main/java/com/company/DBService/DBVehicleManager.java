package com.company.DBService;


import com.company.Customer.Customer;
import com.company.Vehicle.Vehicle;
import com.company.Vehicle.Color;


import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by gregtar on 28.03.17.
 */
public class DBVehicleManager {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public DBVehicleManager(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
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
    public boolean createVehicleTable() throws SQLException {
        String sql = "CREATE TABLE Vehicle(" +
                "numberPlate varchar(255) PRIMARY KEY NOT NULL," +
                "mark varchar(255)," +
                "model varchar(255)," +
                "color VARCHAR(255)," +
                "customerId varchar(255) references Customer(id)" +
                ");";

        connect();
        Statement statement = jdbcConnection.createStatement();
        boolean tableCreated = statement.executeUpdate(sql)>0;
        statement.close();
        disconnect();
        return tableCreated;
    }

    public boolean dropVehicles() throws SQLException {
        String sql = "DROP TABLE Vehicle;";

        connect();

        Statement statement = jdbcConnection.createStatement();
        boolean tableDropped = statement.executeUpdate(sql) > 0;
        statement.close();
        disconnect();
        return tableDropped;
    }

    public List<Vehicle> VehiclesList() throws SQLException {
        List<Vehicle> Vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String mark = resultSet.getString("mark");
            String model = resultSet.getString("model");
            Color color = Color.valueOf(resultSet.getString("color"));
            String numberPlate = resultSet.getString("numberPlate");
      //      Vehicle vehicle = new Vehicle().setMark(mark).setModel(model).setColor(color).setNumberPlate(numberPlate).build();
      //      Vehicles.add(vehicle);
        }
        resultSet.close();
        statement.close();

        disconnect();
        return Vehicles;
    }

    public List<Vehicle> CustomersVehicles(String id ) throws SQLException {
        List<Vehicle> VehicleList = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE customerId = ?;";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String numberPlate = resultSet.getString("numberPlate");
            String mark = resultSet.getString("mark");
            String model = resultSet.getString("model");
            Color color = Color.valueOf(resultSet.getString("color"));
   //         VehiclesList().add(vehicle);
        }
        resultSet.close();
        statement.close();
        return VehicleList;
    }
    public boolean insertVehicleToCustomer(Vehicle vehicle, String customerId) throws SQLException {
         String sql = "INSERT INTO Vehicle (mark, model, color, numberPlate,  customerId) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
        connect();
       PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1,vehicle.getMark());
       statement.setString(2,vehicle.getModel());
        statement.setString(3,vehicle.getColor().toString());
        statement.setString(4,vehicle.getNumberPlate());
        statement.setString(5,customerId);
//
        boolean rowInserted = statement.executeUpdate()>0;

       statement.close();
        disconnect();

       return rowInserted;}

    public boolean hardDeleteUserCarsById(String id) throws SQLException {
        String sql = "DELETE FROM Vehicle WHERE customerId = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean deleteCustomerOfVehicle(String id) throws SQLException {
        String sql = "UPDATE Vehicle SET isDeleted = true WHERE customerId = ?;";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return rowDeleted;
    }


    public boolean changeOwner(String numberPlate, String newOwnerId) throws SQLException {
            String sql = "UPDATE cars SET user_id = ? WHERE number = ?";
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, newOwnerId);
            statement.setString(2, numberPlate);

            boolean ownerChanged = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
            return ownerChanged;}

    public boolean updateVehicleTable(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE Vehicle SET  mark = ? , model = ?, color = ?   WHERE numberPlate = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,vehicle.getMark());
        statement.setString(2,vehicle.getModel());
        statement.setString(3,vehicle.getColor().toString());
        statement.setString(4,vehicle.getNumberPlate());
        boolean isChanged = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return isChanged;

}
}
