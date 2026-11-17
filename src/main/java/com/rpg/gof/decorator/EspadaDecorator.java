package com.rpg.gof.decorator;

import com.rpg.gof.model.Personaje;

/**
 * Decorador concreto que equipa una espada al personaje, otorgándole +10 de ataque.
 */
public class EspadaDecorator extends PersonajeDecorator {

    private static final int BONO_ATAQUE = 10;

    public EspadaDecorator(Personaje personaje) {
        super(personaje);
    }

    @Override
    public int getAtaque() {
        return personaje.getAtaque() + BONO_ATAQUE;
    }

    @Override
    public String getDescripcion() {
        return personaje.getDescripcion() + " + Espada";
    }
}
