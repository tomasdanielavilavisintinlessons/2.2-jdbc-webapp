package com.example.jdbcWebappExercise.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHandlerImpl implements ConnectionHandler {
    private Connection connection;

    private static ConnectionHandler INSTANCE = new ConnectionHandlerImpl();

    private ConnectionHandlerImpl() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource dataSource  = (DataSource) envContext.lookup("jdbc/SchoolDB");

            this.connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
