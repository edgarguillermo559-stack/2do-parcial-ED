# Sistema de Control de Vuelos Internacionales y Pasajeros de Riesgo

## Universidad de Pinar del Río "Hermanos Saíz Montes de Oca"

### Asignatura: Estructuras de Datos
### Segundo Examen Parcial
### Equipo 2

#### Integrantes

- Katherine Alvarez Amador
- Adrián Jesús Peláez Rodríguez
- Adrián Enrique Reinoso Gómez
- Edgar Guillermo Hernández Domínguez
- Alejandro Javier Montesino Ruiz

---

# Descripción del Proyecto

Este proyecto implementa un sistema de gestión de vuelos internacionales desarrollado en Java mediante estructuras dinámicas enlazadas.

La aplicación permite administrar vuelos y pasajeros utilizando una estructura jerárquica tipo Multilista, complementada con una Pila (LIFO), una Cola (FIFO) y algoritmos recursivos para el procesamiento de información.

El objetivo principal consiste en modelar una agencia de vuelos capaz de registrar vuelos, gestionar pasajeros, controlar cancelaciones, organizar listas de espera prioritarias y detectar vuelos considerados de riesgo.

---

# Objetivo Académico

Aplicar los conocimientos adquiridos en la asignatura Estructuras de Datos mediante el diseño e implementación de:

- Multilistas dinámicas.
- Pilas enlazadas.
- Colas enlazadas.
- Recursividad.
- Programación Orientada a Objetos en Java.
- Manejo de estructuras dinámicas basadas en nodos.

---

# Problemática Resuelta

La agencia necesita administrar múltiples vuelos internacionales, donde cada vuelo mantiene su propia lista de pasajeros.

El sistema permite:

- Registrar nuevos vuelos.
- Registrar pasajeros asociados a cada vuelo.
- Gestionar cancelaciones de pasajes.
- Recuperar la última cancelación realizada.
- Organizar pasajeros en una lista de espera prioritaria.
- Identificar vuelos de riesgo mediante un análisis recursivo.

---

# Estructuras de Datos Implementadas

## 1. Multilista

La estructura principal del sistema está formada por una multilista de dos niveles:

```text
Agencia
│
├── Vuelo 1
│   ├── Pasajero 1
│   ├── Pasajero 2
│   └── Pasajero n
│
├── Vuelo 2
│   ├── Pasajero 1
│   └── Pasajero n
│
└── Vuelo n
```

### Nodo Externo (Vuelo)

- Número de vuelo.
- Fecha.
- Destino.
- Referencia al siguiente vuelo.
- Referencia a la lista de pasajeros.

### Nodo Interno (Pasajero)

- Número de pasaporte.
- Nombre.
- País.
- Edad.
- Sexo.
- Referencia al siguiente pasajero.

---

## 2. Pila de Cancelaciones (LIFO)

La pila almacena los pasajeros cancelados recientemente.

### Operaciones Implementadas

- Push()
- Pop()
- Top()
- IsEmpty()

### Funcionalidad

Permite implementar una operación de deshacer (Undo), recuperando al último pasajero eliminado.

---

## 3. Cola de Espera Prioritaria (FIFO)

La cola almacena los pasajeros que participan en el proceso de abordaje.

### Operaciones Implementadas

- Add()
- Peek()
- Poll()
- Clear()
- Size()

La prioridad se construye insertando primero las pasajeras femeninas y posteriormente los pasajeros masculinos.

---

# Método Recursivo

## Detección de Vuelos de Riesgo

Un vuelo se considera de riesgo cuando posee más de 10 pasajeros menores de 16 años.

El algoritmo realiza:

1. Un recorrido recursivo de la lista de pasajeros para contar menores de edad.
2. Un recorrido recursivo de todos los vuelos para determinar cuáles cumplen la condición de riesgo.

El resultado final retorna la cantidad total de vuelos de riesgo presentes en el sistema.

---

# Funcionalidades del Sistema

1. Registrar vuelos.
2. Registrar pasajeros.
3. Mostrar vuelos y pasajeros.
4. Mostrar pasajeros cancelados.
5. Cancelar pasajeros.
6. Deshacer cancelaciones.
7. Mostrar lista de espera prioritaria.
8. Mostrar vuelos de riesgo.
9. Ejecutar batería de pruebas automáticas.

---

# Casos de Prueba Implementados

## Casos Positivos

- Registro exitoso de vuelos.
- Registro exitoso de pasajeros.
- Cancelación de pasajeros.
- Recuperación de cancelaciones.
- Generación de la cola prioritaria.
- Identificación correcta de vuelos de riesgo.

## Casos Negativos y de Borde

- Ausencia de vuelos registrados.
- Intento de registrar pasajeros sin vuelos.
- Vuelo inexistente.
- Pila vacía.
- Cola vacía.
- Pasajeros no encontrados.
- Vuelos sin pasajeros.

---

# Requisitos del Sistema

- Java JDK 8 o superior.
- IntelliJ IDEA, NetBeans o Eclipse.
- Sistema operativo compatible con Java.

---

# Ejecución

1. Abrir el proyecto en el IDE.
2. Compilar todas las clases.
3. Ejecutar Main.java.
4. Utilizar el menú interactivo.

---

# Estructura del Proyecto

```text
Proyecto
│
├── Main.java
├── ClaseControl.java
├── Nodo.java
├── NodoExterno.java
├── NodoInterno.java
├── LinkedStack.java
└── LinkedQueue.java
```

---

# Tecnologías Utilizadas

- Java
- Programación Orientada a Objetos
- Estructuras Dinámicas Enlazadas
- Algoritmos Recursivos

---

# Declaración sobre el Uso de Inteligencia Artificial

Para el desarrollo de este proyecto se utilizó Inteligencia Artificial únicamente como herramienta de apoyo académico.

Su utilización estuvo orientada a:

- Revisión de código.
- Refactorización menor.
- Apoyo en la documentación.
- Elaboración del archivo README.
- Verificación de buenas prácticas de programación.

La implementación de la lógica principal, estructuras de datos, algoritmos recursivos y pruebas funcionales fue desarrollada y comprendida por los miembros del equipo.

---

# Conclusiones

La realización de este proyecto permitió aplicar de forma práctica los conceptos fundamentales de estructuras de datos dinámicas estudiados en la asignatura.

Mediante la implementación de multilistas, pilas, colas y algoritmos recursivos se desarrolló una solución funcional para la gestión de vuelos internacionales y pasajeros de riesgo, fortaleciendo los conocimientos de programación orientada a objetos y diseño de estructuras enlazadas en Java.
