package com.rpg.gof.observer;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;

/**
 * Interfaz Observador del patrón Observer para desacoplar el motor de combate
 * de la presentación o registro de eventos (consola, bitácora, UI o métricas).
 */
public interface ObservadorCombate {

    void onInicioCombate(Personaje heroe, Personaje enemigo);

    void onTurnoEjecutado(int numeroTurno, Personaje atacante, Personaje defensor, ResultadoAtaque resultado);

    void onGolpeCritico(Personaje atacante, Personaje defensor, ResultadoAtaque resultado);

    void onRegeneracion(Personaje personaje, int puntosRegenerados, int hpActual, int hpMax);

    void onFinCombate(Personaje ganador, Personaje perdedor, int totalTurnos);
}
