package com.rpg.gof.model;

import com.rpg.gof.strategy.EstrategiaAtaque;

/**
 * Contrato principal para cualquier combatiente (base o decorado) dentro del sistema RPG.
 */
public interface Personaje {

    String getNombre();

    int getAtaque();

    int getDefensa();

    int getVelocidad();

    int getHp();

    int getHpMax();

    void setHp(int hp);

    EstrategiaAtaque getEstrategia();

    void setEstrategia(EstrategiaAtaque e);

    ResultadoAtaque atacar(Personaje objetivo);

    String getDescripcion();

    /**
     * Ejecuta la regeneración pasiva al final del turno si el personaje cuenta con algún efecto curativo.
     *
     * @return puntos de vida efectivamente regenerados en este turno
     */
    default int regenerarPorTurno() {
        return 0;
    }

    /**
     * Indica si el combatiente permanece con puntos de vida positivos.
     *
     * @return {@code true} si HP es mayor que 0
     */
    default boolean estaVivo() {
        return getHp() > 0;
    }
}
