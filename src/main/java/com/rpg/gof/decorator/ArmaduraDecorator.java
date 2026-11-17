package com.rpg.gof.decorator;

import com.rpg.gof.model.Personaje;

/**
 * Decorador concreto que equipa una armadura al personaje, otorgándole +8 de defensa.
 */
public class ArmaduraDecorator extends PersonajeDecorator {

    private static final int BONO_DEFENSA = 8;

    public ArmaduraDecorator(Personaje personaje) {
        super(personaje);
    }

    @Override
    public int getDefensa() {
        return personaje.getDefensa() + BONO_DEFENSA;
    }

    @Override
    public String getDescripcion() {
        return personaje.getDescripcion() + " + Armadura";
    }
}
