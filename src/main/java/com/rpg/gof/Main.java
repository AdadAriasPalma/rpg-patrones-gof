package com.rpg.gof;

import com.rpg.gof.decorator.AmuletoRegeneracionDecorator;
import com.rpg.gof.decorator.ArmaduraDecorator;
import com.rpg.gof.decorator.BendicionDecorator;
import com.rpg.gof.decorator.EspadaDecorator;
import com.rpg.gof.decorator.VenenoDecorator;
import com.rpg.gof.engine.MotorCombate;
import com.rpg.gof.factory.CreadorDragon;
import com.rpg.gof.factory.CreadorEnemigo;
import com.rpg.gof.factory.CreadorGoblin;
import com.rpg.gof.factory.CreadorGuerrero;
import com.rpg.gof.factory.CreadorMago;
import com.rpg.gof.factory.CreadorOrco;
import com.rpg.gof.factory.CreadorPersonaje;
import com.rpg.gof.factory.CreadorPicaro;
import com.rpg.gof.model.Personaje;
import com.rpg.gof.observer.BitacoraCombate;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Punto de entrada del videojuego RPG por turnos que demuestra la integración de los cuatro
 * patrones GoF (Factory Method, Strategy, Decorator y Observer) mediante tres corridas completas.
 */
public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        MotorCombate motor = new MotorCombate();
        BitacoraCombate bitacora = new BitacoraCombate();
        motor.agregarObservador(bitacora);

        ejecutarCorrida1(motor);
        ejecutarCorrida2(motor);
        ejecutarCorrida3(motor);
    }

    private static void ejecutarCorrida1(MotorCombate motor) {
        System.out.println("\n################################################################################");
        System.out.println("CORRIDA 1: Aria (Mago) + Espada + Veneno + Bendición vs Goblin salvaje");
        System.out.println("################################################################################");

        CreadorPersonaje fabricaHeroe = new CreadorMago();
        CreadorEnemigo fabricaEnemigo = new CreadorGoblin();

        Personaje aria = fabricaHeroe.crearPersonaje("Aria");
        aria = new BendicionDecorator(
                new VenenoDecorator(
                        new EspadaDecorator(aria)
                )
        );
        Personaje goblin = fabricaEnemigo.crearEnemigo();

        motor.iniciarCombate(aria, goblin);
    }

    private static void ejecutarCorrida2(MotorCombate motor) {
        System.out.println("\n################################################################################");
        System.out.println("CORRIDA 2: Kael (Guerrero) + Espada + Armadura + Amuleto de Regeneración vs Orco brutal");
        System.out.println("################################################################################");

        CreadorPersonaje fabricaHeroe = new CreadorGuerrero();
        CreadorEnemigo fabricaEnemigo = new CreadorOrco();

        Personaje kael = fabricaHeroe.crearPersonaje("Kael");
        kael = new AmuletoRegeneracionDecorator(
                new ArmaduraDecorator(
                        new EspadaDecorator(kael)
                )
        );
        Personaje orco = fabricaEnemigo.crearEnemigo();

        motor.iniciarCombate(kael, orco);
    }

    private static void ejecutarCorrida3(MotorCombate motor) {
        System.out.println("\n################################################################################");
        System.out.println("CORRIDA 3: Sombra (Pícaro) + Espada + Veneno + Bendición vs Dragón ancestral");
        System.out.println("################################################################################");

        CreadorPersonaje fabricaHeroe = new CreadorPicaro();
        CreadorEnemigo fabricaEnemigo = new CreadorDragon();

        Personaje sombra = fabricaHeroe.crearPersonaje("Sombra");
        sombra = new BendicionDecorator(
                new VenenoDecorator(
                        new EspadaDecorator(sombra)
                )
        );
        Personaje dragon = fabricaEnemigo.crearEnemigo();

        motor.iniciarCombate(sombra, dragon);
    }
}
