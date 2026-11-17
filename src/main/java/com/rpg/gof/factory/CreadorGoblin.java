package com.rpg.gof.factory;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.PersonajeBase;
import com.rpg.gof.strategy.AtaquePunalada;

/**
 * Fábrica concreta para instanciar al enemigo Goblin salvaje.
 */
public class CreadorGoblin extends CreadorEnemigo {

    @Override
    public Personaje crearEnemigo() {
        return new PersonajeBase("Goblin salvaje", 60, 13, 2, 14, new AtaquePunalada());
    }
}
