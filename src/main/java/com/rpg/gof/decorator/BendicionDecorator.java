package com.rpg.gof.decorator;

import com.rpg.gof.model.Personaje;

/**
 * Decorador concreto que bendice al personaje incrementando en +15% sus atributos ofensivos, defensivos y de agilidad.
 */
public class BendicionDecorator extends PersonajeDecorator {

    private static final double MULTIPLICADOR_BENDICION = 1.15;

    public BendicionDecorator(Personaje personaje) {
        super(personaje);
    }

    @Override
    public int getAtaque() {
        return (int) (personaje.getAtaque() * MULTIPLICADOR_BENDICION);
    }

    @Override
    public int getDefensa() {
        return (int) Math.round(personaje.getDefensa() * MULTIPLICADOR_BENDICION);
    }

    @Override
    public int getVelocidad() {
        return (int) (personaje.getVelocidad() * MULTIPLICADOR_BENDICION);
    }

    @Override
    public String getDescripcion() {
        return personaje.getDescripcion() + " + Bendición";
    }
}
