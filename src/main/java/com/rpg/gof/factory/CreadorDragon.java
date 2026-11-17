package com.rpg.gof.factory;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.PersonajeBase;
import com.rpg.gof.strategy.AtaqueAlientoFuego;

/**
 * Fábrica concreta para instanciar al enemigo Dragón ancestral.
 */
public class CreadorDragon extends CreadorEnemigo {

    @Override
    public Personaje crearEnemigo() {
        return new PersonajeBase("Dragón ancestral", 180, 34, 18, 16, new AtaqueAlientoFuego());
    }
}
