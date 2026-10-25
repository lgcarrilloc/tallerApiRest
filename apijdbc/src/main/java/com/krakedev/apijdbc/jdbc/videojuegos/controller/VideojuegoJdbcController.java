package com.krakedev.apijdbc.jdbc.videojuegos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.krakedev.apijdbc.entidades.Videojuego;
import com.krakedev.apijdbc.jdbc.videojuegos.services.ServicioVideojuegoJdbc;

@RestController
@RequestMapping("/jdbc/videojuegos")
public class VideojuegoJdbcController {

    @Autowired
    private ServicioVideojuegoJdbc servicio;

    @PostMapping
    public Videojuego crear(@RequestBody Videojuego videojuego) {
        return servicio.crear(videojuego);
    }

    @GetMapping
    public List<Videojuego> listar() {
        return servicio.listar();
    }

    @GetMapping("/{codigo}")
    public Videojuego buscarPorCodigo(
            @PathVariable String codigo) {

        return servicio.buscarPorCodigo(codigo);
    }

    @PutMapping("/{codigo}")
    public Videojuego actualizar(
            @PathVariable String codigo,
            @RequestBody Videojuego videojuego) {

        videojuego.setCodigo(codigo);

        return servicio.actualizar(videojuego);
    }

    @DeleteMapping("/{codigo}")
    public boolean eliminar(
            @PathVariable String codigo) {

        return servicio.eliminar(codigo);
    }
}