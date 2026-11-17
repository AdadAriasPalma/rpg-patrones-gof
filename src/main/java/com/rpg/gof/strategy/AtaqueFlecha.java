package com.rpg.gof.strategy;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;

/**
 * Estrategia de ataque a distancia que perfora la mitad de la defensa del objetivo.
 */
public class AtaqueFlecha implements EstrategiaAtaque {

    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int dano = Math.max(1, atacante.getAtaque() - (objetivo.getDefensa() / 2));
        return new ResultadoAtaque(dano, false, "disparo perforante");
    }
}
