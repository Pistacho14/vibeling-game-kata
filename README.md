# Documento de instrucciones y requisitos
## Introducción
Este programa calcula la puntuación de las tarjetas de puntuación de los bolos. La implementación principal está migrada a Java 25. El código Python original se conserva en [python/](python/) y los casos de comportamiento se han trasladado a JUnit 5.

## Requisitos
### Requisito 1

**Historias de usuario:**  El usuario pasa una tarjeta de puntuación y se calcula la puntuación total.

#### Criterio de aceptación

1.La tarjeta de puntuación va a ser un STRING.
2.La puntuación total debe de ser un INTEGER.

### Requisito 2
El código debe pasar todos los casos de [ScoreCardTest](src/test/java/score_card/ScoreCardTest.java)

### Requisito 3
El código TIENE QUE respetar los principios SOLID.

### Requisito 4
Utiliza GRADLE como gestor de dependencias.

### Requisito 5
Los casos test TIENEN QUE usar JUNIT5

### Requisito 6
Respetar los nombres del dominio.

### Requisito 7
Mantener el código Python en [python/](python/).

### Requisito 8
No utilizar herencia.

## Componentes
El programa consta de una clase principal `ScoreCard` y un tipo enumerado `Pins`, en el paquete `score_card`. No se utiliza herencia. La clase principal de dominio es `ScoreCard`.

## Ejecución

Se necesita un JDK 25. Las pruebas se ejecutan con:

```text
gradlew.bat test
```

La configuración de Gradle usa el toolchain de Java 25 y JUnit 5.

## Control de versiones
Se debe utilizar la estructura de ramas de gitflow, haciendo commits cada vez que se pase un caso test respetando la convención de commits.
