package com.rpg.gof.decorator;

import com.rpg.gof.model.Personaje;

/**
 * Decorador concreto que equipa un amuleto curativo (+3 defensa y regeneración de +2 HP por turno).
 */
public class AmuletoRegeneracionDecorator extends PersonajeDecorator {

    private static final int BONO_DEFENSA = 3;
    private static final int CURACION_POR_TURNO = 2;

    public AmuletoRegeneracionDecorator(Personaje personaje) {
        super(personaje);
    }

    @Override
    public int getDefensa() {
        return personaje.getDefensa() + BONO_DEFENSA;
    }

    @Override
    public String getDescripcion() {
        return personaje.getDescripcion() + " + Amuleto de Regeneración";
    }

    /**
     * Aplica la regeneración de +2 HP sin exceder {@code hpMax}.
     *
     * @return puntos de vida efectivamente recuperados por este amuleto
     */
    public int regenerar() {
        if (!estaVivo() || getHp() >= getHpMax()) {
            return 0;
        }
        int hpAnterior = getHp();
        setHp(hpAnterior + CURACION_POR_TURNO);
        return getHp() - hpAnterior;
    }

    @Override
    public int regenerarPorTurno() {
        int curadoAnterior = super.regenerarPorTurno();
        return curadoAnterior + regenerar();
    }
}
