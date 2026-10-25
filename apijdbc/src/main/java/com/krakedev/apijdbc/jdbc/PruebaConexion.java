package com.krakedev.apijdbc.jdbc;

import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        Connection conexion = null;

        try {

            conexion = Conexion.conectar();

            if (conexion != null) {

                System.out.println("Conexion Exitosa");

            } else {

                System.out.println("No fue posible conectar");
            }

        } finally {

            try {

                if (conexion != null) {
                    conexion.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}