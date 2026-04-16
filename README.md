# Documento de instrucciones y requisitos
## Introducción
Este programa calcula la puntuación de las tarjetas de puntuación de los bolos. Hay que pasar este codigo de python a java 21. Este proyecto se va a desarrollar con TDD con [todos estos casos test](/test/test_score_card.py) escribiendo el codigo de forma incremental para que los vaya pasando uno a uno.

## Requisitos
### Requisito 1

**Historias de usuario:**  El usuario pasa una tarjeta de puntuación y se calcula la puntuación total.

#### Criterio de aceptación

1.La tarjeta de puntuación va a ser un STRING.
2.La puntuación total debe de ser un INTEGER.

### Requisito 2
El código debe pasar todos estos casos test [casos test](/test/test_score_card.py)

### Requisito 3
El código TIENE QUE respetar los principios SOLID.

### Requisito 4
Utiliza GRADLE como gestor de dependencias.

### Requisito 5
Los casos test TIENEN QUE usar JUNIT5

### Requisito 6
Respetar los nombres del dominio.

### Requisito 7
Mantener el código python en un directorio aparte.

### Requisito 8
No utilizar herencia.

## Componentes
El programa consta de una clase principal score_card y un tipo enumerado pins. No se debe utilizar herencia. El archivo main debe ser score_card

## Control de versiones
Se debe utilizar la estructura de ramas de gitflow, haciendo commits cada vez que se pase un caso test respetando la convención de commits.
