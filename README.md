# README de la Aplicación de Gestión Ferroviaria (CRUD)

## Introducción

Este documento describe una aplicación de consola Java diseñada para gestionar información ferroviaria. La aplicación permite realizar operaciones **CRUD** (Crear, Leer, Actualizar, Eliminar) sobre diversas entidades relacionadas con el sistema ferroviario, utilizando Hibernate para la persistencia de datos.

El objetivo principal de esta aplicación es demostrar la implementación de operaciones CRUD básicas en un contexto de gestión de entidades, tales como compañías ferroviarias, trenes, trayectos y estaciones. La interacción con la aplicación se realiza a través de una interfaz de línea de comandos, ofreciendo un menú sencillo para navegar y ejecutar las diferentes acciones.

## Funcionalidades Principales: Operaciones CRUD

La aplicación se centra en las siguientes operaciones CRUD, aplicables a las entidades principales del sistema ferroviario:

### Crear (Añadir entidad)

Permite añadir nuevas instancias de las siguientes entidades a la base de datos:

*   **Compañía:** Permite crear una nueva compañía ferroviaria, solicitando el nombre y la opción de añadir trenes asociados durante la creación.
*   **Tren:** Permite crear un nuevo tren, solicitando el nombre, la capacidad y asociándolo a una compañía existente o creando una nueva compañía al momento. También ofrece la opción de añadir trayectos asociados al tren durante la creación.
*   **Trayecto:** Permite crear un nuevo trayecto, solicitando nombre, hora de llegada, hora de salida, precio y la opción de añadir trenes y estaciones asociadas al trayecto.
*   **Estación:** Permite crear una nueva estación, solicitando el nombre y la opción de añadir trayectos asociados a la estación durante la creación.

**Cómo usar:**

1.  Seleccione la opción **"a) Añadir entidad"** en el menú principal.
2.  Se le presentará un submenú para elegir el tipo de entidad que desea añadir (Compañía, Tren, Trayecto, Estación).
3.  Siga las instrucciones en pantalla, introduciendo la información solicitada para cada campo de la entidad.

### Leer (Consultar entidad)

Permite consultar información de las entidades almacenadas en la base de datos. Ofrece dos modos de consulta:

*   **Consulta específica por ID:** Permite buscar una entidad concreta proporcionando su identificador único (ID).
*   **Consulta de todas las entidades:** Permite listar todas las instancias de una entidad en particular.

Las entidades que se pueden consultar son:

*   **Compañía**
*   **Tren**
*   **Trayecto**
*   **Estación**

**Cómo usar:**

1.  Seleccione la opción **"b) Consultar entidad"** en el menú principal.
2.  Se le preguntará si desea realizar una consulta específica por ID o listar todas las entidades.
3.  Si elige la consulta específica, se le pedirá el ID de la entidad.
4.  A continuación, se le presentará un submenú para elegir el tipo de entidad que desea consultar (Compañía, Tren, Trayecto, Estación).
5.  La aplicación mostrará la información de la entidad o la lista de entidades según su elección.

### Actualizar (Actualizar entidad)

Permite modificar la información de una entidad existente en la base de datos. Para actualizar una entidad, es necesario proporcionar su ID y luego los nuevos valores para los campos que se deseen modificar.

Las entidades que se pueden actualizar son:

*   **Compañía:** Permite modificar el nombre de la compañía.
*   **Tren:** Permite modificar el nombre y la capacidad del tren.
*   **Trayecto:** Permite modificar el nombre, la hora de llegada, la hora de salida y el precio del trayecto.
*   **Estación:** Permite modificar el nombre de la estación.

**Cómo usar:**

1.  Seleccione la opción **"d) Actualizar entidad"** en el menú principal.
2.  Se le pedirá que introduzca el ID de la entidad que desea actualizar.
3.  Luego, se le presentará un submenú para elegir el tipo de entidad que va a actualizar (Compañía, Tren, Trayecto, Estación).
4.  Siga las instrucciones en pantalla, introduciendo los nuevos valores para los campos que desee modificar de la entidad seleccionada.

### Eliminar (Eliminar entidad)

Permite eliminar una entidad existente de la base de datos. Para eliminar una entidad, es necesario proporcionar su ID.

Las entidades que se pueden eliminar son:

*   **Compañía**
*   **Tren**
*   **Trayecto**
*   **Estación**

**Cómo usar:**

1.  Seleccione la opción **"c) Eliminar entidad"** en el menú principal.
2.  Se le pedirá que introduzca el ID de la entidad que desea eliminar.
3.  A continuación, se le presentará un submenú para elegir el tipo de entidad que desea eliminar (Compañía, Tren, Trayecto, Estación).
4.  La aplicación procederá a eliminar la entidad especificada de la base de datos.

### Cantidad de Entidades (Cantidad entidades)

Permite obtener el número total de instancias de cada tipo de entidad almacenadas en la base de datos.

Las entidades de las que se puede contar las instancias son:

*   **Compañía**
*   **Tren**
*   **Trayecto**
*   **Estación**

**Cómo usar:**

1.  Seleccione la opción **"e) Cantidad entidades"** en el menú principal.
2.  Se le presentará un submenú para elegir el tipo de entidad de la cual desea conocer la cantidad (Compañía, Tren, Trayecto, Estación).
3.  La aplicación mostrará el número total de entidades del tipo seleccionado.

### Salir

Permite finalizar la ejecución de la aplicación de manera segura.

**Cómo usar:**

1.  Seleccione la opción **"f) Salir"** en el menú principal.
2.  La aplicación mostrará un mensaje de despedida y se cerrará.

## Entidades del Sistema

A continuación, se describen brevemente las entidades principales gestionadas por la aplicación:

*   **Compañía (Companyia):** Representa una empresa ferroviaria. Cada compañía tiene un nombre y puede operar varios trenes.
*   **Tren (Tren):** Representa un vehículo ferroviario. Cada tren tiene un nombre, una capacidad y pertenece a una compañía. Puede realizar varios trayectos.
*   **Trayecto (Trajecte):** Representa una ruta o recorrido entre estaciones. Cada trayecto tiene un nombre, hora de llegada, hora de salida, precio y puede ser realizado por varios trenes y pasar por varias estaciones.
*   **Estación (Estacio):** Representa un punto de parada en la red ferroviaria. Cada estación tiene un nombre y puede formar parte de varios trayectos.

## Cómo Ejecutar la Aplicación

Para ejecutar esta aplicación, necesita tener instalado lo siguiente:

*   **JDK (Java Development Kit):** Versión 8 o superior.
*   **Maven:** Para la gestión de dependencias y la construcción del proyecto (opcional si gestiona las dependencias manualmente).
*   **Base de datos:**  La aplicación está configurada para usar una base de datos relacional (la configuración específica de la base de datos se encuentra en el archivo de configuración de Hibernate, `HibernateUtil.java` no incluido en este fragmento de código).

**Pasos para ejecutar:**

1.  **Compilar la aplicación:** Si está utilizando Maven, puede compilar el proyecto con el comando `mvn compile`. Si no, compile manualmente los archivos `.java`.
2.  **Ejecutar la aplicación:** Ejecute la clase `Main.java` que contiene el método `main`. Puede hacerlo desde su IDE o utilizando la línea de comandos: `java Main`.
3.  **Interactuar con el menú:** Una vez que la aplicación se inicie, se mostrará un menú en la consola. Siga las instrucciones del menú para realizar las operaciones CRUD deseadas.

## Dependencias

La aplicación utiliza las siguientes dependencias principales:

*   **Hibernate ORM:** Para la gestión de la persistencia de datos y la interacción con la base de datos.
*   **Java.io:** Para la lectura de la entrada del usuario desde la consola.

Asegúrese de tener configuradas correctamente las dependencias de Hibernate en su proyecto (por ejemplo, a través de Maven o incluyendo los JARs necesarios en el classpath).

## Manejo de Errores

La aplicación incluye un manejo básico de errores para situaciones comunes como errores de entrada/salida (E/S), errores de formato numérico y errores relacionados con Hibernate (como excepciones de transacción). Cuando ocurre un error, se muestra un mensaje descriptivo en la consola y, en algunos casos, se imprime la traza de la excepción para facilitar la depuración.

Es importante revisar la consola para cualquier mensaje de error durante la ejecución de la aplicación. Para una aplicación más robusta, se recomienda implementar un sistema de logging más completo.

## Conclusión

Esta aplicación proporciona una base funcional para la gestión de información ferroviaria mediante operaciones CRUD utilizando Hibernate. Es un ejemplo práctico de cómo implementar interacciones básicas con una base de datos desde una aplicación de consola Java. Puede ser extendida y mejorada para añadir funcionalidades más complejas y una interfaz de usuario más amigable en el futuro.