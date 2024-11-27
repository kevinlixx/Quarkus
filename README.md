## Tabla de Contenidos
1. [Programación reactiva en Quarkus](#1-programación-reactiva-en-quarkus)
    - [Vert.x](#vertx)
    - [Mutiny](#mutiny)
    - [Hibernate ORM Panache](#hibernate-orm-panache)
2. [Configuración base de datos en Quarkus](#2-configuracion-base-de-datos-en-quarkus)
    - [Configuración de una base de datos de forma sincrona](#configuracion-de-una-base-de-datos-de-forma-sincrona)
    - [Configuración de una base de datos de forma asincrona](#configuracion-de-una-base-de-datos-de-forma-asincrona)
    - [Conexión externa a la base de datos](#conexion-externa-a-la-base-de-datos)
    - [JDBC vs vertx-reactive](#jdbc-vs-vertx-reactive)
3. [Instalación de una base de datos en Docker](#4-instalación-de-una-base-de-datos-en-docker)
    - [Crear la BD y usuario en la imagen de SQL Server](#crear-la-bd-y-usuario-en-la-imagen-de-sqlserver)
4. [Conexión de microservicios de Quarkus en Docker](#4-conexion-de-microservicios-de-quarkus-en-docker)
    - [Configuración de Docker](#Cofiguracion-de-docker)
    - [Construcción de la imagen de Docker para los dos microservicios](#1-contruccion-de-la-imagen-de-docker-para-los-dos-microservicios)
    - [Crear una red en Docker](#2-crear-una-red-en-docker)
5. [Estimación de Costos para Despliegue de Microservicios en la Nube](#estimación-de-costos-para-despliegue-de-microservicios-en-la-nube)
    - [Oracle Cloud Infrastructure (OCI)](#1-oracle-cloud-infrastructure-oci)
    - [Amazon Web Services (AWS)](#2-amazon-web-services-aws)
    - [Microsoft Azure](#3-microsoft-azure)
    
# <center> Quarkus</center>
## 1. definicion de Quarkus

Quarkus es un framework de Java que permite construir aplicaciones de forma rápida y eficiente. Quarkus se basa en la tecnología de GraalVM, que permite compilar aplicaciones Java en binarios nativos, lo que mejora el rendimiento y la eficiencia de las aplicaciones, esta tecnologia es muy util para la construccion de microservicios y aplicaciones reactivas, esta enfocada en programacion en la nube.

<section style="background-color: white;">listo 

<image src="https://quarkus.io/assets/images/home/quarkus_metrics_graphic_bootmem_wide.png">

</section>

## 1. Programación reactiva en Quarkus

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

# 2. Configuracion base de datos en quarkus

En quarkus se puede trabajar con diferentes bases de datos como mysql, postgresql, sqlserver, etc; hay dos formas de trabajar con una base de datos en quarkus de forma sincrona y asincrona, A continuacion se explicara como configurar una base de datos en quarkus.

## Configuracion de una base de datos de forma sincrona

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

## Configuracion de una base de datos de forma asincrona

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

## conexion externa a la base de datos
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

# 4. instalación de una base de datos en docker

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

### Crear la bd y usuario en la imagen de sqlserver
para crear la base de datos y el usuario en la imagen de sqlserver se puede hacer de dos formas, la primera es conectandose a la imagen de sqlserver y la segunda es copiando un archivo .sql a la imagen de sqlserver, en este caso se va a realizar la segunda forma

```bash
docker start sqlserver2017

docker cp db.sql sqlserver2017:/db.sql 

docker exec -it sqlserver2017 /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'YourStrong!Passw0rd' -i /db.sql

docker run -i --rm -p 8080:8080 --name customer quarkus/code-with-quarkus
```

# 4. Conexion de microservicios de quarkus en docker

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

en este caso se creo dos contenedores uno para el microservicio product y otro para el microservicio customer

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
docker tag usuario/sqlserver2017:v1.0 usuario/sqlserver2017:v1.0 

```

para probar que la imagen se haya creado correctamente se puede hacer de la siguiente manera

```bash
docker pull tuusuario/tuimagen:v1.0
```

# Estimación de Costos para Despliegue de Microservicios en la Nube

---

## 1. Oracle Cloud Infrastructure (OCI) 
OCI ofrece una ventaja en costos, especialmente para proyectos pequeños o pruebas.

### Opciones principales:
- *OCI Container Instances:* 
  - *Uso:* Ejecución directa de contenedores sin necesidad de gestionar clústeres Kubernetes.
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

## 2. Amazon Web Services (AWS)
AWS es ideal si buscas escalabilidad y muchas opciones de integración.

### Opciones principales:

- *Amazon ECS (Elastic Container Service):*
  - *Uso:* Gestiona tus contenedores de manera sencilla sin configurar clústeres.
  - *Costo:* Pago por los recursos asignados a los contenedores (CPU, RAM) o por la instancia EC2 que los ejecute.
  - *Ventaja:* Integración fácil con otros servicios de AWS.

- *Amazon EKS (Elastic Kubernetes Service):*
  - *Uso:* Despliegas contenedores en un clúster Kubernetes.
  - *Costo:* cobra por hora por el clúster más los costos de las máquinas EC2.
  - *Ventaja:* Ideal para despliegues complejos.

- *Fargate (Serverless para ECS o EKS):*
  - *Uso:* Ejecuta contenedores sin gestionar servidores.
  - *Costo:* Basado en recursos asignados por contenedor.
  - *Ventaja:* Gran simplicidad, pero puede ser más caro si necesitas recursos constantes.

> [!NOTE]
>
> Para ver la estimación de costos para despliegue de microservicios en la nube, da clic [aquí]().
---

### 3. *Microsoft Azure*  
Azure es una opción fuerte si usas herramientas integradas como DevOps o buscas soporte empresarial.

#### Opciones principales:
- *Azure Container Instances (ACI):*
  - *Uso:* Ejecución rápida de contenedores individuales.
  - *Costo:* Pago por segundo basado en CPU y RAM.
  - *Ventaja:* Simplicidad y agilidad.

- *Azure Kubernetes Service (AKS):*
  - *Uso:* Gestión avanzada de contenedores con Kubernetes.
  - *Costo:* Gratis por el clúster; solo pagas las VMs usadas como nodos.
  - *Ventaja:* Escalabilidad y gestión robusta.

- *Azure App Service (para contenedores):*
  - *Uso:* Despliegas directamente tus contenedores en un entorno PaaS.
  - *Costo:* Pago por las instancias del plan App Service.
  - *Ventaja:* Sin necesidad de administrar infraestructura.

> [!NOTE]
>
> Para ver la estimación de costos para despliegue de microservicios en la nube, da clic [aquí](https://azure.microsoft.com/en-us/pricing/calculator/).

---
