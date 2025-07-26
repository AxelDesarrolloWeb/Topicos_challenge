# Challenge ForoHub - API REST

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

API REST para un sistema de foros (foroHub) desarrollada con Spring Boot que permite gestionar tópicos de discusión con autenticación JWT.

🔗 **Repositorio GitHub**: [https://github.com/AxelDesarrolloWeb/Topicos_challenge](https://github.com/AxelDesarrolloWeb/Topicos_challenge)

## Características principales

- 🛡️ Autenticación JWT segura
- 📝 CRUD completo de tópicos
- 🔍 Listado paginado de tópicos
- ⚡ Operaciones transaccionales
- 📚 Documentación con Swagger
- 🧪 Validación de datos
- 🔄 Soft delete (eliminación lógica)

## Tecnologías utilizadas

- **Lenguaje**: Java 17
- **Framework**: Spring Boot 3
- **Seguridad**: Spring Security + JWT
- **Base de datos**: MySQL
- **Documentación**: OpenAPI 3 (Swagger)
- **Gestión de dependencias**: Maven
- **Otras librerías**:
  - Lombok
  - Spring Data JPA
  - Hibernate Validator
  - Springdoc OpenAPI

## Configuración del proyecto

### Requisitos previos

- Java 17 o superior
- MySQL 8.x
- Maven

### Pasos de instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/AxelDesarrolloWeb/Topicos_challenge.git
   cd Topicos_challenge
   ```

2. Crear la base de datos MySQL:
   ```sql
   CREATE DATABASE foroHub;
   ```

3. Configurar las credenciales en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foroHub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

4. Compilar y ejecutar la aplicación:
   ```bash
   mvn clean package
   java -jar target/*.jar
   ```

La aplicación estará disponible en: `http://localhost:8087`

## Uso de la API

### Autenticación

1. **Registro de usuario**:
   ```http
   POST /auth/registro
   Content-Type: application/json
   
   {
     "email": "usuario@ejemplo.com",
     "contrasena": "password123"
   }
   ```

2. **Inicio de sesión**:
   ```http
   POST /auth/login
   Content-Type: application/json
   
   {
     "email": "usuario@ejemplo.com",
     "contrasena": "password123"
   }
   ```
   Respuesta:
   ```json
   {
     "jwTtoken": "token_jwt_generado"
   }
   ```

### Operaciones con tópicos (requieren autenticación)

**Crear tópico**:
```http
POST /topicos
Authorization: Bearer <token_jwt>
Content-Type: application/json

{
  "titulo": "Título del tópico",
  "autor": "Autor del tópico",
  "curso": "Curso relacionado",
  "mensaje": "Contenido del mensaje"
}
```

**Obtener detalle de tópico**:
```http
GET /topicos/{id}
Authorization: Bearer <token_jwt>
```

**Actualizar tópico**:
```http
PUT /topicos
Authorization: Bearer <token_jwt>
Content-Type: application/json

{
  "id": 1,
  "titulo": "Nuevo título",
  "curso": "Nuevo curso",
  "mensaje": "Mensaje actualizado"
}
```

**Eliminar tópico (soft delete)**:
```http
DELETE /topicos/{id}
Authorization: Bearer <token_jwt>
```

## Documentación API

Accede a la documentación interactiva de la API:

- Swagger UI: `http://localhost:8087/swagger-ui.html`
- OpenAPI Spec: `http://localhost:8087/v3/api-docs`

## Estructura del proyecto

```
src/main/java
├── com.topico.alvax
│   ├── controller
│   │   ├── TopicoController.java
│   │   ├── AutenticacionController.java
│   │   └── RegistroController.java
│   ├── domain
│   │   ├── topicos
│   │   │   ├── Topico.java
│   │   │   ├── TopicoRepository.java
│   │   │   ├── DatosRegistroTopico.java
│   │   │   ├── DatosActualizarTopico.java
│   │   │   ├── DatosListadoTopico.java
│   │   │   └── DatosDetalleTopico.java
│   │   └── usuarios
│   │       ├── Usuario.java
│   │       ├── UsuarioRepository.java
│   │       └── UsuarioService.java
│   ├── infra
│   │   ├── errores
│   │   │   └── TratadorDeErrores.java
│   │   ├── security
│   │   │   ├── SecurityConfigurations.java
│   │   │   ├── SecurityFilter.java
│   │   │   ├── TokenService.java
│   │   │   ├── AutenticacionService.java
│   │   │   └── DatosJWTToken.java
│   │   └── springdoc
│   │       └── SpringDocConfiguration.java
│   └── TopicoApplication.java
└── resources
    ├── application.properties
    └── db/migration
        ├── V1__create-table-topicos.sql
        └── V2__create-table-usuarios.sql
```

## Contribución

Las contribuciones son bienvenidas! Por favor:

1. Haz un fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-feature`)
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva feature'`)
4. Push a la rama (`git push origin feature/nueva-feature`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.
