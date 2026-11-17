package com.rpg.gof.factory;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.PersonajeBase;
import com.rpg.gof.strategy.AtaqueFlecha;

/**
 * Fábrica concreta para crear personajes de clase Arquero.
 */
public class CreadorArquero extends CreadorPersonaje {

    @Override
    public Personaje crearPersonaje(String nombre) {
        validarNombre(nombre);
        return new PersonajeBase(nombre.trim(), 115, 23, 11, 18, new AtaqueFlecha());
    }
}
