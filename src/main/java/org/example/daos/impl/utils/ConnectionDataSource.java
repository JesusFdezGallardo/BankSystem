package org.example.daos.impl.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataSource {
  private static Connection connection;

  private ConnectionDataSource() {
  }

  public static Connection getConnection() throws SQLException {

    if (connection == null) {
      String url = "jdbc:mysql://localhost:3306/bank?createDatabaseIfNotExist=true&serverTimezone=UTC";
      String username = "root";
      String password = "";
      connection = DriverManager.getConnection(url, username, password);
    }

    return connection;
  }
}
