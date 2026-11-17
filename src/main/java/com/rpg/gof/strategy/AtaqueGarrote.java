package com.rpg.gof.strategy;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;

/**
 * Estrategia contundente propia de enemigos pesados como el orco.
 */
public class AtaqueGarrote implements EstrategiaAtaque {

    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int dano = Math.max(1, (int) (atacante.getAtaque() * 1.15) - objetivo.getDefensa());
        return new ResultadoAtaque(dano, false, "golpe de garrote");
    }
}
