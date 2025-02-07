# 

## Tabla de Contenidos
- [](#)
  - [Tabla de Contenidos](#tabla-de-contenidos)
- [ Quarkus](#-quarkus)
    - [¿Qué es GraalVM?](#qué-es-graalvm)
    - [programacion reactiva](#programacion-reactiva)
      - [vertx](#vertx)
        - [dependencia necesaria para vertx](#dependencia-necesaria-para-vertx)
    - [Mutiny](#mutiny)
      - [Uni y Multi](#uni-y-multi)
      - [dependencia necesaria para mutiny](#dependencia-necesaria-para-mutiny)
    - [Hibernate orm panache](#hibernate-orm-panache)
      - [Dependencias necesarias para hibernate orm panache](#dependencias-necesarias-para-hibernate-orm-panache)
    - [Microservicios](#microservicios)
  - [Prerrequisitos para trabajar con Quarkus](#prerrequisitos-para-trabajar-con-quarkus)
    - [Java](#java)
    - [Configuración de las variables de entorno](#configuración-de-las-variables-de-entorno)
    - [IDE](#ide)
    - [Apache Maven](#apache-maven)
    - [Configuración de las variables de entorno](#configuración-de-las-variables-de-entorno-1)
    - [Docker Desktop](#docker-desktop)
  - [Creación de un proyecto Quarkus](#creación-de-un-proyecto-quarkus)
  - [Estructura de un proyecto Quarkus](#estructura-de-un-proyecto-quarkus)
- [Configuración base de datos en Quarkus](#configuración-base-de-datos-en-quarkus)
  - [Configuración de una base de datos de forma sincrona](#configuración-de-una-base-de-datos-de-forma-sincrona)
  - [Configuración de una base de datos de forma asincrona](#configuración-de-una-base-de-datos-de-forma-asincrona)
  - [Conexión externa a la base de datos](#conexión-externa-a-la-base-de-datos)
    - [JDBC vs vertx-reactive](#jdbc-vs-vertx-reactive)
- [instalación de una base de datos en docker](#instalación-de-una-base-de-datos-en-docker)
    - [Crear bd y usuario en la imagen de sqlserver](#crear-bd-y-usuario-en-la-imagen-de-sqlserver)
- [Conexion de microservicios de quarkus en docker](#conexion-de-microservicios-de-quarkus-en-docker)
  - [Cofiguracion de docker](#cofiguracion-de-docker)
    - [configuracion pom.xml](#configuracion-pomxml)
    - [1. Contruccion de la imagen de docker para los dos microservicios](#1-contruccion-de-la-imagen-de-docker-para-los-dos-microservicios)
    - [2. Crear una red en docker](#2-crear-una-red-en-docker)
  - [3. Subir los contenedores a docker hub](#3-subir-los-contenedores-a-docker-hub)

# <center> Quarkus</center>

Quarkus es un framework de Java diseñado para construir aplicaciones de forma rápida y eficiente, especialmente en entornos de microservicios y aplicaciones en la nube. Quarkus se basa en la tecnología de GraalVM, que permite compilar aplicaciones Java en binarios nativos, mejorando significativamente el rendimiento y la eficiencia junto con la implemetacion de programacion reactiva la cual permite trabajar con operaciones, eventos y flujos de forma asincrona.

<section style="background-color: white;">
<image src="https://quarkus.io/assets/images/home/quarkus_metrics_graphic_bootmem_wide.png">
</section>

> [!NOTE]
>
> Para obtener más información sobre Quarkus, haz clic en [Quarkus](https://quarkus.io/)



### ¿Qué es GraalVM?

GraalVM es una máquina virtual universal que soporta múltiples lenguajes de programación y permite la ejecución de aplicaciones en diferentes entornos. Una de las características más destacadas de GraalVM es su capacidad para compilar aplicaciones Java en binarios nativos. Esto significa que las aplicaciones pueden ejecutarse directamente en el sistema operativo sin necesidad de una máquina virtual Java (JVM), lo que reduce el tiempo de arranque y el uso de memoria.

<center> <img src="https://docs.oracle.com/en/graalvm/enterprise/20/docs/docs/img/graalvm_architecture.png"> </center>

> [!NOTE]
>
> Para obtener más información sobre GraalVM, haz clic en [GraalVM](https://www.graalvm.org/).
>

### programacion reactiva

La programación reactiva es un paradigma de programación que se basa en la reactividad, es decir, en la capacidad de responder a los eventos de forma eficiente y escalable. En este sentido, la programación reactiva se centra en la creación de aplicaciones que puedan responder a los eventos de forma rápida y eficiente, lo que permite mejorar la experiencia del usuario y la escalabilidad de las aplicaciones.



> [!NOTE]
>
> Para entender mas sobre la programacion reactiva en quarkus, haz clic en [Programacion reactiva](https://quarkus.io/guides/quarkus-reactive-architecture).
>
En Quarkus, para trabajar con programación reactiva, se utilizan Vert.x, Mutiny y Hibernate ORM con Panache las cuales son  dependencias necesarias para trabajar con programacion reactiva en quarkus.

#### vertx 
Es un motor de eventos que permite construir aplicaciones reactivas y escalables. Vert.x se basa en el modelo de actores, lo que significa que cada componente de la aplicación es un actor independiente que puede recibir y enviar mensajes de forma asíncrona. Esto permite construir aplicaciones reactivas que pueden responder a los eventos de forma eficiente y escalable.

##### dependencia necesaria para vertx

```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-vertx</artifactId>
</dependency>
```

>[!NOTE]
>
> Para obtener más información sobre Vert.x y  dependencias mas especificas, haz clic en [Vert.x](https://es.quarkus.io/guides/vertx).

### Mutiny
Es una biblioteca reactiva que permite trabajar  operaciones, eventos y flujos de forma reactiva. Mutiny se basa en el modelo de programación reactiva, lo que significa que las operaciones se realizan de forma asíncrona y se pueden componer de forma eficiente y escalable.

se puede trabajar con dos tipos de resultados:
#### Uni y Multi
- Uni: representa un resultado unico ejemplo una consulta a la base de datos

- Multi: representa un resultado multiple ejemplo una lista de resultados de una consulta a la base de datos

#### dependencia necesaria para mutiny

```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-mutiny</artifactId>
</dependency>

```
>[!NOTE]
>
> Para obtener más información sobre Mutiny y  dependencias mas especificas, haz clic en [Mutiny](https://quarkus.io/guides/mutiny-primer).

### Hibernate orm panache
Permite trabajar con una base de datos de forma reactiva y simplifica las operaciones de base de datos en aplicaciones reactivas.

#### Dependencias necesarias para hibernate orm panache

```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-hibernate-orm-panache</artifactId>
</dependency>
```

```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-hibernate-validator</artifactId>
</dependency>
```

> [!NOTE]
>
> Para obtener más información sobre Hibernate orm panache y  dependencias mas especificas, haz clic en [Hibernate orm panache](https://quarkus.io/guides/hibernate-orm-panache).

### Microservicios

Los microservicios son una arquitectura de software que divide una aplicación en pequeños servicios independientes que se comunican entre sí. Cada microservicio se encarga de una funcionalidad específica y puede desarrollarse, desplegarse y escalarse de forma independiente. Quarkus está optimizado para la creación de microservicios, proporcionando un entorno de desarrollo rápido y eficiente, así como herramientas para la integración y despliegue en la nube.

<center><img src='https://www.arrobasolutions.com/wp-content/uploads/2021/10/Arquitectura-de-microservicios-que-es-y-cuales-son-sus-ventajas.jpg'></center>

>[!NOTE]
>
> para obtener mas informacion sobre microservicios en quarkus, haz clic en [Microservicios](https://www.redhat.com/es/topics/microservices).


## Prerrequisitos para trabajar con Quarkus 
Para poder ejecutar, compilar y empaquetar un proyecto de Quarkus, es necesario tener instalado en el sistema las siguientes herramientas:

### Java
Quarkus requiere de una versión de Java 17 o superior para poder ejecutar y compilar aplicaciones. para este caso instalaremos el JDK 21 acompañado de GraalVM para poder compilar y empaquetar la aplicacion en un ejecutable nativo.

[Descargar GraalVM for JDK 21.0.5 ](https://www.oracle.com/java/technologies/downloads/#graalvmjava21-windows)

### Configuración de las variables de entorno
Para configurar las variables de entorno de Java, se debe seguir los siguientes pasos:

1. Descomprimir el archivo descargado de GraalVM en una carpeta de preferencia.
2. Crear una nueva variable de entorno llamada `JAVA_HOME`,`GRAALVM_HOME` y asignarle la ruta de la carpeta donde se descomprimió GraalVM. 
Por ejemplo:
```
GRAALVM_HOME=C:\Program Files\GraalVM\graalvm-jdk-21.0.5+9.1
JAVA_HOME=C:\Program Files\GraalVM\graalvm-jdk-21.0.5+9.1
```
3. Editar la variable de entorno `Path` y agregar la ruta de la carpeta `bin` de GraalVM.
Por ejemplo:
```
%JAVA_HOME%\bin
%GRAALVM_HOME%\bin
```
4. Para verificar que la instalación de Java se realizó correctamente, abrir una terminal y ejecutar el siguiente comando:
```
java -version
```

### IDE
Para el desarrollo de aplicaciones con Quarkus, se recomienda utilizar IDE intellij IDEA debido a que es una de las herramientas más completas y con mayor soporte para el desarrollo de aplicaciones con Quarkus. Para instalar intellij IDEA, se debe seguir los siguientes pasos:

1. Descargar el instalador de intellij IDEA Community 2024-3 desde el siguiente enlace: [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/es-es/idea/download/?section=windows).

2. Ejecutar el instalador descargado y seguir los pasos que se indican en el asistente de instalación.

3. Una vez finalizada la instalación, abrir intellij IDEA y ir al a opcion de plugins en el menú de configuración y seleccionar los siguientes plugins 
   - Quarkus Tools
   - Lombok
   - Docker
   - Github Copilot
  
  <center> <img src='./imgs/img_extensiones.png' style= 'width: 80%; height: 50%;'> </img></center>

### Apache Maven
Maven es una herramienta de gestión y construcción de proyectos Java. Quarkus utiliza Maven para gestionar las dependencias, Para instalar Apache Maven, se debe Descargar el archivo binario de Apache Maven-3.9.9 desde el siguiente enlace: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).

### Configuración de las variables de entorno
Para configurar las variables de entorno de Maven, se debe seguir los siguientes pasos:

1. Descomprimir el archivo descargado de Apache Maven en una carpeta de preferencia.
2. Crear una nueva variable de entorno llamada `MAVEN_HOME` y asignarle la ruta de la carpeta donde se descomprimió Apache Maven.
Por ejemplo:
```
MAVEN_HOME=C:\Program Files\apache-maven-3.9.9
```
3. Editar la variable de entorno `Path` y agregar la ruta de la carpeta `bin` de Apache Maven.
Por ejemplo:
```
%MAVEN_HOME%\bin
```

### Docker Desktop
Docker Desktop es una herramienta que permite la creación, administración y ejecución de contenedores Docker en sistemas operativos Windows y macOS; en nuestro caso, se utilizará Docker Desktop para ejecutar contenedores de aplicaciones Quarkus en un entorno de desarrollo local

Para instalar Docker Desktop, se debe seguir los siguientes pasos:

1. Descargar el instalador de Docker Desktop v.4.36.0 desde el siguiente enlace: [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop/).

2. Ejecutar el instalador descargado y seguir los pasos que se indican en el asistente de instalación.

> [!NOTE]
>  Para obtener más información sobre docker, haz clic en [Docker](https://www.docker.com/).
>

## Creación de un proyecto Quarkus
Para crear un proyecto Quarkus, hay varias formas para hacerlo.
1. Crear un proyecto Quarkus desde la línea de comandos [Quarkus CLI](https://quarkus.io/guides/cli-tooling)
2. Crear un proyecto Quarkus desde la página web de Quarkus utilizando el siguiente enlace: [https://code.quarkus.io/](https://code.quarkus.io/).
3. Crear un proyecto Quarkus desde el IDE IntelliJ IDEA utilizando el asistente de creación de proyectos, en nuestro caso, se utilizará esta opción.
      3.1. Tener instalado el plugin de Quarkus Tools en IntelliJ IDEA.

      3.2. Ir a la opción de `File` -> `New` -> `Project` y seleccionar la opción de `Quarkus` en el asistente de creación de proyectos.

      <center><img src='./imgs/img_newproject.png' style= 'width: 100%; height: 50%;'></img></center>

      3.3. Seleccionar las opciones de configuración del proyecto, como el nombre del proyecto, el grupo y la versión de Quarkus.

      <center><img src='./imgs/img_configuracion.png' style= 'width: 100%;'></img></center>

      3.4. También se pueden seleccionar las extensiones de Quarkus que se desean agregar al proyecto, como Hibernate ORM, RESTEasy, etc.

      <center><img src='./imgs/img_configExtensiones.png' style= 'width: 80%; height: 50%;'></img></center>



## Estructura de un proyecto Quarkus
Al momento de crear un proyecto Quarkus, se generan una serie de carpetas y archivos que conforman la estructura del proyecto. A continuuacion se muestra una estructura basica de un proyecto Quarkus

<center><img src='./imgs/ims_estructuraQuarkus.png' style= 'width: 80%; height: 50%;'></img></center>



# Configuración base de datos en Quarkus

En quarkus se puede trabajar con diferentes bases de datos como mysql, postgresql, sqlserver, etc; hay dos formas de trabajar con una base de datos en quarkus de forma sincrona y asincrona, A continuacion se explicara como configurar una base de datos en quarkus.

## Configuración de una base de datos de forma sincrona

Para la configuracion de una base de datos de forma sincrona se debe agregar las siguientes dependencias en el archivo pom.xml

```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-jdbc-mssql</artifactId>
</dependency>

```

Para la configuracion de la base de datos se debe agregar las siguientes propiedades en el archivo aplication.properties

``` properties

quarkus.datasource.db-kind=mssql
quarkus.datasource.username=user
quarkus.datasource.password=pass
quarkus.datasource.jdbc.url=jdbc:sqlserver://localhost:1433/name_db
quarkus.datasource.jdbc.max-size=20

```

- se trabaja con jdbc para la conexion a la base de datos de forma sincrona y se bloquea el hilo de ejecucion hasta que se obtenga una respuesta de la base de datos

## Configuración de una base de datos de forma asincrona

Para la configuracion de una base de datos de forma asincrona se debe agregar las siguientes dependencias en el archivo pom.xml

```xml
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-reactive-mssql-client</artifactId>
    </dependency>
```

esta dependencia es el controlador de base de datos reactiva para mssql. Hibernate Reactive usa ese controlador para interactuar con la base de datos sin bloquear el subproceso del llamador.

para la configuracion de la base de datos se debe agregar las siguientes propiedades en el archivo aplication.properties

```properties

quarkus.datasource.db-kind=mssql
quarkus.datasource.username=user
quarkus.datasource.password=Pass
quarkus.datasource.reactive.url=vertx-reactive:sqlserver://localhost:1433/name_db
quarkus.datasource.reactive.max-size=20
quarkus.datasource.jdbc=false 
```

- se trabaja con vertx-reactive para la conexion a la base de datos de forma asincrona y no se bloquea el hilo de ejecucion

> [!NOTE]
>
> el jdbc= false indica que se va a trabajar con una base de datos reactiva ya que el jdbc se trabaja con una base de datos de forma sincrona y la idea es que sea de manera asincroica y la aplication properties tiene por defecto el jbc

## Conexión externa a la base de datos
Para conectar quarkus con un db donde su properties estan en una ruta especifica se debe agregar la siguiente propiedad en el archivo aplication.properties

``` properties
quarkus.cofig.locations=classpath:application.properties, file:/path/to/properties
```
el classpath:application.properties es la ruta por defecto de las propiedades de la aplicacion y el file:/path/to/properties es la ruta donde se encuentra el archivo de propiedades de la base de datos


> [!NOTE]
>
> hay diferentes configuraciones para trabajar con una base de datos de forma asincrona y local o en la nube

### JDBC vs vertx-reactive

- JDBC: 
    - se trabaja de forma sincrona
    - se bloquea el hilo de ejecucion hasta que se obtenga una respuesta de la base de datos
    - no es eficiente para aplicaciones reactivas
- vertx-reactive: 
    - se trabaja de forma asincrona
    - no se bloquea el hilo de ejecucion 
    - es eficiente para aplicaciones reactivas

> [!NOTE]
>
> Para obtener más información sobre la configuracion de una base de datos en quarkus, haz clic en [Configuracion de base de datos](https://quarkus.io/guides/datasource).

# instalación de una base de datos en docker

para la instalacion de una base de datos en docker se debe seguir los siguientes pasos

1. Descargar imagen de la base de datos que se va a instalar en este caso sqlserver

```bash
docker pull mcr.microsoft.com/mssql/server:2017-latest
```
2. Crear contenedor con la imagen de sqlserver
```bash
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=YourStrong!Passw0rd' -p 1433:1433 --name sqlserver2017 -d mcr.microsoft.com/mssql/server:2017-latest
```

3. Verificar que el contenedor este corriendo
```bash
docker ps
```

### Crear bd y usuario en la imagen de sqlserver
para crear la base de datos y el usuario en la imagen de sqlserver se puede hacer de dos formas, la primera es conectandose a la imagen de sqlserver y la segunda es copiando un archivo .sql a la imagen de sqlserver, en este caso se va a realizar la segunda forma

```bash
docker start sqlserver2017

docker cp db.sql sqlserver2017:/db.sql 

docker exec -it sqlserver2017 /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'YourStrong!Passw0rd' -i /db.sql
```

# Conexion de microservicios de quarkus en docker

## Cofiguracion de docker

vamos a plantear un ejemplo para comprender este punto, primero tenemos un microservicio llamadao product que se va a conectar a una base de datos sqlserver y luego tenemos otro microservicio llamado customer que se va a conectar al primer microservicio y a la base de datos sqlserver

### configuracion pom.xml
```xml
<quarkus.package.type>native</quarkus.package.type>
<quarkus.native.container-build>true</quarkus.native.container-build>
```
el package type native indica que se va a trabajar con una imagen de docker y el container-build indica que se va a trabajar con un contenedor de docker


### 1. Contruccion de la imagen de docker para los dos microservicios

```bash
./mvnw package -Dnative 


docker build -f src/main/docker/Dockerfile.native-micro -t quarkus/code-with-quarkus .


```
Una vez realizadas las pruebas del contenedor se crea de manera permanente ya que el -rm elimina el contenedor una vez se detiene

```bash
docker network create my_network


docker run -i -p 8080:8080 --network my_network --name customer quarkus/code-with-quarkus

docker run -i -p 8081:8081 --network my_network --name product quarkus/code-with-quarkus

docker network inspect my_network
``` 
Antes de crear los contenedores se debe crear una red en docker para que los contenedores puedan comunicarse entre ellos;En este caso se creo dos contenedores uno para el microservicio product y otro para el microservicio customer


>[!NOTE]
>
> esta informacion es un ejemplo para la creacion de un dockerfile.native-micro para un microservicio, para mas informacion de los diferentes tipos de dockerfile se puede consultar en la documentacion de quarkus

### 2. Crear una red en docker
En caso de que ya tenga creado los contenedores y quiera conectarlos a una red en docker se puede hacer de la siguiente manera

- primero creamos una red en docker para que los contenedores puedan comunicarse entre si
```bash
docker network create my_network
```

- luego conectamos los contenedores a la red creada
```bash
docker network connect my_network my-quarkus-container
```
En nuestro caso sera a los dos contenedores

```bash

docker network connect my_network customer

docker network connect my_network product
```
ahora confirma que los contenedores esten conectados a la red

```bash
docker network inspect my_network
```

## 3. Subir los contenedores a docker hub
Para subir los contenedores a docker hub se debe seguir los siguientes pasos

1. Iniciar sesion en docker hub
```bash
docker login
```
2. 
```bash
docker commit <container-id> tuusuario/nombreimagen:v1.0

docker commit 28663bcd0e8fb42628ed91643a7f87bdd950cc3a187af6ab0c4a4e1c270ececb avvillas/product:v1.0

docker tag tuusuario/tuimagen:v1.0 tuusuario/tuimagen:v1.0
#este tag  sirve para que la imagen se pueda subir a docker hub
# ejemplo
docker tag avvillas/product:v1.0 avvillas/product:v1.0 

docker push tuusuario/tuimagen:v1.0
# ejemplo
docker push avvilas/product:v1.0
```

para probar que la imagen se haya creado correctamente se puede hacer de la siguiente manera

```bash
docker pull tuusuario/tuimagen:v1.0
# ejemplo
docker pull avvillas/product:v1.0
```

