package com.rpg.gof.factory;

import com.rpg.gof.model.Personaje;

/**
 * Creador abstracto del patrón Factory Method para instanciar héroes jugables.
 */
public abstract class CreadorPersonaje {

    /**
     * Método de fábrica que delega en las subclases la creación del personaje concreto.
     *
     * @param nombre nombre asignado al héroe
     * @return instancia configurada de {@link Personaje}
     */
    public abstract Personaje crearPersonaje(String nombre);

    /**
     * Hook de validación invocado por las fábricas concretas antes de instanciar el personaje.
     *
     * @param nombre nombre a validar
     */
    protected void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del héroe no puede ser nulo o vacío.");
        }
    }
}
