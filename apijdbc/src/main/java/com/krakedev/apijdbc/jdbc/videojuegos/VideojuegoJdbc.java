package com.krakedev.apijdbc.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakedev.apijdbc.entidades.Videojuego;
import com.krakedev.apijdbc.jdbc.Conexion;

public class VideojuegoJdbc {

    private static final Logger logger =
            LoggerFactory.getLogger(VideojuegoJdbc.class);

    public Videojuego insertar(Videojuego videojuego) {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = Conexion.conectar();

            String sql = """
                    INSERT INTO videojuegos
                    (codigo,nombre,plataforma,precio,disponible,genero)
                    VALUES (?,?,?,?,?,?)
                    """;

            ps = con.prepareStatement(sql);

            ps.setString(1, videojuego.getCodigo());
            ps.setString(2, videojuego.getNombre());
            ps.setString(3, videojuego.getPlataforma());
            ps.setDouble(4, videojuego.getPrecio());
            ps.setBoolean(5, videojuego.isDisponible());
            ps.setString(6, videojuego.getGenero());

            ps.executeUpdate();

            logger.info("Videojuego insertado");

            return videojuego;

        } catch (Exception e) {

            logger.error("Error al insertar videojuego", e);

        } finally {

            try {

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (Exception e) {

                logger.error("Error al cerrar recursos", e);
            }
        }

        return null;
    }

    public List<Videojuego> listar() {

        List<Videojuego> lista = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = Conexion.conectar();

            String sql = "SELECT * FROM videojuegos";

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                Videojuego v = new Videojuego();

                v.setCodigo(rs.getString("codigo"));
                v.setNombre(rs.getString("nombre"));
                v.setPlataforma(rs.getString("plataforma"));
                v.setPrecio(rs.getDouble("precio"));
                v.setDisponible(rs.getBoolean("disponible"));
                v.setGenero(rs.getString("genero"));

                lista.add(v);
            }

            logger.info("Listado de videojuegos obtenido");

        } catch (Exception e) {

            logger.error("Error al listar videojuegos", e);

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (Exception e) {

                logger.error("Error al cerrar recursos", e);
            }
        }

        return lista;
    }

    public Videojuego buscar(String codigo) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = Conexion.conectar();

            String sql =
                    "SELECT * FROM videojuegos WHERE codigo = ?";

            ps = con.prepareStatement(sql);

            ps.setString(1, codigo);

            rs = ps.executeQuery();

            if (rs.next()) {

                Videojuego v = new Videojuego();

                v.setCodigo(rs.getString("codigo"));
                v.setNombre(rs.getString("nombre"));
                v.setPlataforma(rs.getString("plataforma"));
                v.setPrecio(rs.getDouble("precio"));
                v.setDisponible(rs.getBoolean("disponible"));
                v.setGenero(rs.getString("genero"));

                logger.info("Busqueda de videojuego realizada");

                return v;
            }

        } catch (Exception e) {

            logger.error("Error al buscar videojuego", e);

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (Exception e) {

                logger.error("Error al cerrar recursos", e);
            }
        }

        return null;
    }

    public Videojuego actualizar(Videojuego videojuego) {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = Conexion.conectar();

            String sql = """
                    UPDATE videojuegos
                    SET nombre=?,
                        plataforma=?,
                        precio=?,
                        disponible=?,
                        genero=?
                    WHERE codigo=?
                    """;

            ps = con.prepareStatement(sql);

            ps.setString(1, videojuego.getNombre());
            ps.setString(2, videojuego.getPlataforma());
            ps.setDouble(3, videojuego.getPrecio());
            ps.setBoolean(4, videojuego.isDisponible());
            ps.setString(5, videojuego.getGenero());
            ps.setString(6, videojuego.getCodigo());

            ps.executeUpdate();

            logger.info("Videojuego actualizado");

            return videojuego;

        } catch (Exception e) {

            logger.error("Error al actualizar", e);

        } finally {

            try {

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (Exception e) {

                logger.error("Error al cerrar recursos", e);
            }
        }

        return null;
    }

    public boolean eliminar(String codigo) {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = Conexion.conectar();

            String sql =
                    "DELETE FROM videojuegos WHERE codigo=?";

            ps = con.prepareStatement(sql);

            ps.setString(1, codigo);

            int filas = ps.executeUpdate();

            logger.info("Videojuego eliminado");

            return filas > 0;

        } catch (Exception e) {

            logger.error("Error al eliminar", e);

        } finally {

            try {

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (Exception e) {

                logger.error("Error al cerrar recursos", e);
            }
        }

        return false;
    }
}