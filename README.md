# JAVA DEVELOPER - TEST ASSIGNMENT

## Descripción del Proyecto

Esta aplicación de escritorio permite el registro y login de usuarios, cumpliendo con los requisitos especificados en el test
assignment. La aplicación incluye funcionalidades de validación de email y password, registro de nuevos usuarios y recuperación
de contraseñas. Aunque la funcionalidad de envío de emails no está operativa, la aplicación genera y muestra la nueva contraseña
de manera local y la actualiza en la base de datos.

## Funcionalidades

### Registro de Usuario

- **Botón "Registrarse":**
  - Campos: Nombre, Apellido, Email y Password.
  - Validaciones:
    - Email debe contener el carácter '@'.
    - Password debe incluir al menos una letra mayúscula, una letra minúscula, un número, un símbolo y tener un mínimo de 8
      caracteres.

### Ingreso de Usuario

- **Botón "Ingresar":**
  - Campos: Email y Password.
  - Validaciones:
    - Email debe contener el carácter '@'.
    - Password debe incluir al menos una letra mayúscula, una letra minúscula, un número, un símbolo y tener un mínimo de 8
      caracteres.
  - Si los datos ingresados son incorrectos, se muestra el mensaje "Usuario/contraseña incorrectos".

### Recuperación de Contraseña

- **Botón "Olvidé mi clave":**
  - Elimina la clave del usuario, genera una nueva contraseña y la actualiza en la base de datos.
  - La nueva contraseña se muestra de manera local (la funcionalidad de envío de email no está operativa).

## Tecnologías Utilizadas

- Java v17
- Spring Boot
- SQL
- Interfaz gráfica con Java Swing.

## Instalación y Ejecución

1. Clonar el repositorio.
2. Configurar la base de datos SQL.
3. Ejecutar la aplicación desde tu entorno de desarrollo preferido.
4. Utilizar la interfaz gráfica para registrar y gestionar usuarios.

## Notas Adicionales

- La aplicación guarda toda la información en una base de datos.
- La generación de contraseñas y su actualización en la base de datos funciona de manera local.
