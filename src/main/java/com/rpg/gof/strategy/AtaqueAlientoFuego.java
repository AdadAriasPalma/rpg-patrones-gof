package com.rpg.gof.strategy;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;

/**
 * Estrategia elemental devastadora que ignora la mitad de la defensa del objetivo.
 */
public class AtaqueAlientoFuego implements EstrategiaAtaque {

    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int dano = Math.max(1, atacante.getAtaque() - (objetivo.getDefensa() / 2));
        return new ResultadoAtaque(dano, false, "aliento de fuego");
    }
}
