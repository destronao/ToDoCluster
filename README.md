# Sistema de Gestión de Tareas

Un sistema completo de gestión de tareas desarrollado en Java, con arquitectura limpia y extensible. Permite crear, gestionar y archivar tareas a través de una interfaz de línea de comandos (CLI).

## Características principales

- ✅ **Gestión completa del ciclo de vida**: Crear, iniciar, completar, cancelar y archivar tareas
- ✅ **Sistema de prioridades**: LOW, MEDIUM, HIGH, URGENT
- ✅ **Estados de tarea**: BACKLOG, ACTIVE, DONE, CANCELED, ARCHIVED
- ✅ **Filtrado avanzado**: Por estado y prioridad
- ✅ **Interfaz CLI intuitiva**: Menú interactivo con validación de entrada
- ✅ **Arquitectura extensible**: Fácil agregar nuevas implementaciones (base de datos, web UI)
- ✅ **Documentación completa**: Javadoc generado y guías para usuarios y desarrolladores

## Arquitectura

El proyecto sigue una arquitectura en capas limpia:

- **core/**: Entidades del dominio (Task, TaskState, TaskPriority)
- **repository/**: Interfaces y implementaciones de acceso a datos
- **services/**: Lógica de negocio y validaciones
- **ui/**: Interfaz de usuario (actualmente CLI)
- **tests/**: Suite completa de pruebas unitarias

## Requisitos

- Java 17 o superior
- JDK para compilación

## Instalación y ejecución

### Compilación
```bash
javac -d bin src/core/*.java src/repository/*.java src/repository/inmemory/*.java src/services/*.java src/ui/*.java src/tests/*.java
```

### Ejecutar la aplicación
```bash
java -cp bin ui.TaskCLI
```

### Ejecutar pruebas
```bash
java -cp bin tests.TestRunner
```

## Uso básico

1. Ejecuta la aplicación
2. Selecciona una opción del menú:
   - Crear nueva tarea
   - Listar tareas
   - Actualizar estado o prioridad
   - Filtrar por estado/prioridad
   - Archivar tarea
3. Sigue las instrucciones en pantalla

## Documentación

- **Guía del usuario**: [USER_GUIDE.md](USER_GUIDE.md) - Instrucciones detalladas para usar la aplicación
- **Guía del desarrollador**: [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md) - Arquitectura, contribución y desarrollo
- **Agentes**: [AGENTS.md](AGENTS.md) - Explicación de los agentes de documentación
- **Documentación API**: [docs/index.html](docs/index.html) - Javadoc completo

## Estado del proyecto

✅ **Funcionalidades implementadas**:
- Sistema completo de gestión de tareas
- CLI funcional con todas las operaciones
- Suite de pruebas (35/35 tests pasan)
- Documentación Javadoc completa

🔄 **Extensiones futuras**:
- Persistencia en base de datos
- Interfaz web
- API REST
- Autenticación de usuarios

## Contribución

Ver [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md) para instrucciones de contribución.

## Licencia

Este proyecto es de código abierto. Ver términos de uso.

---

*Desarrollado con ❤️ usando arquitectura limpia y principios SOLID.*</content>
<parameter name="filePath">README.md