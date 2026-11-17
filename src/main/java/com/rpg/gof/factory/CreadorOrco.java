package com.rpg.gof.factory;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.PersonajeBase;
import com.rpg.gof.strategy.AtaqueGarrote;

/**
 * Fábrica concreta para instanciar al enemigo Orco brutal.
 */
public class CreadorOrco extends CreadorEnemigo {

    @Override
    public Personaje crearEnemigo() {
        return new PersonajeBase("Orco brutal", 130, 28, 12, 8, new AtaqueGarrote());
    }
}
