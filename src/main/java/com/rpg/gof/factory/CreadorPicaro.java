package com.rpg.gof.factory;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.PersonajeBase;
import com.rpg.gof.strategy.AtaquePunalada;

/**
 * Fábrica concreta para crear personajes de clase Pícaro.
 */
public class CreadorPicaro extends CreadorPersonaje {

    @Override
    public Personaje crearPersonaje(String nombre) {
        validarNombre(nombre);
        return new PersonajeBase(nombre.trim(), 105, 26, 12, 22, new AtaquePunalada());
    }
}
