# Challenge

Construir un MicroServicio que haciendo uso del siguiente servicio REST (https://be.buenbit.com/api/market/tickers)

#### A) Realice una llamada recurrente cada 10 segundos, almacene los datos y que exponga a través de un API REST las siguientes funcionalidades

1) Obtener el precio del Bitcoin en pesos argentinos (objeto btcars en la API de buenbit) en cierto timestamp.

2) Conocer el promedio de precio entre dos timestamps ingresados por parametros.
3) Devolver en forma paginada los datos almacenados con o sin filtro de
   timestamp.

#### B) Incorporar un conjunto de test unitarios que demuestren los casos funcionales de la solución

#### C) Incorporar un archivo README.md que contenga una descripción de la solución propuesta así como instrucciones de ejecución en entorno local.

## Indicaciones
* La aplicación deberá estar desarrollada usando Springboot y subida a un repositorio en github con permisos públicos de acceso y clonado.(Java 1.8 o superior)
* La aplicación deberá poder ser ejecutada en entorno local.
* Solid, Clean Code.
* La persistencia de información se podrá realizar en MongoDB o Redis
* En el README realizar cualquier aclaración, de ser necesaria, de como se pensó la solución.
## Puntos extras si
* Usa webflux
* Es desarrollado en kotlin
* Realiza los test de integración con Postman, Rest Assured o similar
* Dockerización