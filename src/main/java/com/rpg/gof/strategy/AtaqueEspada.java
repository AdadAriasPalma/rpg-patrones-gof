package com.rpg.gof.strategy;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;

/**
 * Estrategia de ataque físico directo con espada.
 */
public class AtaqueEspada implements EstrategiaAtaque {

    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int dano = Math.max(1, atacante.getAtaque() - objetivo.getDefensa());
        return new ResultadoAtaque(dano, false, "corte de espada");
    }
}
