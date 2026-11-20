# Ejercicios realizados - Tema 3 (ALED)

Ejercicios resueltos del Tema 3 de Algoritmos y Estructuras de Datos (programación concurrente: hebras, condiciones de carrera, monitores con `synchronized`, `wait()` y `notifyAll()`, interbloqueo, bloqueo vivo e inanición).

Proyecto de Eclipse. Cada ejercicio tiene una clase con su propio `main` que lo prueba.

## Concurrencia (`src/concurrencia/`)

| Ejercicio | Clase | Respuesta |
|---|---|---|
| 3.3. Temporizador con interrupciones | `Ejercicio3Temporizador` | Imprime Segundos: 1 ... Segundos: 4 (a veces también el 5, según quién gane en el segundo 5) y "Hebra interrumpida". Termina a pesar del `while (true)` porque `interrupt()` llega mientras la hebra está en `sleep()`: salta `InterruptedException` y el `catch` hace `return` |
| 3.4. Segundero sin `interrupt()` | `Ejercicio4Segundero` | La hebra se para con un indicador `volatile boolean`. `volatile` hace que la hebra vea el cambio hecho desde `main` (sin él podría seguir leyendo una copia vieja); no hace atómico un `++`. A diferencia de `interrupt()`, no corta el `sleep`: la hebra termina la vuelta en curso y se para al volver a comprobar el `while` |
| 3.5. Contador sin sincronizar | `Ejercicio5Contador` | Imprime un valor menor que 20000 y distinto en cada ejecución: `cuenta++` es leer, sumar y escribir, y las dos hebras se pisan (condición de carrera). Además `main` espera con `sleep(1000)`, que es adivinar cuánto tardan |
| 3.6. Contador sincronizado | `Ejercicio6ContadorSincronizado` | Imprime siempre 20000. Dos cambios: `synchronized` en los métodos (exclusión mutua) y `join()` en vez de `sleep()` (espera exactamente a que acaben) |
| 3.7. Tampones (productor-consumidor) | `Tampon`, `Ejercicio7Tampon` | Monitor de un solo dato: `guardar()` espera mientras está lleno y `sacar()` mientras está vacío, siempre con `while` y `wait()`. Los dos métodos acaban en `notifyAll()` porque cambian el estado; sin él, quien espera no se despierta nunca |
| 3.8. Parking | `Parking`, `SensorEntrada`, `SensorSalida`, `Supervisor`, `Ejercicio8Parking` | Monitor con capacidad dada en el constructor: `entra()` espera mientras está lleno; `sale()` libera una plaza y hace `notifyAll()` para despertar a los coches que esperan. `detectaVehiculo()` y `abrirBarrera()` se dan por programados en el enunciado: aquí son simulaciones |
| 3.9. Bloqueo vivo | `Ejercicio9BloqueoVivo` | Livelock: cada hebra coge un recurso, no consigue el otro, lo devuelve y vuelve a empezar a la vez que la otra. Están activas pero no avanzan (en un deadlock estarían dormidas). Soluciones: ir por turnos, coger los recursos en el mismo orden o esperar un tiempo aleatorio antes de reintentar |

## Monitores de exámenes de otros años (`src/concurrencia/examen*/`)

Cada paquete tiene el monitor y un `Main` que lanza varias hebras para probarlo. Las clases que el enunciado da por hechas (`Muestra`, `Estacion`...) son simulaciones.

| Ejercicio | Paquete | Qué hace el monitor |
|---|---|---|
| Parcial 2 2014: laboratorio con N puestos | `examen2014laboratorio` | `entra()` no espera, devuelve si hay puesto; el analista espera muestras y el investigador su resultado; con M muestras pendientes, el investigador espera para entregar |
| Parcial 2 2015: instrumentos y técnico de mantenimiento | `examen2015laboratorio` | `toma()` espera si no hay instrumentos o se está revisando; `revisar()` espera a que se devuelvan todos y `terminar()` despierta a los investigadores |
| Parcial 2 2016: base de datos de informes (lectores y escritores) | `examen2016datos` | Varios pueden consultar a la vez; guardar espera si hay alguien guardando o consultando; `terminar()` mira `guardando` para saber qué deshacer. Equidad: las consultas pueden dejar sin guardar para siempre a quien espera (inanición); se arregla contando los que esperan para guardar y dando turnos |
| Parcial 2 2017: cajón de muestras | `examen2017cajon` | Productor-consumidor con capacidad N: `coloca()` espera si está lleno, `extrae()` si está vacío |
| Extraordinario 2025-26: salud del jefe final | `examenextraordinario2526jefe` | Condición de carrera en `recibirDano()` (dos hebras leen 10000 y escriben 9900: se pierde un golpe), arreglada con `synchronized`; `autoResurreccion()` espera mientras `salud > 0` |
| Parcial 2 2019: dispensador de reactivo | `examen2019dispensador` | El primero que lo encuentra vacío avisa al ayudante; al reponer, la dosis queda reservada para el que avisó |
| Parcial 2 2021: máximo 3 trenes por línea | `examen2021trenes` | El monitor va dentro de `LineaMetroImpl`; `arrancaTren()` crea la hebra con una lambda que pide paso, se mueve y avisa al terminar |
| Ordinario 2025-26: bandeja de hamburguesas | `examenordinario2526bandeja` | Capacidad 5 y espera mientras se limpia; el `setLimpiando()` también hace `notifyAll()` |

## Cómo ejecutarlo

- **Eclipse:** File > Import > Existing Projects into Workspace, y Run As > Java Application sobre cualquier clase con `main`.
- **Terminal:**
  ```
  javac -d bin src/concurrencia/*.java
  java -cp bin concurrencia.Ejercicio8Parking
  ```
