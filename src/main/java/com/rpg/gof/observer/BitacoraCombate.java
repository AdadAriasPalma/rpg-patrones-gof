package com.rpg.gof.observer;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Observador concreto que imprime en consola y almacena en memoria la bitácora
 * detallada de cada combate por turnos.
 */
public class BitacoraCombate implements ObservadorCombate {

    private final List<String> historial = new ArrayList<>();
    private int golpesCriticos;
    private int totalDanoInfligido;
    private int totalHpRegenerado;

    private void registrar(String linea) {
        historial.add(linea);
        System.out.println(linea);
    }

    /**
     * Retorna una vista inmutable de todas las líneas registradas en la bitácora.
     *
     * @return lista de eventos registrados
     */
    public List<String> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    @Override
    public void onInicioCombate(Personaje heroe, Personaje enemigo) {
        this.golpesCriticos = 0;
        this.totalDanoInfligido = 0;
        this.totalHpRegenerado = 0;

        registrar("================================================================================");
        registrar("                         INICIO DEL COMBATE POR TURNOS                          ");
        registrar("================================================================================");
        registrar(formatearFicha("HÉROE  ", heroe));
        registrar(formatearFicha("ENEMIGO", enemigo));
        registrar("--------------------------------------------------------------------------------");
        String primero = heroe.getVelocidad() >= enemigo.getVelocidad() ? heroe.getNombre() : enemigo.getNombre();
        registrar(String.format("Orden de iniciativa por velocidad: %s ataca primero.", primero));
        registrar("--------------------------------------------------------------------------------");
    }

    @Override
    public void onTurnoEjecutado(int numeroTurno, Personaje atacante, Personaje defensor, ResultadoAtaque resultado) {
        totalDanoInfligido += resultado.getDano();
        String marcaCritico = resultado.isCritico() ? " [CRÍTICO]" : "";
        registrar(String.format(
                "[Turno %02d] %-16s ejecuta %-28s -> %2d de daño%s | %s HP: %d/%d",
                numeroTurno,
                atacante.getNombre(),
                "\"" + resultado.getEfecto() + "\"",
                resultado.getDano(),
                marcaCritico,
                defensor.getNombre(),
                defensor.getHp(),
                defensor.getHpMax()
        ));
    }

    @Override
    public void onGolpeCritico(Personaje atacante, Personaje defensor, ResultadoAtaque resultado) {
        golpesCriticos++;
        registrar(String.format(
                "   >>> [¡CRÍTICO!] %s aprovecha su velocidad (%d) para asestar un golpe letal sobre %s.",
                atacante.getNombre(),
                atacante.getVelocidad(),
                defensor.getNombre()
        ));
    }

    @Override
    public void onRegeneracion(Personaje personaje, int puntosRegenerados, int hpActual, int hpMax) {
        totalHpRegenerado += puntosRegenerados;
        registrar(String.format(
                "   >>> [REGENERACIÓN] %s recupera +%d HP mediante su amuleto -> HP actual: %d/%d",
                personaje.getNombre(),
                puntosRegenerados,
                hpActual,
                hpMax
        ));
    }

    @Override
    public void onFinCombate(Personaje ganador, Personaje perdedor, int totalTurnos) {
        registrar("--------------------------------------------------------------------------------");
        registrar("RESUMEN DEL COMBATE:");
        registrar(String.format("  - Turnos transcurridos : %d", totalTurnos));
        registrar(String.format("  - Daño total acumulado : %d", totalDanoInfligido));
        registrar(String.format("  - Golpes críticos      : %d", golpesCriticos));
        registrar(String.format("  - HP total regenerado  : %d", totalHpRegenerado));
        registrar(String.format("  - Estado del ganador   : %s (HP %d/%d)",
                ganador.getDescripcion(), ganador.getHp(), ganador.getHpMax()));
        registrar(String.format("  - Estado del derrotado : %s (HP %d/%d)",
                perdedor.getNombre(), perdedor.getHp(), perdedor.getHpMax()));
        registrar("********************************************************************************");
        registrar(String.format("                 ¡VICTORIA PARA %s!", ganador.getNombre().toUpperCase()));
        registrar("********************************************************************************");
    }

    private String formatearFicha(String rol, Personaje p) {
        return String.format(
                "%s | %-48s | HP:%3d/%3d | ATK:%2d | DEF:%2d | VEL:%2d | Estrategia: %s",
                rol,
                p.getDescripcion(),
                p.getHp(),
                p.getHpMax(),
                p.getAtaque(),
                p.getDefensa(),
                p.getVelocidad(),
                p.getEstrategia().getNombreEstrategia()
        );
    }
}
