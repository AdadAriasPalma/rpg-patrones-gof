package com.rpg.gof.strategy;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;

/**
 * Estrategia de ataque mágico que ignora dos tercios de la defensa del objetivo.
 */
public class AtaqueHechizo implements EstrategiaAtaque {

    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int dano = Math.max(1, atacante.getAtaque() - (objetivo.getDefensa() / 3));
        return new ResultadoAtaque(dano, false, "hechizo arcano");
    }
}
