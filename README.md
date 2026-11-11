# ✈️ Control de Vuelos Internacionales y Pasajeros de Riesgo

**Universidad de Pinar del Río "Hermanos Saíz Montes de Oca"**
Facultad de Ciencias Técnicas · Departamento de Informática
Asignatura: **Estructuras de Datos** · Curso 2026-2027
**2do Examen Parcial · Equipo 2**

## 👥 Integrantes

- Katherine Alvarez Amador
- Adrian Jesus Pelaez Rodriguez
- Adrian Enrique Reinoso Gómez
- Edgar Guillermo Hernández Domínguez
- Alejandro Javier Montesino Ruiz

## 📑 Contenido

1. [Descripción del problema](#1-descripción-del-problema)
2. [Estructuras de datos empleadas](#2-estructuras-de-datos-empleadas)
3. [Estructura del proyecto](#3-estructura-del-proyecto)
4. [Cómo compilar y ejecutar](#4-cómo-compilar-y-ejecutar)
5. [Casos de prueba ejecutados](#5-casos-de-prueba-ejecutados)
6. [Presentación para la exposición oral](#6-presentación-para-la-exposición-oral)
7. [Declaración de uso de Inteligencia Artificial](#7-declaración-de-uso-de-inteligencia-artificial)

---

## 1. Descripción del problema

Una agencia de viajes necesita automatizar el control de sus **vuelos internacionales** y de los **pasajeros** de cada uno. El sistema debe permitir:

- Registrar los vuelos de la agencia (número, fecha y destino).
- Registrar los pasajeros de cada vuelo (número de pasaporte, nombre, país, edad y sexo).
- **Cancelar** el pasaje de un pasajero y poder **revertir** la última cancelación realizada.
- Organizar una **lista de espera prioritaria** para el abordaje.
- Determinar cuántos **vuelos son de riesgo**. Un vuelo se considera de riesgo si tiene **más de 10 pasajeros menores de 16 años**.

Para resolverlo se combinan las estructuras que pide la orden del examen: una **Multilista** como modelo principal, una **Pila (LIFO)** para las cancelaciones, una **Cola (FIFO)** para el abordaje y **métodos recursivos** para el conteo de vuelos de riesgo.

## 2. Estructuras de datos empleadas

Todas las estructuras están implementadas **desde cero con nodos enlazados** (punteros `next`), sin usar las colecciones de Java (`ArrayList`, `Stack`, `Queue`, etc.). La clase `ClaseControl` representa la agencia y concentra las operaciones.

### 2.1 Multilista: Agencia → Vuelos → Pasajeros

Es una lista simple de vuelos y cada vuelo enlaza a su propia lista simple de pasajeros.

```
head ─► [Vuelo AR123] ─────► [Vuelo IB456] ─────► [Vuelo LA789] ─► ...
              │                    │                    │
              ▼ listaPasajeros     ▼                    ▼
        [Pasajero 1]         [Pasajero 1]         [Pasajero 1]
              ▼ next               ▼ next               ▼ next
        [Pasajero 2]         [Pasajero 2]         [Pasajero 2]
              ▼                    ▼                    ▼
            null                 null                 null
```

| Clase | Papel | Atributos principales |
|---|---|---|
| `NodoExterno` | Nodo de la lista principal (vuelo) | `numeroVuelo`, `fecha`, `destino`, `next`, `listaPasajeros` |
| `NodoInterno` | Nodo de la sublista (pasajero) | `numeroPasaporte`, `nombre`, `pais`, `edad`, `sexo`, `next` |

Operaciones: `agregarVuelo`, `agregarPasajero` y `mostrarDatosVuelos`. Ambas inserciones se hacen **al final** de la lista.

### 2.2 Pila (LIFO): registro de cancelaciones

Guarda los pasajeros cancelados. El último en cancelarse es el primero en poder revertirse.

| Clase | Papel | Atributos |
|---|---|---|
| `NodoCancelacion` | Nodo de la pila | `info` (el pasajero), `infoVuelo` (vuelo del que salió), `next` |

Operaciones sobre la pila: `push`, `pop`, `top` e `isEmptyPila`.

Operaciones de negocio que la usan:

- `procesoCancelacion(nombre)`: busca al pasajero por nombre en todos los vuelos, lo **elimina de la sublista** (primero, medio o último) y lo apila con el número de su vuelo.
- `revertirCancelacion()`: desapila la última cancelación y **reinserta al pasajero en su vuelo original** (queda al final de la lista de ese vuelo).
- `mostrarPasajerosCancelados()`: recorre la pila de la cima hacia el fondo.

### 2.3 Cola (FIFO): lista de espera prioritaria para el abordaje

| Clase | Papel | Atributos |
|---|---|---|
| `NodoCola` | Nodo de la cola | `info` (el pasajero), `infoVuelo`, `next` |

Operaciones sobre la cola: `add` (encola al final), `poll` (desencola del frente), `peek` e `isEmptyCola`. La clase mantiene los punteros `first` y `last`, por lo que encolar y desencolar es O(1).

Operaciones de negocio:

- `agregarCola()`: **reinicia la cola** y la vuelve a llenar recorriendo toda la multilista, primero con los pasajeros de sexo `F` y después con los de sexo `M`. Así, **las mujeres tienen prioridad de abordaje** y, dentro de cada grupo, se respeta el orden de la multilista. Al reiniciar la cola, llamar varias veces a `agregarCola()` no genera duplicados.
- `mostrarCola(numeroVuelo)`: muestra los pasajeros en cola que pertenecen a un vuelo.

> Los pasajeros se encolan por referencia pero **no se eliminan de la multilista**: la cola representa el orden de abordaje, no la baja del vuelo.

### 2.4 Métodos recursivos: vuelos de riesgo

Se resuelve con métodos recursivos, **sin ningún `for` ni `while`**:

| Método | Qué hace | Caso base | Caso recursivo |
|---|---|---|---|
| `metodoContadorMenores(NodoInterno)` | Cuenta los pasajeros menores de 16 años de **una sublista** | `pasajero == null` → `0` | suma 1 si `edad < 16` y continúa con `getNext()` |
| `metodoContadorMenoresVuelos(NodoExterno)` | Recorre la **lista de vuelos** y cuenta los que tienen más de 10 menores | `vuelos == null` → `0` | calcula los menores del vuelo; si son `> 10` suma 1, y continúa con el siguiente vuelo |
| `metodoRecursivo()` | Punto de entrada: llama a `metodoContadorMenoresVuelos(head)` | | |

### 2.5 Complejidad

Con **V** vuelos y **P** pasajeros por vuelo:

| Operación | Complejidad |
|---|---|
| `agregarVuelo` | O(V) (recorre hasta el último vuelo) |
| `agregarPasajero` | O(V + P) |
| `procesoCancelacion` | O(V · P) en el peor caso |
| `push` / `pop` / `top` | O(1) |
| `add` / `poll` / `peek` | O(1) |
| `agregarCola` | O(V · P) |
| `metodoRecursivo` | O(V · P) (visita cada pasajero una vez) |

## 3. Estructura del proyecto

```
ParcialEstructuradeDatos/
├── README.md
├── docs/
│   └── Presentacion.pdf            ← (agregar la presentación aquí)
└── src/
    ├── Main.java                   # Casos de prueba (21 casos)
    ├── ClaseControl.java           # Multilista + Pila + Cola + métodos recursivos
    ├── NodoExterno.java            # Nodo de vuelo
    ├── NodoInterno.java            # Nodo de pasajero
    ├── NodoCancelacion.java        # Nodo de la pila
    └── NodoCola.java               # Nodo de la cola
```

## 4. Cómo compilar y ejecutar

**Requisitos:** JDK instalado. El proyecto está configurado en IntelliJ IDEA con JDK 22 y solo usa Java básico, sin dependencias externas.

**Desde IntelliJ IDEA:** abrir la carpeta del proyecto y ejecutar `Main`.

**Desde la terminal**, en la carpeta raíz del proyecto:

```bash
javac -d out src/*.java
java -cp out Main
```

Al ejecutarse, `Main` corre los 21 casos de prueba y muestra los resultados por consola.

## 5. Casos de prueba ejecutados

Las pruebas están en `Main.java` y son **funcionales y por consola**: cada caso imprime su resultado y, cuando hay un valor esperado, lo indica. Para facilitar la lectura, los pasajeros se imprimen con el prefijo `mn` (menor de 16 años) o `my` (16 años o más).

### Datos de prueba

| Vuelo | Destino | Pasajeros | Menores de 16 | ¿Es de riesgo? | Qué comprueba |
|---|---|---|---|---|---|
| `AR123` | Madrid | 14 | 12 | ✅ Sí | Vuelo de riesgo con adultos mezclados |
| `IB456` | Londres | 5 | 3 | ❌ No | Pocos menores |
| `LA789` | Lima | 15 | 15 | ✅ Sí | Todos menores |
| `AA101` | Miami | 10 | 10 | ❌ No | **Caso borde:** exactamente 10, y la regla pide *más de* 10 |
| `XX000` | París | 0 | 0 | ❌ No | **Caso borde:** vuelo sin pasajeros |

Resultado esperado del método recursivo: **2 vuelos de riesgo** (`AR123` y `LA789`).

### ✅ Casos positivos (flujos normales)

| Caso | Qué se prueba | Resultado esperado |
|---|---|---|
| 2 | Agregar 5 vuelos y sus pasajeros | Se registran en orden de inserción |
| 3 | Mostrar todos los vuelos con sus pasajeros | Lista completa con prefijos `mn`/`my` |
| 4 | Conteo recursivo de vuelos de riesgo | `2` (`AR123` y `LA789`) |
| 5.1 | Cancelar al **primer** pasajero de una sublista (Felipe, `AR123`) | Sale de la lista y entra a la pila |
| 5.2 | Cancelar a un pasajero del **medio** (Mateo, `AR123`) | Se reenlazan los nodos vecinos |
| 5.3 | Cancelar al **último** pasajero (Renata, `AR123`) | La sublista termina en el nodo anterior |
| 5.4 | Cancelar en **otro vuelo** (Edgar Guillermo, `IB456`) | Se cancela en el vuelo correcto |
| 6 | Estado de la multilista tras las cancelaciones | Los cancelados ya no aparecen |
| 7 | Mostrar la pila de cancelados | Orden LIFO: el más reciente primero (Edgar Guillermo) |
| 8 | Consultar la cima con `top()` | Último cancelado y su vuelo |
| 9 | Revertir dos cancelaciones | Vuelven Edgar Guillermo a `IB456` y Renata a `AR123`, en orden LIFO |
| 10 y 11 | Estado de la multilista y de la pila tras revertir | Reflejan el cambio; en la pila quedan Mateo y Felipe |
| 12 | Revertir hasta vaciar la pila | `isEmptyPila()` devuelve `true` |
| 14 | Llenar la cola con `agregarCola()` y mostrar por vuelo | Mujeres primero, luego hombres |
| 15 | Multilista después de llenar la cola | Los pasajeros siguen en sus vuelos |
| 16 | Atender la cola con `poll()` | Salen en orden FIFO con las mujeres primero; al final la cola queda vacía |
| 17 | Conteo recursivo después de operar la cola | Sigue siendo `2` |
| 18 | Llamar a `agregarCola()` dos veces seguidas | **No hay duplicados** porque la cola se reinicia |
| 19 | Vaciar la cola | `isEmptyCola()` devuelve `true` |
| 20 | Agregar 12 menores más a `AR123` y recalcular | Sigue siendo `2` |
| 21 | Estado final de la multilista | Muestra todos los datos acumulados |

### ❌ Casos negativos y de borde

| Caso | Qué se prueba | Resultado esperado |
|---|---|---|
| 1 | **Agencia vacía:** mostrar vuelos, cancelar, revertir, mostrar cancelados, contar riesgo y mostrar cola | Mensajes de error controlados y conteo `0`; no hay excepciones |
| 5.5 | Cancelar a un pasajero que **no existe** | `Error: No se encontró al pasajero en los vuelos registrados.` |
| 13 | **Revertir con la pila vacía** | `Error: No se encontraron pasajeros en la lista de cancelacion` |
| 14 | Mostrar la cola de un vuelo **sin pasajeros** (`XX000`) | Mensaje de que no hay pasajeros en cola para ese vuelo |
| Datos | Vuelo con **exactamente 10 menores** (`AA101`) | **No** se cuenta como de riesgo |
| Datos | Vuelo **sin pasajeros** (`XX000`) | Se muestra `No hay Pasajeros agendados para este viaje` y no se cuenta como de riesgo |

> **Nota:** los resultados esperados se obtuvieron siguiendo la lógica del código. Antes de entregar, conviene ejecutar `Main` y confirmar que la salida coincide con estas tablas.

## 6. Presentación para la exposición oral

📎 **Presentación:** _(completar: enlace a Canva / Google Slides / PowerPoint, o el archivo en `docs/Presentacion.pdf`)_

## 7. Declaración de uso de Inteligencia Artificial

> ⚠️ **Completar con los datos reales del equipo antes de entregar.** Esta declaración debe ser explícita y honesta, indicando en qué partes específicas se usó IA.

| Parte del trabajo | ¿Se usó IA? | Herramienta | Detalle |
|---|---|---|---|
| Diseño de las estructuras y código en Java | _(Sí / No)_ | _(completar)_ | _(qué se hizo con ayuda de IA)_ |
| Casos de prueba (`Main.java`) | _(Sí / No)_ | _(completar)_ | _(completar)_ |
| Refactorización / limpieza del código | _(Sí / No)_ | _(completar)_ | _(completar)_ |
| Redacción del `README.md` | Sí | Claude (Anthropic) | Redacción y organización del documento a partir de la orden del examen y del código del proyecto; el equipo lo revisó. |
| Presentación para la exposición | _(Sí / No)_ | _(completar)_ | _(completar)_ |

Los integrantes declaran que conocen y pueden explicar el funcionamiento de todo el código entregado.
