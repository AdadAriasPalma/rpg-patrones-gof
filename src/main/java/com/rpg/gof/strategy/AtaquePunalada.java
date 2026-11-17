package com.rpg.gof.strategy;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;

/**
 * Estrategia sigilosa que asesta un golpe crítico (x1.5) cuando el atacante alcanza velocidad >= 20.
 */
public class AtaquePunalada implements EstrategiaAtaque {

    private static final int UMBRAL_VELOCIDAD_CRITICA = 20;

    @Override
    public ResultadoAtaque atacar(Personaje atacante, Personaje objetivo) {
        int danoBase = Math.max(1, atacante.getAtaque() - objetivo.getDefensa());
        boolean critico = atacante.getVelocidad() >= UMBRAL_VELOCIDAD_CRITICA;
        int danoFinal = critico ? Math.max(1, (int) (danoBase * 1.5)) : danoBase;
        return new ResultadoAtaque(danoFinal, critico, "puñalada furtiva");
    }
}
