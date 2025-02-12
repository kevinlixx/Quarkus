
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

      <center> <img src='./imgs/img_configExtensiones.png' style= 'width: 80%; height: 50%;'></img> </center>



## Estructura de un proyecto Quarkus
Al momento de crear un proyecto Quarkus, se generan una serie de carpetas y archivos que conforman la estructura del proyecto. A continuacion se muestra una estructura basica de un proyecto Quarkus

<center><img src='./imgs/ims_estructuraQuarkus.png' style= 'width: 80%; height: 50%;'></img> </center>

#### .mvn
Esta carpeta contiene los archivos de configuración de Maven, como el archivo `wrapper.properties` que se utiliza para configurar el wrapper de Maven. El wrapper de Maven es una herramienta que permite ejecutar Maven sin necesidad de instalarlo en el sistema.

#### src
Esta carpeta contiene el código fuente del proyecto, dividido en diferentes subcarpetas:
- `main`: contiene el código fuente principal del proyecto, como las clases Java y los recursos de la aplicación.
  - `java`: contiene las clases Java del proyecto.
  - `resources`: contiene los recursos de la aplicación, como archivos de configuración, propiedades y plantillas.
  - `docker`: contiene los archivos de configuración de Docker para la creación de contenedores.
    - `Dockerfile.jvm`: archivo de configuración de Docker para la creación de un contenedor con la JVM.
    - `Dockerfile.legacy-jar`: archivo de configuración de Docker para la creación de un contenedor con un JAR legado el cual es un archivo que contiene el código fuente de la aplicación y las dependencias.
    - `Dockerfile.native`: archivo de configuración de Docker para la creación de un contenedor con un ejecutable nativo que se compila con GraalVM.
    - `Dockerfile.native-micro`: archivo de configuración de Docker para la creación de un contenedor el cual sera mas pequeño y ligero que el contenedor de Dockerfile.native debido a que solo contiene el ejecutable nativo.
  
  - `test`: contiene el código fuente de las pruebas unitarias y de integración del proyecto.
  
#### target
Esta carpeta contiene los archivos generados por Maven durante el proceso de compilación y empaquetado del proyecto

#### Pom.xml
Este archivo es el archivo de configuración de Maven y contiene la información del proyecto, como las dependencias, los plugins y las configuraciones de Maven.

#### README.md
todo proyecto de quarkus tiene un archivo README.md el cual contiene algunas instrucciones donde se explica como ejecutar el proyexto en modo desarrollo y como empaquear el proyecto en cada una de las diferentes formas de empaquetado.

## Ejemplo de un Microservicio en Quarkus
Ahora crearemos un microservicio en Quarkus que se conectará a una base de datos y expondrá un servicio REST para realizar operaciones CRUD sobre los datos de la base de datos. Para ello, seguiremos los siguientes pasos:

### Agregar dependencias 
lo primero que haremos una vez creado el proyecto es agregar las dependencias necesarias para trabajar con una base de datos en quarkus, en este caso trabajaremos con las dependencias de [hibernate orm panache](https://quarkus.io/guides/hibernate-orm-panache), [vertx](https://quarkus.io/guides/vertx), [mutiny](https://quarkus.io/guides/mutiny-primer) y [REST](https://quarkus.io/guides/rest)

### Creacion de las entidades

Crearemos una entidad que represente los datos de la base de datos, en este caso crearemos una carpeta llamada controlador sen la carpeta main/java y crearemos una clase llamada Product.java que representará los datos de un producto. La clase Product.java debe tener las siguientes anotaciones:
```java
package com.avvillas.controlador;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Lombok genera los getter y setter
public class Product extends PanacheEntity { // PanacheEntity es una clase que nos permite hacer operaciones CRUD gracias a Hibernate ORM Panache nos facilita el trabajo con la base de datos

    @Transient // indica que el campo no se mapea a la base de datos y se usa solo para almacenar datos de manera temporal
    private Long id;

    
    @ManyToOne// muchos productos pueden pertenecer a un cliente
    @JoinColumn(name = "customer", referencedColumnName = "id") // indica que la columna customer de la tabla product es una clave foranea que referencia a la columna id de la tabla customer

    @JsonBackReference // evita la recursividad en la serializacion de los datos tomando a customer como el padre y product como el hijo
    private Customer customer; // un producto pertenece a un cliente
    @Column // indica que el campo es una columna de la tabla product
    private Long product; // codigo del producto

    // almacenaran los datos del producto de manera temporal
    @Transient
    private String name;
    @Transient
    private String code;
    @Transient
    private String description;
}
```
> [!NOTE]
>  no debemos crear los getter y setter de los atributos de la clase Product.java ya que lombok se encarga de generarlos automaticamente y el panaheEntity nos permite hacer operaciones CRUD sobre la base de datos sin necesidad de crear un repositorio para la entidad 

Ahora crearemos una entidad para el cliente que sera el padre de la entidad product, para ello crearemos una clase llamada Customer.java en la carpeta controlador en la carpeta main/java que representará los datos de un cliente. La clase Customer.java debe tener las siguientes anotaciones:

```java
package com.avvillas.controlador;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data // Lombok genera los getter y setter

public class Customer extends PanacheEntity { // PanacheEntity es una clase que nos permite hacer operaciones CRUD

    //Ahora vamos a definir los campos de la tabla
    private String code;
    private String accountNumber;
    private String names;
    private String surname;
    private String phone;
    private String address;
    //Ahora vamos a definir la relación con la tabla Product
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.ALL},fetch = FetchType.EAGER) // un cliente puede tener varios productos
    @JsonManagedReference
    private List<Product> products; // lista de productos que tiene el cliente

    //Ahora vamos a definir el metodo toString, el cual nos permite imprimir los datos de un cliente
    @Override
    public String toString() {
        return "Customer{" +
                "code='" + code + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", names='" + names + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
```
### Configuración base de datos en Quarkus

En quarkus se puede trabajar con diferentes bases de datos como mysql, postgresql, sqlserver, etc; hay dos formas de trabajar con una base de datos en quarkus de forma sincrona y asincrona, A continuacion se explicara como configurar una base de datos en quarkus.

#### Configuración de una base de datos de forma sincrona

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

#### Configuración de una base de datos de forma asincrona

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

#### Conexión externa a la base de datos
Para conectar quarkus con un db donde su properties estan en una ruta especifica se debe agregar la siguiente propiedad en el archivo aplication.properties

``` properties
quarkus.cofig.locations=classpath:application.properties, file:/path/to/properties
```
el classpath:application.properties es la ruta por defecto de las propiedades de la aplicacion y el file:/path/to/properties es la ruta donde se encuentra el archivo de propiedades de la base de datos


> [!NOTE]
>
> hay diferentes configuraciones para trabajar con una base de datos de forma asincrona y local o en la nube

#### JDBC vs vertx-reactive

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
docker start sqlserver2017 // para iniciar la imagen de sqlserver

docker cp db.sql sqlserver2017:/db.sql // copiar el archivo db.sql a la imagen de sqlserver

docker exec -it sqlserver2017 /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'YourStrong!Passw0rd' -i /db.sql // ejecutar el archivo db.sql en la imagen de sqlserver
```

En nuestro caso el archivo db.sql contiene las siguientes instrucciones

para nuestro mciroserivicio de product se va a crear una base de datos llamada product_db y un usuario llamado product_user con la contraseña Pr0duct!pass y se le van a otorgar permisos de db_owner

```sql

-- Crear la base de datos product_db
CREATE DATABASE product_db;
GO

-- Usar la base de datos product_db
USE product_db;
GO

-- Crear el usuario product_user
CREATE LOGIN product_user WITH PASSWORD = 'Pr0duct!pass';
GO
CREATE USER product_user FOR LOGIN product_user;
GO

-- Otorgar permisos al usuario product_user
ALTER ROLE db_owner ADD MEMBER product_user;
GO

-- Crear la tabla Product en product_db
CREATE TABLE Product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    code NVARCHAR(50) NOT NULL,
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(255)
);
GO

```

Para nuestro microservicio de customer se va a crear una base de datos llamada customer_db y un usuario llamado customer_user con la contraseña CusT0mer!pass y se le van a otorgar permisos de db_owner.

```sql
-- Create the database
CREATE DATABASE customer_db;
GO

-- Use the database
USE customer_db;
GO

-- Create the user
CREATE LOGIN customer_user WITH PASSWORD = 'Cust0mer!Pass';
CREATE USER customer_user FOR LOGIN customer_user;
GO

-- Grant permissions to the user
ALTER ROLE db_owner ADD MEMBER customer_user;
GO

-- Create the Customer table
CREATE TABLE Customer (
    id BIGINT IDENTITY PRIMARY KEY, -- PanacheEntity uses 'id' as the primary key
    code VARCHAR(255) NOT NULL,
    accountNumber VARCHAR(255) NOT NULL,
    names VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);
GO

-- Create the Product table
CREATE TABLE Product (
    id BIGINT IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);
GO

```


### Creacion del modelo de datos
Ahora crearemos un modelo de datos que represente los datos de la base de datos, en este caso crearemos una carpeta llamada modelo en la carpeta main/java y crearemos una clase llamada clsCustomerApi.java que representará los datos de un cliente. La clase clsCustomerApi.java debe tener las siguientes anotaciones:

```java
package com.avvillas.modelo;

import com.avvillas.controlador.Customer;
import com.avvillas.controlador.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


import java.util.ArrayList;
import java.util.List;

import static io.smallrye.mutiny.helpers.spies.Spy.onFailure;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NO_CONTENT;


@Slf4j
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClsCustomerApi {
    @Inject
    Vertx vertx;

    @ConfigProperty(name = "product.url")
    String productServiceUrl;

    private WebClient webClient;

    @PostConstruct
    void initialize() {
        this.webClient = WebClient.create(vertx,
                new WebClientOptions().setDefaultHost(productServiceUrl)
                        .setSsl(false).setTrustAll(true));
    }


    // busca todos los registros de la tabla el names es el campo por el cual se ordena
    @GET
    public Uni<List<PanacheEntityBase>> list() { // nos busca todos los registros de la tabla el names es el campo por el cual se ordena
        return Customer.listAll(Sort.by("names")) // se ordena por el campo name
                .onItem().invoke(customers -> log.info("Retrieved customers: " + customers)) // si es exitoso se imprime el resultado
                .onFailure().invoke(e -> log.error("Error retrieving customers", e)); // si hay un error se imprime el error
    }

    // busca un registro por el id
    @GET
    @Path("/{Id}")
    public Uni<PanacheEntityBase> getById(@PathParam("Id") Long Id) { // busca un registro por el id
        return Customer.findById(Id) // busca un registro por el id
                .onItem().ifNotNull().invoke(customer -> log.info("Retrieved customer: " + customer)) // si es exitoso se imprime el resultado
                .onItem().ifNull().failWith(() -> new WebApplicationException("Customer not found", NOT_FOUND)) // si es nulo se imprime un mensaje de error
                .onFailure().invoke(e -> log.error("Error retrieving customer", e)); // si hay un error se imprime el error
    }

    // agrega un registro
    @POST
    public Uni<Response> add(Customer c) { // agrega un registro
        if (c == null) { // si el registro es nulo
            log.error("Customer is null");
            return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).entity("Customer cannot be null").build()); // nos indica que el registro no puede ser nulo
        }
        log.info("Adding customer: " + c); // si no es nulo se imprime el registro
        return Panache.withTransaction(c::persist) // se persiste el registro, los :: son para referenciar un metodo en este caso persist
                .onItem().transform(inserted -> Response.status(Response.Status.CREATED).build()) // si es exitoso indica que se creo el registro
                .onFailure().invoke(e -> log.error("Error persisting customer", e)); // si hay un error se imprime el error
    }


    // Actualiza un registro
    @PUT
    @Path("/{Id}")
    public Uni<Response> update(@PathParam("Id") Long Id, Customer c) { // Actualiza un registro
        return Panache
                .withTransaction(() -> Customer.<Customer>findById(Id) // busca el registro por el id
                        .onItem().ifNotNull().invoke(customer -> { // si es diferente de nulo
                            customer.setAccountNumber(c.getAccountNumber()); // se actualiza el campo accountNumber
                            customer.setNames(c.getNames()); // se actualiza los campos
                            customer.setSurname(c.getSurname());
                            customer.setPhone(c.getPhone());
                            customer.setAddress(c.getAddress());
                            customer.setProducts(c.getProducts());
                        })
                )
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.status(NOT_FOUND)::build)
                .onFailure().invoke(e -> log.error("Error updating customer", e)); // se imprime el error
    }

    // Elimina un registro
    @DELETE
    @Path("/{Id}")
    public Uni<Response> delete(@PathParam("Id") Long Id) { // Elimina un registro
        return Panache.withTransaction(() -> Customer.deleteById(Id)) // se elimina el registro por el id
                .onItem().transform(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.status(NOT_FOUND).build())
                // el ? es un operador ternario si es verdadero se imprime el mensaje de que se elimino el registro
                // el : es un operador ternario si es falso se imprime el mensaje de que no se encontro el registro
                .onFailure().invoke(e -> log.error("Error deleting customer", e)); // si hay un error se imprime el error
    }
    @GET
    @Path("/{Id}/product")
    public Uni<Customer> getByIdProduct(@PathParam("Id") Long Id) {
        log.info("Fetching customer with ID: " + Id);
        return Uni.combine().all().unis(getCustomerReactive(Id), getAllProducts())
                .asTuple()
                .map(tuple -> {
                    Customer customer = tuple.getItem1();
                    List<Product> products = tuple.getItem2();
                    log.info("Customer: " + customer);
                    log.info("Products: " + products);
                    customer.getProducts().forEach(product -> {
                        products.forEach(p -> {
                            log.info("Ids are: " + product.getProduct() + " = " + p.getId());
                            if (product.getProduct().equals(p.getId())) {
                                product.setName(p.getName());
                                product.setDescription(p.getDescription());
                            }
                        });
                    });
                    return customer;
                })
                .onFailure().retry().atMost(3) // Retry up to 3 times on failure
                .onFailure().invoke(e -> log.error("Error fetching customer or products", e))
                .onFailure().recoverWithItem(e -> {
                    log.error("Recovering from error: ", e);
                    Customer errorCustomer = new Customer();
                    errorCustomer.setNames("Error fetching: " + e.getMessage());
                    return errorCustomer;
                });
    }


    @GET
    @Path("/products")
    public Uni<List<Product>> getAllProducts(){
        return webClient.get(8081, "localhost", "/product").send()
                .onFailure().invoke(res -> log.error("Error recuperando productos ", res))
                .onItem().transform(res -> {
                    List<Product> lista = new ArrayList<>();
                    JsonArray objects = res.bodyAsJsonArray();
                    objects.forEach(p -> {
                        log.info("See Objects: " + objects);
                        ObjectMapper objectMapper = new ObjectMapper();
                        // Pass JSON string and the POJO class
                        Product product = null;
                        try {
                            product = objectMapper.readValue(p.toString(), Product.class);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        lista.add(product);
                    });
                    return lista;
                });
    }

    private Uni<Customer> getCustomerReactive(Long Id){
        return Customer.findById(Id);
    }

}
```

> [!NOTE]
> Como se puede observar en el codigo anterior se esta trabajando con una base de datos de forma asincrona y se esta haciendo uso de mutiny para trabajar con los datos de la base de datos de forma reactiva y mediante el uso de vertx se esta haciendo una peticion a un microservicio de product para obtener los datos de los productos


### dependencias adicionales
-Swagger-ui permite visualizar y probar los servicios REST de la aplicación. Para habilitar Swagger-ui, se debe agregar la siguiente dependencia en el archivo pom.xml

```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-smallrye-openapi</artifactId>
</dependency>
```

- Para habilitar la generación de documentación OpenAPI, se debe agregar la siguiente propiedad en el archivo application.properties

```properties 

# Swagger UI configuration 
quarkus.swagger-ui.path=/swagger-ui # ruta de acceso a swagger-ui
quarkus.swagger-ui.always-include=true # indica que siempre se incluya swagger-ui
mp.openapi.extensions.smallrye.info.title=Customer API # titulo de la documentacion
%dev.mp.openapi.extensions.smallrye.info.title=Customer API(development) # titulo de la documentacion en modo desarrollo
%test.mp.openapi.extensions.smallrye.info.title=Customer API (test) # titulo de la documentacion en modo test
mp.openapi.extensions.smallrye.info.version=1.0.1 # version de la documentacion
mp.openapi.extensions.smallrye.info. description=Microservicio de clientes # descripcion de la documentacion 

```

>[!NOTE]
> Para obtener más información sobre la documentacion de una api en quarkus, haz clic en [Documentacion de swagger-ui](https://quarkus.io/guides/openapi-swaggerui).


### Ejecutar el microservicio en modo desarrollo
Quarkus nos permite nuestros servicios en modo desarrollo,el cual nos permite realizar cambios en el código fuente y ver los cambios reflejados en tiempo real sin necesidad de reiniciar el servidor. Para ejecutar el microservicio en modo desarrollo, se debe seguir los siguientes pasos:

1. Abrir una terminal y ejecutar el siguiente comando para iniciar el microservicio en modo desarrollo, tener en cuenta que el microservicio debe estar en la carpeta raiz del proyecto
   
```bash
./mvnw compile quarkus:dev
```
una vez escrita esta linea de codigo aparecera lo siguiente en la terminal

![alt text](image.png)

iremos a la ruta http://localhost:8080/q/dev-ui/ para visualizar la interfaz en modo desarrollo


![alt text](image-1.png)

y para visualizar que todo este funcionando correctamente iremos a la ruta http://localhost:8080/swagger-ui/ para visualizar la documentacion de la api

![alt text](image-2.png)


> [!IMPORTANT]
> se debe teenr ejecutando el contenedor donde esta la base de datos para que el microservicio pueda conectarse a la base de datos si no no correra el microservicio
>


### Empaquetar el microservicio
Una vez que se hayan realizado las pruebas en modo desarrollo, se debe empaquetar el microservicio para su despliegue en un entorno de producción. Para empaquetar el microservicio, se debe seguir los siguientes pasos:

1. en el pom.xml se debe agregar la siguiente propiedad para que el microservicio se empaquete en u nejecutable de graalvm

```xml
<quarkus.package.type>native</quarkus.package.type>
<quarkus.native.container-build>true</quarkus.native.container-build>
```

2. Ejecutar el siguiente comando para empaquetar el microservicio en un ejecutable nativo de GraalVM

```bash
./mvnw package -Dnative
```

> [!NOTE]
> Este comando empaqueta el microservicio en un ejecutable nativo de GraalVM, el cual es un ejecutable ligero y rápido que no requiere una JVM para ejecutarse.

### Cofiguracion de docker

Para conectar los microservicios de quarkus en docker se debe seguir los siguientes pasos

```bash
docker network create my_network // crea una red en docker para que los contenedores puedan comunicarse entre ellos


docker run -i -p 8080:8080 --network my_network --name customer quarkus/code-with-quarkus // corre el contenedor del microservicio customer

docker run -i -p 8081:8081 --network my_network --name product quarkus/code-with-quarkus // corre el contenedor del microservicio product

docker network inspect my_network // confirma que los contenedores esten conectados a la red
``` 
Antes de crear los contenedores se debe crear una red en docker para que los contenedores puedan comunicarse entre ellos;En este caso se creo dos contenedores uno para el microservicio product y otro para el microservicio customer


>[!NOTE]
> esta informacion es un ejemplo para la creacion de un dockerfile.native-micro para un microservicio, para mas informacion de los diferentes tipos de dockerfile se puede consultar en la documentacion de quarkus

#### Crear una red en docker
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

