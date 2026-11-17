package com.rpg.gof.decorator;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;

/**
 * Decorador concreto que impregna el arma con veneno (+5 ataque, +5 daño adicional y efecto de veneno).
 */
public class VenenoDecorator extends PersonajeDecorator {

    private static final int BONO_ATAQUE = 5;
    private static final int DANO_VENENO = 5;

    public VenenoDecorator(Personaje personaje) {
        super(personaje);
    }

    @Override
    public int getAtaque() {
        return personaje.getAtaque() + BONO_ATAQUE;
    }

    @Override
    public String getDescripcion() {
        return personaje.getDescripcion() + " + Veneno";
    }

    @Override
    public ResultadoAtaque atacar(Personaje objetivo) {
        ResultadoAtaque resultadoBase = super.atacar(objetivo);
        return new ResultadoAtaque(
                resultadoBase.getDano() + DANO_VENENO,
                resultadoBase.isCritico(),
                resultadoBase.getEfecto() + " + veneno"
        );
    }
}
