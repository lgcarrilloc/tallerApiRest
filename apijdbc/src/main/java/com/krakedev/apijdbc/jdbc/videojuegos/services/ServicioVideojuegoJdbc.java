package com.krakedev.apijdbc.jdbc.videojuegos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.apijdbc.entidades.Videojuego;
import com.krakedev.apijdbc.jdbc.videojuegos.VideojuegoJdbc;

@Service
public class ServicioVideojuegoJdbc {

    private VideojuegoJdbc videojuegoJdbc;

    public ServicioVideojuegoJdbc() {
        videojuegoJdbc = new VideojuegoJdbc();
    }

    public Videojuego crear(Videojuego videojuego) {
        return videojuegoJdbc.insertar(videojuego);
    }

    public List<Videojuego> listar() {
        return videojuegoJdbc.listar();
    }

    public Videojuego buscarPorCodigo(String codigo) {
        return videojuegoJdbc.buscar(codigo);
    }

    public Videojuego actualizar(Videojuego videojuego) {
        return videojuegoJdbc.actualizar(videojuego);
    }

    public boolean eliminar(String codigo) {
        return videojuegoJdbc.eliminar(codigo);
    }
}