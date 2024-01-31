# TALLER 1: APLICACIONES DISTRIBUIDAS (HTTP, SOCKETS, HTML, JS,MAVEN, GIT)

En este taller se implementó un servidor que funciona como gateway para encapsular llamadas a servicios Web externos. 
La aplicación que entrega el servidor a los clientes web permite consultar información de películas de cine, 
esta información es obtenida del OMDb API.
=======
En este taller se implementó un servidor que funciona como gateway para encapsular llamadas a servicios web externos. La aplicación que entrega el servidor a los clientes web permite consultar información de películas de cine, esta información es obtenida de OMDb API.

## Guía de Inicio

Las siguientes instrucciones le permitirán descargar una copia y ejecutar la aplicación en su máquina local.

### Prerrequisitos

- Java versión 8 OpenJDK
- Maven
- Git

## Instalación 

1. Ubíquese sobre el directorio donde desea realizar la descarga y ejecute el siguiente comando:
   
     ``` git clone https://github.com/AndresOnate/AREP-TALLER1.git ```

2. Navegue al directorio del proyecto:
   
      ``` cd  AREP-TALLER1 ```

3. Ejecute el siguiente comando para compilar el código:

      ``` mvn compile ```

5.  Ejecute el siguiente comando para empaquetar el proyecto:
   
      ``` mvn package ``` 

6. Para iniciar el servidor, ejecute el siguiente comando:

    ``` java -cp target/LAB1_AREP-1.0-SNAPSHOT.jar edu.escuelaing.arep.app.HttpServer ```

7. Verifique en la linea de comanos que se imprimió el mensaje **Listo para recibir ...**

![image](https://github.com/AndresOnate/AREP-TALLER1/assets/63562181/ed3ca723-c5db-4c29-98ec-4fb5dea3287b)

8. De igual forma, puede abrir el proyecto con un IDE y ejecutar el método main de la clase HTTPServer. En la imagen siguiente se muestra el proyecto con el IDE IntelliJ:

![image](https://github.com/AndresOnate/AREP-TALLER1/assets/63562181/b0cc4c7c-d574-4c2a-bc4d-b059e1fe939c)

## Probando la Aplicación.  

Una vez muestra en la línea de comandos el mensaje **Listo para recibir ...**, se puede ingresar a la aplicación en cualquier navegador con la siguiente URL:

       http://localhost:35000/

Debería ver en pantalla lo siguiente:

![image](https://github.com/AndresOnate/AREP-TALLER1/assets/63562181/e714baba-5970-4b20-841c-441e59a1a87f)

Como puede observar, la aplicación tiene un espacio donde puede ingresar el título de la película. Una vez ingrese el título, presione el botón `Search`, 

![image](https://github.com/AndresOnate/AREP-TALLER1/assets/63562181/2f65c2e5-b14b-4449-98af-4bde4b6f6662)

El servidor puede tardar unos segundos mientras obtiene la información del API. La información de la película es mostrada en una tabla. Si desea consultar otra película, ingrese el título y vuelva a dar en el botón. 
   
## Ejecutando las Pruebas.  

A continuación se muestra cómo ejecutar las pruebas desde la línea de comandos y un IDE como IntelliJ.

1. Navegue al directorio del proyecto con la línea de comandos.
2. Ejecute el siguiente comando:
   
   ``` mvn test ```
3. Debe mostrarse en pantalla que las pruebas fueron exitosas.

   ![image](https://github.com/AndresOnate/AREP-TALLER1/assets/63562181/dfa43a9e-6c48-474a-95f2-89a070904051)

4. Puede ejecutar las pruebas desde un IDE como IntelliJ:

   ![image](https://github.com/AndresOnate/AREP-TALLER1/assets/63562181/68fbe63e-15e1-4564-b37b-947f620f0754)

## Construido Con. 

- Maven - Administración de dependencias

## Versión

1.0.0

## Autores

- Andrés Camilo Oñate Quimbayo

