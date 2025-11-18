# EmployeeWs
Servicio de Gestión de Empleados (employee-service)
📜 Descripción
Este proyecto es un microservicio backend, construido con Spring Boot y Java 17, que gestiona las operaciones CRUD para una entidad de "Empleado". El servicio está diseñado siguiendo los principios de Arquitectura Hexagonal para separar la lógica de negocio de la infraestructura, y está enfocado en ser resiliente, escalable y seguro (DevSecOps).

El paquete base del proyecto es com.mx.bankx.

✨ Características Principales
API RESTful completa para la gestión de empleados (CRUD).

Búsqueda avanzada por nombre.

Arquitectura Hexagonal (Puertos y Adaptadores).

Seguridad basada en Tokens JWT para todos los endpoints.

Perfiles de Spring para entornos de dev (H2) y prod (Oracle).

Documentación de API automática con OpenAPI (Swagger).

Manejo de excepciones centralizado.

Observabilidad con Spring Boot Actuator (/health).

Contenerización con Docker.

🛠️ Stack Tecnológico
Core: Java 17, Spring Boot 2.7.18, Maven

Datos: Spring Data JPA

Bases de Datos: Oracle (producción), H2 (desarrollo/pruebas)

Seguridad: Spring Security, JWT (jjwt)

Documentación: SpringDoc OpenAPI (Swagger)

Pruebas: JUnit 5, Mockito

Mapeo: MapStruct

Contenedores: Docker

🚀 Puesta en Marcha (Ambiente Local)
Sigue estos pasos para configurar tu ambiente de desarrollo local.

1. Prerrequisitos
Asegúrate de tener instalado el siguiente software en tu sistema:

JDK 17 (Amazon Corretto, Eclipse Temurin o similar)

Apache Maven 3.8+

Git

Docker (Recomendado, para un despliegue sencillo)

(Opcional) Cliente de Oracle: Si deseas conectarte a una base de datos Oracle (perfil prod).

2. Clonar el Repositorio
Bash

git clone <url-del-repositorio>
cd employee-service
3. Configuración (Perfiles)
Este proyecto utiliza perfiles de Spring Boot para gestionar las configuraciones de la base de datos.

Perfil dev (Por defecto para desarrollo)
Base de datos: H2 en memoria.

Acción: No requiere configuración adicional. El esquema se crea automáticamente (ddl-auto: update).

Consola H2: Disponible en http://localhost:8080/h2-console

Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:mem:employeedb

User Name: sa

Password: (dejar en blanco)

Perfil prod (Para producción)
Base de datos: Oracle.

Acción: Requiere que la base de datos Oracle esté disponible y que el esquema exista.

Script DDL: Debes ejecutar el script SQL de Oracle (provisto en la guía de desarrollo) manualmente en tu instancia de BD antes de iniciar la aplicación.

Variables de Entorno: Esta configuración es segura y espera las credenciales como variables de entorno (práctica DevSecOps).
