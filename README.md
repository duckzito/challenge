# Challenge

## Instalación

### requisitos

* java 11
* maven
* mongodb
* docker 

```bash
docker-compose up
```

La aplicación inicia una instancia de mongodb, populandola en base al archivo prices.json para tener una base minima de muestra que nos permita ejecutar los request definidos en la colección de postman adjunta.

## Aclaraciones

* En cuanto a la paginación en un servicio reactivo, hay que tener presente que son conceptos que no se llevan del todo bien, porque lo idea seria poder consumir un Flux como un stream de eventos, y al forzar a tener un paginado nos obliga a tener un numero determinado de eventos cuando estos pueden llegar a ser infinitos. Un buen aproach seria devolver los resultados usando SSE.


* El GET para obtener el precio en un timestamp puntual, genera una query donde busca en un periodo de 10 minutos y devuelve el ultimo encontrado, para evitar tener que conocer la fecha puntual en la que se inserto un registro.
  

* Por simplicidad los parametros de fechas usan el siguiente formato: ```2021-08-16T09:39:36```
  

* El tamaño de las respuestas paginadas es de 10 elementos. 


## Pendientes 

* Test unitarios


## Puntos de mejora

* Agregar cache
* Mejorar el manejo de errores
* El endpoint que calcula el promedio no escala, ya que para hacer el avg obtiene todos los resultados de la base entre las fechas dadas y eventualmente eso podrian ser millones y millones de registros. 


