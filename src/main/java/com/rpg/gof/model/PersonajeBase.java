package com.rpg.gof.model;

import com.rpg.gof.strategy.EstrategiaAtaque;
import java.util.Objects;

/**
 * Implementación concreta del componente base en el patrón Decorator.
 * Representa a un héroe o enemigo con sus atributos fundamentales y su estrategia de ataque.
 */
public class PersonajeBase implements Personaje {

    private final String nombre;
    private int hp;
    private final int hpMax;
    private final int ataque;
    private final int defensa;
    private final int velocidad;
    private EstrategiaAtaque estrategia;

    /**
     * Inicializa un personaje base con puntos de vida al máximo.
     *
     * @param nombre     identificador del combatiente
     * @param hpMax      puntos de vida máximos
     * @param ataque     estadística base de ataque
     * @param defensa    estadística base de defensa
     * @param velocidad  estadística base de velocidad
     * @param estrategia estrategia de ataque inicial
     */
    public PersonajeBase(String nombre, int hpMax, int ataque, int defensa, int velocidad, EstrategiaAtaque estrategia) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del personaje no puede ser nulo ni vacío.");
        }
        if (hpMax <= 0) {
            throw new IllegalArgumentException("El HP máximo debe ser mayor a 0.");
        }
        this.nombre = nombre.trim();
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.ataque = Math.max(0, ataque);
        this.defensa = Math.max(0, defensa);
        this.velocidad = Math.max(0, velocidad);
        this.estrategia = Objects.requireNonNull(estrategia, "La estrategia de ataque es obligatoria.");
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getAtaque() {
        return ataque;
    }

    @Override
    public int getDefensa() {
        return defensa;
    }

    @Override
    public int getVelocidad() {
        return velocidad;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getHpMax() {
        return hpMax;
    }

    @Override
    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hp, hpMax));
    }

    @Override
    public EstrategiaAtaque getEstrategia() {
        return estrategia;
    }

    @Override
    public void setEstrategia(EstrategiaAtaque e) {
        this.estrategia = Objects.requireNonNull(e, "La estrategia de ataque no puede ser nula.");
    }

    @Override
    public ResultadoAtaque atacar(Personaje objetivo) {
        Objects.requireNonNull(objetivo, "El objetivo del ataque no puede ser nulo.");
        return estrategia.atacar(this, objetivo);
    }

    @Override
    public String getDescripcion() {
        return nombre + " (base)";
    }
}
