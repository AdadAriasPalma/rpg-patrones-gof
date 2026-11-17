package com.rpg.gof.engine;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;
import com.rpg.gof.observer.ObservadorCombate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Sujeto (Subject) del patrón Observer encargado de orquestar el ciclo de combate
 * por turnos entre un héroe y un enemigo sin acoplarse a la salida en consola.
 */
public class MotorCombate {

    private final List<ObservadorCombate> observadores = new ArrayList<>();

    public void agregarObservador(ObservadorCombate observador) {
        Objects.requireNonNull(observador, "El observador no puede ser nulo.");
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void eliminarObservador(ObservadorCombate observador) {
        observadores.remove(observador);
    }

    /**
     * Ejecuta un combate completo por turnos hasta que uno de los dos combatientes quede en 0 HP.
     *
     * @param heroe   combatiente aliado
     * @param enemigo combatiente adversario
     * @return el personaje vencedor del encuentro
     */
    public Personaje iniciarCombate(Personaje heroe, Personaje enemigo) {
        Objects.requireNonNull(heroe, "El héroe no puede ser nulo.");
        Objects.requireNonNull(enemigo, "El enemigo no puede ser nulo.");

        notificarInicioCombate(heroe, enemigo);

        Personaje primero = heroe.getVelocidad() >= enemigo.getVelocidad() ? heroe : enemigo;
        Personaje segundo = (primero == heroe) ? enemigo : heroe;

        int numeroTurno = 0;
        while (heroe.estaVivo() && enemigo.estaVivo()) {
            numeroTurno++;
            ejecutarAccion(numeroTurno, primero, segundo);
            if (segundo.estaVivo()) {
                ejecutarAccion(numeroTurno, segundo, primero);
            }
        }

        Personaje ganador = heroe.estaVivo() ? heroe : enemigo;
        Personaje perdedor = (ganador == heroe) ? enemigo : heroe;
        notificarFinCombate(ganador, perdedor, numeroTurno);
        return ganador;
    }

    private void ejecutarAccion(int numeroTurno, Personaje atacante, Personaje defensor) {
        ResultadoAtaque resultado = atacante.atacar(defensor);
        defensor.setHp(defensor.getHp() - resultado.getDano());

        if (resultado.isCritico()) {
            notificarGolpeCritico(atacante, defensor, resultado);
        }
        notificarTurnoEjecutado(numeroTurno, atacante, defensor, resultado);

        if (atacante.estaVivo()) {
            int puntosRegenerados = atacante.regenerarPorTurno();
            if (puntosRegenerados > 0) {
                notificarRegeneracion(atacante, puntosRegenerados, atacante.getHp(), atacante.getHpMax());
            }
        }
    }

    private void notificarInicioCombate(Personaje heroe, Personaje enemigo) {
        for (ObservadorCombate observador : observadores) {
            observador.onInicioCombate(heroe, enemigo);
        }
    }

    private void notificarTurnoEjecutado(int numeroTurno, Personaje atacante, Personaje defensor, ResultadoAtaque resultado) {
        for (ObservadorCombate observador : observadores) {
            observador.onTurnoEjecutado(numeroTurno, atacante, defensor, resultado);
        }
    }

    private void notificarGolpeCritico(Personaje atacante, Personaje defensor, ResultadoAtaque resultado) {
        for (ObservadorCombate observador : observadores) {
            observador.onGolpeCritico(atacante, defensor, resultado);
        }
    }

    private void notificarRegeneracion(Personaje personaje, int puntosRegenerados, int hpActual, int hpMax) {
        for (ObservadorCombate observador : observadores) {
            observador.onRegeneracion(personaje, puntosRegenerados, hpActual, hpMax);
        }
    }

    private void notificarFinCombate(Personaje ganador, Personaje perdedor, int totalTurnos) {
        for (ObservadorCombate observador : observadores) {
            observador.onFinCombate(ganador, perdedor, totalTurnos);
        }
    }
}
