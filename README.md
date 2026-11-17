# Videojuego RPG por Turnos en Consola — Patrones de Diseño GoF

Proyecto desarrollado en **Java 17 (Maven)** compatible con **Apache NetBeans IDE 23**, que implementa un motor de combate RPG por turnos aplicando estrictamente cuatro patrones de diseño del catálogo **Gang of Four (GoF)**: **Factory Method**, **Strategy**, **Decorator** y **Observer**.

---

## Arquitectura y Patrones Implementados

| Paquete | Patrón / Rol | Clases e Interfaces |
| :--- | :--- | :--- |
| `com.rpg.gof.model` | Modelo de Dominio | `Personaje`, `PersonajeBase`, `ResultadoAtaque` |
| `com.rpg.gof.strategy` | **Strategy** | `EstrategiaAtaque`, `AtaqueEspada`, `AtaqueHechizo`, `AtaqueFlecha`, `AtaquePunalada`, `AtaqueGarrote`, `AtaqueAlientoFuego` |
| `com.rpg.gof.factory` | **Factory Method** | `CreadorPersonaje`, `CreadorGuerrero`, `CreadorMago`, `CreadorArquero`, `CreadorPicaro`, `CreadorEnemigo`, `CreadorGoblin`, `CreadorOrco`, `CreadorDragon` |
| `com.rpg.gof.decorator` | **Decorator** | `PersonajeDecorator`, `EspadaDecorator`, `ArmaduraDecorator`, `VenenoDecorator`, `AmuletoRegeneracionDecorator`, `BendicionDecorator` |
| `com.rpg.gof.observer` | **Observer** | `ObservadorCombate`, `BitacoraCombate` |
| `com.rpg.gof.engine` | Sujeto / Motor | `MotorCombate` |
| `com.rpg.gof` | Punto de Entrada | `Main` |

### Resumen de Diseño
1. **Factory Method (`com.rpg.gof.factory`)**: Encapsula la creación de héroes (`CreadorGuerrero`, `CreadorMago`, `CreadorArquero`, `CreadorPicaro`) y enemigos (`CreadorGoblin`, `CreadorOrco`, `CreadorDragon`) asignando estadísticas base y su estrategia inicial de combate.
2. **Strategy (`com.rpg.gof.strategy`)**: Desacopla las fórmulas deterministas de cálculo de daño (`AtaqueEspada`, `AtaqueHechizo`, `AtaqueFlecha`, `AtaquePunalada`, `AtaqueGarrote`, `AtaqueAlientoFuego`), permitiendo intercambiar dinámicamente el comportamiento ofensivo sin modificar `PersonajeBase`.
3. **Decorator (`com.rpg.gof.decorator`)**: Permite equipar y apilar modificadores en tiempo de ejecución (`EspadaDecorator`, `ArmaduraDecorator`, `VenenoDecorator`, `AmuletoRegeneracionDecorator`, `BendicionDecorator`) en cualquier orden sin explosión combinatoria de subclases.
4. **Observer (`com.rpg.gof.observer` y `com.rpg.gof.engine`)**: `MotorCombate` actúa como *Subject* notificando eventos (`onInicioCombate`, `onTurnoEjecutado`, `onGolpeCritico`, `onRegeneracion`, `onFinCombate`) a `ObservadorCombate`, manteniendo desacoplada la lógica del juego respecto a `BitacoraCombate`.

---

## Estructura del Proyecto

```text
AnalisisYDiseno/
├── pom.xml
├── README.md
├── EJECUCION.txt
├── Reporte_Patrones_GoF_RPG.tex
├── diagramas/
│   ├── diagrama_clases.puml
│   └── diagrama_secuencia.puml
└── src/
    └── main/
        └── java/
            └── com/
                └── rpg/
                    └── gof/
                        ├── Main.java
                        ├── model/
                        ├── strategy/
                        ├── factory/
                        ├── decorator/
                        ├── observer/
                        └── engine/
```

---

## Ejecución del Proyecto

### Opción 1: Desde Apache NetBeans IDE 23
1. Abrir Apache NetBeans IDE 23.
2. Ir a **File > Open Project...** (`Ctrl + Shift + O`).
3. Seleccionar la carpeta raíz del proyecto (`AnalisisYDiseno`, donde reside `pom.xml`) y hacer clic en **Open Project**.
4. NetBeans reconocerá automáticamente el proyecto Maven con `exec-maven-plugin` configurado hacia `com.rpg.gof.Main`.
5. Presionar **F6** (o clic derecho en el proyecto > **Run**) para compilar y ejecutar las 3 corridas en la ventana *Output*.

### Opción 2: Desde Terminal con Maven
Ejecutar en la raíz del proyecto:

```bash
mvn clean compile exec:java
```

Si Maven está integrado en la instalación local de NetBeans 23 en Windows (sin estar en el `PATH` global), puede ejecutarse mediante:

```powershell
& "C:\Program Files\NetBeans-23\netbeans\java\maven\bin\mvn.cmd" clean compile exec:java
```

---

## Escenarios Demostrados en `Main.java`

1. **Corrida 1**: `Aria` (Mago) + `Espada` + `Veneno` + `Bendición` vs `Goblin salvaje`
2. **Corrida 2**: `Kael` (Guerrero) + `Espada` + `Armadura` + `Amuleto de Regeneración` vs `Orco brutal`
3. **Corrida 3**: `Sombra` (Pícaro) + `Espada` + `Veneno` + `Bendición` vs `Dragón ancestral`

La salida real completa de la ejecución se encuentra registrada en [`EJECUCION.txt`](EJECUCION.txt).
