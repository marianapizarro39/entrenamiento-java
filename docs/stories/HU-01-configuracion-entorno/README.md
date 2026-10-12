# HU-01: Configuración del entorno de desarrollo

## ¿Qué hace?
Deja listo el entorno para desarrollar la API con Java y Spring Boot, y deja armado el repositorio y el tablero de trabajo. 

## Herramientas instaladas 

|Herramienta | Versión |
| JDK (Eclipse Temurin) | 21.0.12.1 |
| Gradle (Groovy, con wrapper) | incluido en el proyecto |
| Spring Boot | 4.1.1 |
| PostgreSQL | 18.6 |
| Git | 2.56.0 |
| Cliente de BD | pgAdmin y DBeaver |
| Pruebas de API | REST Client e Insomnia |

## Cómo verificar el entorno
1. `java -version` muestra Java 21.
2. `psql --version` muestra PostgreSQL 18.
3. `./gradlew bootRun` dentro de `GestionPersonasBackend`arranca la app en el puerto 8080.

## Decisiones
- **Pasé de Maven a Gradle (con Groovy) por tres razones:
  - Es la herramienta que má se usa en la empresa, así que me conviene aprenderla desde ahora.
  - Quise familiarizarme con ella para aprender una herramienta que se usa en proyectos reales. 
  - Es una versión estable y Spring Initializr ya genera el proyecto listo para Gradle, así que el cambio fue sencillo. 