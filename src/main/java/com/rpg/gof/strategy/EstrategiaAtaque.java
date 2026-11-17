package com.rpg.gof.strategy;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;

/**
 * Interfaz del patrón Strategy que define el algoritmo de cálculo de daño para un ataque.
 */
public interface EstrategiaAtaque {

    /**
     * Calcula y retorna el resultado determinista del ataque del atacante sobre el objetivo.
     *
     * @param atacante combatiente que ejecuta el ataque (puede estar decorado)
     * @param objetivo combatiente que recibe el ataque (puede estar decorado)
     * @return resultado inmutable con el daño, indicador de crítico y efecto
     */
    ResultadoAtaque atacar(Personaje atacante, Personaje objetivo);

    /**
     * Retorna un nombre legible de la estrategia para fines de bitácora y diagnóstico.
     *
     * @return nombre de la estrategia
     */
    default String getNombreEstrategia() {
        return this.getClass().getSimpleName();
    }
}
