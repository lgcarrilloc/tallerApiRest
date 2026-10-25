package com.krakedev.apijdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {

    private static final Logger logger =
            LoggerFactory.getLogger(Conexion.class);

    private static final String URL =
            "jdbc:postgresql://localhost:5432/apijdbc";

    private static final String USER = "postgres";

    private static final String PASSWORD = "luis2809";

    public static Connection conectar() {

        Connection conexion = null;

        try {

            Class.forName("org.postgresql.Driver");

            conexion = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD);

            logger.info("Conexion realizada");

        } catch (ClassNotFoundException e) {

            logger.error("Driver PostgreSQL no encontrado", e);

        } catch (SQLException e) {

            logger.error("Error al conectar con PostgreSQL", e);

        }

        return conexion;
    }
}