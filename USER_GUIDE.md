# Guía del Usuario - Sistema de Gestión de Tareas

Esta guía te ayudará a usar el Sistema de Gestión de Tareas a través de la interfaz de línea de comandos (CLI).

## Inicio rápido

1. **Compila el proyecto** (si no está compilado):
   ```bash
   javac -d bin src/core/*.java src/repository/*.java src/repository/inmemory/*.java src/services/*.java src/ui/*.java
   ```

2. **Ejecuta la aplicación**:
   ```bash
   java -cp bin ui.TaskCLI
   ```

3. **Comienza a gestionar tus tareas** siguiendo el menú interactivo.

## Menú principal

Al iniciar la aplicación, verás el siguiente menú:

```
=== Sistema de Gestión de Tareas ===
Bienvenido al CLI de gestión de tareas.

=== Menú Principal ===
1. Crear nueva tarea
2. Listar todas las tareas
3. Actualizar estado de tarea
4. Actualizar prioridad de tarea
5. Filtrar tareas por estado
6. Filtrar tareas por prioridad
7. Archivar tarea
8. Salir
```

## Funcionalidades

### 1. Crear nueva tarea

- **Qué hace**: Crea una nueva tarea en el sistema
- **Cómo usar**:
  1. Selecciona opción 1
  2. Ingresa el título
  3. Ingresa la descripción (opcional)
  4. Selecciona la prioridad: LOW, MEDIUM, HIGH, URGENT
- **Resultado**: La tarea se crea con ID único y estado BACKLOG

### 2. Listar todas las tareas

- **Qué hace**: Muestra todas las tareas existentes
- **Formato**: `[Task T0001] - Título - Prioridad - Estado`
- **Nota**: Incluye la descripción completa debajo

### 3. Actualizar estado de tarea

- **Qué hace**: Cambia el estado de una tarea existente
- **Estados disponibles**: BACKLOG, ACTIVE, DONE, CANCELED
- **Cómo usar**:
  1. Selecciona opción 3
  2. Ingresa el ID de la tarea (ej: T0001)
  3. Selecciona el nuevo estado
- **Validaciones**: No se puede cambiar estado de tareas archivadas

### 4. Actualizar prioridad de tarea

- **Qué hace**: Cambia la prioridad de una tarea
- **Prioridades**: LOW, MEDIUM, HIGH, URGENT
- **Cómo usar**: Similar al estado, ingresa ID y nueva prioridad
- **Validaciones**: No se puede cambiar prioridad de tareas archivadas

### 5. Filtrar tareas por estado

- **Qué hace**: Muestra solo tareas con un estado específico
- **Estados**: BACKLOG, ACTIVE, DONE, CANCELED, ARCHIVED
- **Uso**: Selecciona estado y verás la lista filtrada

### 6. Filtrar tareas por prioridad

- **Qué hace**: Muestra solo tareas con una prioridad específica
- **Uso**: Similar al filtro por estado

### 7. Archivar tarea

- **Qué hace**: Archiva una tarea completada o cancelada
- **Requisitos**: La tarea debe estar en estado DONE o CANCELED
- **Efecto**: Cambia estado a ARCHIVED y ya no se puede modificar

### 8. Salir

- **Qué hace**: Cierra la aplicación
- **Nota**: Los datos se pierden al salir (implementación en memoria)

## Ciclo de vida de una tarea

1. **BACKLOG**: Estado inicial, tarea creada pero no iniciada
2. **ACTIVE**: Tarea en proceso de trabajo
3. **DONE**: Tarea completada exitosamente
4. **CANCELED**: Tarea cancelada (no se completará)
5. **ARCHIVED**: Tarea finalizada y archivada

### Transiciones válidas

- BACKLOG → ACTIVE (iniciar)
- ACTIVE → DONE (completar)
- ACTIVE → CANCELED (cancelar)
- DONE → ARCHIVED (archivar)
- CANCELED → ARCHIVED (archivar)

## Consejos de uso

- **IDs únicos**: Cada tarea tiene un ID como T0001, T0002, etc.
- **Validación**: El sistema valida entradas y estados
- **Mensajes**: Lee los mensajes de error/éxito para entender qué sucede
- **Persistencia**: Actualmente los datos no se guardan entre sesiones
- **Case sensitive**: Los estados y prioridades distinguen mayúsculas/minúsculas

## Solución de problemas

### "Tarea no encontrada"
- Verifica que el ID sea correcto (incluyendo ceros)
- Asegúrate de que la tarea existe

### "No se puede actualizar"
- Las tareas archivadas no se pueden modificar
- Verifica el estado actual de la tarea

### "Entrada inválida"
- Estados: BACKLOG, ACTIVE, DONE, CANCELED
- Prioridades: LOW, MEDIUM, HIGH, URGENT

### La aplicación se cierra inesperadamente
- Verifica que no haya errores de compilación
- Asegúrate de usar Java 17+

## Soporte

Si encuentras problemas, consulta la [Guía del Desarrollador](DEVELOPER_GUIDE.md) o revisa los logs de error.</content>
<parameter name="filePath">USER_GUIDE.md