package com.gft.testingtraining.arquillian.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DatabaseInitializer {
    private final Connection connection;

    public DatabaseInitializer(Connection connection) {
        this.connection = connection;
    }
    
    public void clearData() throws SQLException {
        connection.prepareStatement("DELETE FROM BasicModelUnit").executeUpdate();
    }

    public void insertData() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO BasicModelUnit (id, text) values (?, ?)");
        
        for (int id = 0; id < 10; id++) {
            statement.setInt(1, 100 + id);
            statement.setString(2, "Hello " + id);
            statement.addBatch();
        }
        
        statement.executeBatch();
    }
}
