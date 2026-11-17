package com.rpg.gof.model;

/**
 * Representa el resultado inmutable de ejecutar un ataque durante un turno de combate.
 */
public final class ResultadoAtaque {

    private final int dano;
    private final boolean critico;
    private final String efecto;

    /**
     * Construye un resultado de ataque con sus atributos finales.
     *
     * @param dano    cantidad de daño infligido al objetivo
     * @param critico {@code true} si el impacto fue golpe crítico
     * @param efecto  descripción del efecto o técnica aplicada
     */
    public ResultadoAtaque(int dano, boolean critico, String efecto) {
        this.dano = Math.max(0, dano);
        this.critico = critico;
        this.efecto = efecto != null ? efecto : "ataque básico";
    }

    public int getDano() {
        return dano;
    }

    public boolean isCritico() {
        return critico;
    }

    public String getEfecto() {
        return efecto;
    }

    @Override
    public String toString() {
        return String.format("ResultadoAtaque{dano=%d, critico=%s, efecto='%s'}", dano, critico, efecto);
    }
}
