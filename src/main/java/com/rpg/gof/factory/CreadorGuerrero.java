package com.rpg.gof.factory;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.PersonajeBase;
import com.rpg.gof.strategy.AtaqueEspada;

/**
 * Fábrica concreta para crear personajes de clase Guerrero.
 */
public class CreadorGuerrero extends CreadorPersonaje {

    @Override
    public Personaje crearPersonaje(String nombre) {
        validarNombre(nombre);
        return new PersonajeBase(nombre.trim(), 140, 25, 15, 10, new AtaqueEspada());
    }
}
