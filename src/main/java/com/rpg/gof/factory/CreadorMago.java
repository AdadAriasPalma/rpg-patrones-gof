package com.rpg.gof.factory;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.PersonajeBase;
import com.rpg.gof.strategy.AtaqueHechizo;

/**
 * Fábrica concreta para crear personajes de clase Mago.
 */
public class CreadorMago extends CreadorPersonaje {

    @Override
    public Personaje crearPersonaje(String nombre) {
        validarNombre(nombre);
        return new PersonajeBase(nombre.trim(), 100, 20, 10, 15, new AtaqueHechizo());
    }
}
