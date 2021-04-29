package dev.tribble.database;

import java.sql.Connection;
import java.sql.DriverManager;

public enum DBConnection {
    INSTANCE;

    private Connection connection;

    private DBConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://enterprise.c8zc9lkfuycr.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=tribble", "tribble_system", "tribbletime");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConnection(){
        return this.connection;
    }
}
