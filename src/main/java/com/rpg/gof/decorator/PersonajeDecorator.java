package com.rpg.gof.decorator;

import com.rpg.gof.model.Personaje;
import com.rpg.gof.model.ResultadoAtaque;
import com.rpg.gof.strategy.EstrategiaAtaque;
import java.util.Objects;

/**
 * Decorador abstracto que envuelve a un {@link Personaje} para añadirle capacidades
 * o modificar dinámicamente sus estadísticas sin alterar su clase base.
 */
public abstract class PersonajeDecorator implements Personaje {

    /**
     * Mantiene la referencia al decorador más externo durante la cadena de llamadas de ataque,
     * garantizando que la estrategia lea los atributos totales ya acumulados por todos los decoradores.
     */
    private static final ThreadLocal<Personaje> ATACANTE_DECORADO = new ThreadLocal<>();

    protected final Personaje personaje;

    /**
     * Construye un decorador envolviendo a otro personaje (base o ya decorado).
     *
     * @param personaje componente a envolver
     */
    protected PersonajeDecorator(Personaje personaje) {
        this.personaje = Objects.requireNonNull(personaje, "El personaje decorado no puede ser nulo.");
    }

    @Override
    public String getNombre() {
        return personaje.getNombre();
    }

    @Override
    public int getAtaque() {
        return personaje.getAtaque();
    }

    @Override
    public int getDefensa() {
        return personaje.getDefensa();
    }

    @Override
    public int getVelocidad() {
        return personaje.getVelocidad();
    }

    @Override
    public int getHp() {
        return personaje.getHp();
    }

    @Override
    public int getHpMax() {
        return personaje.getHpMax();
    }

    @Override
    public void setHp(int hp) {
        personaje.setHp(hp);
    }

    @Override
    public EstrategiaAtaque getEstrategia() {
        return personaje.getEstrategia();
    }

    @Override
    public void setEstrategia(EstrategiaAtaque e) {
        personaje.setEstrategia(e);
    }

    @Override
    public ResultadoAtaque atacar(Personaje objetivo) {
        Objects.requireNonNull(objetivo, "El objetivo del ataque no puede ser nulo.");
        boolean esExterior = (ATACANTE_DECORADO.get() == null);
        if (esExterior) {
            ATACANTE_DECORADO.set(this);
        }
        try {
            if (personaje instanceof PersonajeDecorator) {
                return personaje.atacar(objetivo);
            }
            return personaje.getEstrategia().atacar(ATACANTE_DECORADO.get(), objetivo);
        } finally {
            if (esExterior) {
                ATACANTE_DECORADO.remove();
            }
        }
    }

    @Override
    public String getDescripcion() {
        return personaje.getDescripcion();
    }

    @Override
    public int regenerarPorTurno() {
        return personaje.regenerarPorTurno();
    }
}
