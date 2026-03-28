# Ejercicio de automatización con Serenity

Prueba de saucedemo.com usando framework Serenity Screenplay.

## Requisitos
Los siguientes programas son necesarios para ejecutar el proyecto:

* Java 17 (el projecto fue ejecutado usando la versión 17.0.2)
* Maven 3.9.6
* Firefox (última versión... el proyecto fue probado con Firefox 141.0 (64-bit))

## Cómo ejecutar la prueba
Desde la consola, ejecutar el siguiente comando en la carpeta del proyecto:

```shell
mvn clean verify
```
Mientras la prueba se ejecuta, debería abrirse la ventana del navegador que muestra a la prueba ejecutando las acciones
para realizar una compra en el sitio saucedemo.com

## Cómo revisar los reportes
Una vez ejecutada la prueba, el reporte se encuentra en la siguiente ubicación:

```
target/cucumber-reports.html
```
