# <center> Quarkus</center>

## Tabla de Contenidos
1. [Descripción](#descripción)
2. [Prerrequisitos](#prerrequisitos)
    - [Java](#java)
    - [IDE](#ide)
    - [Apache Maven](#apache-maven)
    - [Docker Desktop](#docker-desktop)
3. [Programación reactiva en Quarkus](#programación-reactiva-en-quarkus)
    - [Vert.x](#vertx)
    - [Mutiny](#mutiny)
    - [Hibernate ORM Panache](#hibernate-orm-panache)
4. [Configuración base de datos en Quarkus](#configuración-base-de-datos-en-quarkus)
    - [Configuración de una base de datos de forma sincrona](#configuración-de-una-base-de-datos-de-forma-sincrona)
    - [Configuración de una base de datos de forma asincrona](#configuración-de-una-base-de-datos-de-forma-asincrona)
    - [Conexión externa a la base de datos](#conexión-externa-a-la-base-de-datos)
    - [JDBC vs vertx-reactive](#jdbc-vs-vertx-reactive)
5. [Instalación de una base de datos en Docker](#instalación-de-una-base-de-datos-en-docker)
    - [Crear BD y usuario en la imagen de SQL Server](#crear-bd-y-usuario-en-la-imagen-de-sql-server)
6. [Conexión de microservicios de Quarkus en Docker](#conexión-de-microservicios-de-quarkus-en-docker)
    - [Configuración de Docker](#configuración-de-docker)
    - [Construcción de la imagen de Docker para los dos microservicios](#construcción-de-la-imagen-de-docker-para-los-dos-microservicios)
    - [Crear una red en Docker](#crear-una-red-en-docker)
7. [Servicios en la nube](#servicio-en-la-nube)
    - [Oracle Cloud Infrastructure (OCI)](#oracle-cloud-infrastructure-oci)


    

## Descripción

Quarkus es un framework de Java que permite construir aplicaciones de forma rápida y eficiente. Quarkus se basa en la tecnología de GraalVM, que permite compilar aplicaciones Java en binarios nativos, lo que mejora el rendimiento y la eficiencia de las aplicaciones, esta tecnologia es muy util para la construccion de microservicios y aplicaciones reactivas, esta enfocada en programacion en la nube.

<section style="background-color: white;">listo 

<image src="https://quarkus.io/assets/images/home/quarkus_metrics_graphic_bootmem_wide.png">

</section>
    
# Prerrequisitos 
Para poder ejecutar, compilar y empaquetar un proyecto de Quarkus, es necesario tener instalado en el sistema las siguientes herramientas:

## Java
Quarkus requiere de una versión de Java 17 o superior para poder ejecutar y compilar aplicaciones. para este caaso instalaremos el JDK 21 acompañado de GraalVM para poder compilar y empaquetar la aplicacion en un ejecutable nativo.

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

## IDE
Para el desarrollo de aplicaciones con Quarkus, se recomienda utilizar IDE intellij IDEA debido a que es una de las herramientas más completas y con mayor soporte para el desarrollo de aplicaciones con Quarkus. Para instalar intellij IDEA, se debe seguir los siguientes pasos:

1. Descargar el instalador de intellij IDEA Community 2024-3 desde el siguiente enlace: [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/es-es/idea/download/?section=windows).

2. Ejecutar el instalador descargado y seguir los pasos que se indican en el asistente de instalación.

3. Una vez finalizada la instalación, abrir intellij IDEA y ir al a opcion de plugins en el menú de configuración y seleccionar el plugin de Quarkus Tools(2.0.2),Lombok(v.242.23726.38),Docker(v.242.24807.21) y Github Copilot(v.1.5.29.7524).

## Apache Maven
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

## Docker Desktop
Docker Desktop es una herramienta que permite la creación, administración y ejecución de contenedores Docker en sistemas operativos Windows y macOS. Para instalar Docker Desktop, se debe seguir los siguientes pasos:

1. Descargar el instalador de Docker Desktop v.4.36.0 desde el siguiente enlace: [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop/).

2. Ejecutar el instalador descargado y seguir los pasos que se indican en el asistente de instalación.

## Programación reactiva en Quarkus

- La programación reactiva es un paradigma de programación que se basa en la reactividad, es decir, en la capacidad de responder a los eventos de forma eficiente y escalable. En este sentido, la programación reactiva se centra en la creación de aplicaciones que puedan responder a los eventos de forma rápida y eficiente, lo que permite mejorar la experiencia del usuario y la escalabilidad de las aplicaciones.

> [!NOTE]
>
> Para mas informacion sobre todo lo relacionado con la programacion reactiva en quarkus, se puede consultar en la documentacion oficial de quarkus 
    [Programacion reactiva](https://es.quarkus.io/guides/getting-started-reactive).

En Quarkus, para trabajar con programación reactiva, se utilizan Vert.x, Mutiny y Hibernate ORM con Panache las cuales son  dependencias necesarias para trabajar con programacion reactiva en quarkus. A continuación, se explicará brevemente cada uno de ellos.

### vertx 
Es un motor de eventos que permite construir aplicaciones reactivas y escalables. Vert.x se basa en el modelo de actores, lo que significa que cada componente de la aplicación es un actor independiente que puede recibir y enviar mensajes de forma asíncrona. Esto permite construir aplicaciones reactivas que pueden responder a los eventos de forma eficiente y escalable.

#### dependencia necesaria para vertx

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
Es una biblioteca reactiva que permite trabajar  operacioones, eventos y flujos de forma reactiva. Mutiny se basa en el modelo de programación reactiva, lo que significa que las operaciones se realizan de forma asíncrona y se pueden componer de forma eficiente y escalable.

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

# Servicio en la nube

## Oracle Cloud Infrastructure (OCI) 
OCI es una plataforma de nube pública y privada que ofrece una amplia gama de servicios de infraestructura y plataforma como servicio. OCI es una plataforma de nube de alto rendimiento y segura que ofrece una amplia gama de servicios de infraestructura y plataforma como servicio. OCI es una plataforma de nube de alto rendimiento y segura que ofrece una amplia gama de servicios de infraestructura y plataforma como servicio.

### Opciones principales:
- *OCI Container Instances:* 
  - *Uso:* Ejecutar contenedores de Docker en la nube.
  - *Costo:* Pago por uso basado en recursos (CPU, RAM) consumidos por el contenedor.
  - *Ventaja:* Simple y económica si tienes pocos microservicios.
- *OCI Kubernetes Engine (OKE):*
  - *Uso:* Gestionar múltiples contenedores con Kubernetes.
  - *Costo:* El uso de Kubernetes en OCI es gratuito, pero pagarías por las máquinas virtuales (VM) que sirven como nodos de tu clúster.
  - *Ventaja:* Escalabilidad y gestión avanzada si planeas crecimiento.

>[!NOTE]
>
> Para ver la estimación de costos para despliegue de microservicios en la nube, da clic [aquí](https://www.oracle.com/cloud/costestimator.html).
---


