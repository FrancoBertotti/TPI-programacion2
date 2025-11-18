# README.md - TPI Programacion II
## Vehiculo -> SeguroVehicular (Relacion 1 a 1)

## Descripcion del dominio elegido
El proyecto implementa una relacion 1 a 1 unidireccional entre:
- Vehiculo (A)
- SeguroVehicular (B)

Cada Vehiculo puede tener un unico SeguroVehicular. 

### Requisitos
- Java 21 o compatible
- MySQL Server 8+
- MySQL Workbench o DBeaver
- Archivos:
  - 01_esquema.sql
  - 02_catalogos.sql

### Pasos
1. Abrir MySQL Workbench o DBeaver.
2. Ejecutar 01_esquema.sql para crear:
   - La base segurovehiculos_db
   - Las tablas
   - La relacion 1 a 1
3. Ejecutar 02_datos.sql con datos de prueba.


### Configurar archivo database.Connection
```
db.url=jdbc:mysql://localhost:3306/seguro
db.user=root
db.password=""

### Ejecutar
Ejecutar Main.principal


## Flujo basico de uso
- Crear seguro
- Crear vehiculo
- Asignar seguro a vehiculo
- Buscar por ID


## Enlace al video



## Documentacion incluida
- UML
- Informe PDF
- Scripts SQL
