package com.rpg.gof.factory;

import com.rpg.gof.model.Personaje;

/**
 * Creador abstracto del patrón Factory Method para instanciar enemigos.
 */
public abstract class CreadorEnemigo {

    /**
     * Crea una instancia concreta de enemigo lista para entrar en combate.
     *
     * @return enemigo configurado
     */
    public abstract Personaje crearEnemigo();
}
