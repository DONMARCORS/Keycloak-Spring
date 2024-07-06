# Keycloak-Spring

## Recreación de entorno

Para recrear el entorno y el correcto funcionamiento del proyecto se debe realizar lo siguiente:

- Instalar el servidor de Keycloak.
- Crear un usuario administrador para poder ingresar en Keycloak.
- Crear un realm para la aplicacion, al igual que un cliente.
- Cambiar los datos requeridos para la base de datos en el `aplication.properties`.
- En la clase `KeycloakProvider`, modificar las constantes con los datos de Keycloak.
- Crear y asignar roles para realm y cliente que tengan el nombre `admin` y `user`.

## Caracteristicas

- Crear, actualizar, leer y eliminar usuarios con roles admin y user (Unicamente con usuario admin).
- Crear, actualizar, leer y eliminar productos (Para usuarios tanto admin como user).
- Verificar roles y permisos por medio de un Json Web Token.
- Utilizar el pool de conecciones Hikari.
- Utilizar Cache local por medio de un HashMap y Spring Caching.
