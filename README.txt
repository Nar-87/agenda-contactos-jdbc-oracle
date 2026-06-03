# Agenda de Contactos Java

## Descripción

Aplicación de escritorio desarrollada en Java para la gestión de contactos personales y profesionales.

El proyecto utiliza una arquitectura MVC (Modelo - Vista - Controlador), acceso a base de datos Oracle mediante JDBC y una interfaz gráfica desarrollada con AWT.

## Funcionalidades

### Gestión de contactos

* Crear contactos.
* Modificar contactos existentes.
* Eliminar contactos.
* Consultar información de contactos.

### Tipos de contacto

#### Amigos

Cada contacto amigo almacena:

* Nombre.
* Teléfono.
* Fecha de cumpleaños.

#### Profesionales

Cada contacto profesional almacena:

* Nombre.
* Teléfono.
* Empresa.

### Listados

* Listado alfabético de contactos.
* Listado de cumpleaños ordenado por fecha.
* Visualización de listados en pantalla.
* Exportación de listados a fichero.

### Importación y exportación

* Importación de contactos desde fichero de texto.
* Exportación de listados a fichero.
* Guardado de información desde la aplicación.

### Gestión de errores

La aplicación incorpora excepciones personalizadas para:

* Contactos duplicados.
* Contactos inexistentes.
* Errores durante la importación de datos.
* Formatos de fecha incorrectos.
* Datos inválidos.

### Interfaz gráfica

* Interfaz desarrollada con AWT.
* Confirmación antes de eliminar contactos.
* Confirmación antes de cerrar la aplicación.
* Mensajes informativos para operaciones realizadas correctamente.

## Tecnologías utilizadas

* Java
* AWT
* JDBC
* Oracle Database XE
* SQL Developer
* NetBeans IDE

## Arquitectura

El proyecto sigue el patrón MVC:

### Modelo

Gestiona:

* Entidades de negocio.
* Acceso a base de datos Oracle mediante JDBC.
* Operaciones CRUD.

### Vista

Gestiona:

* Ventana principal.
* Ventanas de confirmación.
* Ventanas de mensajes y listados.

### Controlador

Gestiona:

* Reglas de negocio.
* Validaciones.
* Comunicación entre la vista y el modelo.

## Base de datos

La aplicación utiliza Oracle Database XE.

La conexión se realiza mediante JDBC:

jdbc:oracle:thin:@localhost:1521:xe

Usuario utilizado durante el desarrollo:

SCOTT

## Posibles mejoras futuras

* Migración de AWT a Swing o JavaFX.
* Búsqueda avanzada de contactos.
* Validación visual de formularios.
* Gestión de fotografías de contactos.
* Adaptación a una base de datos configurable.

## Autor

Proyecto desarrollado durante el ciclo formativo de Desarrollo de Aplicaciones Multiplataforma (DAM).

# Agenda de Contactos

## Ventana principal

![Ventana principal](capturas/principal.JPG)

## Nuevo contacto

![Nuevo contacto](capturas/alta.JPG)

## Eliminar contacto

![Eliminar contacto](capturas/eliminar.JPG)

## Confirmar eliminación contacto

![Confirmar eliminación contacto](capturas/eliminar.JPG)









