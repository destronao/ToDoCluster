# Guía del Desarrollador - Sistema de Gestión de Tareas

Esta guía proporciona información técnica detallada para desarrolladores que quieren entender, modificar o contribuir al proyecto.

## Arquitectura del sistema

### Patrón de diseño

El proyecto sigue el patrón **Repository** con **Inyección de Dependencias** y arquitectura en capas:

```
┌─────────────────┐
│   UI Layer      │  ← TaskCLI (presentación)
├─────────────────┤
│ Service Layer   │  ← TaskService (lógica de negocio)
├─────────────────┤
│ Repository      │  ← TaskRepository (contrato de datos)
│   Layer         │  ← InMemoryTaskRepository (implementación)
├─────────────────┤
│   Core/Domain   │  ← Task, TaskState, TaskPriority (entidades)
└─────────────────┘
```

### Principios SOLID aplicados

- **S**: Single Responsibility - Cada clase tiene una responsabilidad clara
- **O**: Open/Closed - Extensible mediante interfaces (TaskRepository)
- **L**: Liskov Substitution - InMemoryTaskRepository implementa TaskRepository
- **I**: Interface Segregation - Interfaces específicas y minimalistas
- **D**: Dependency Inversion - TaskService depende de TaskRepository, no de implementación

## Estructura de paquetes

```
src/
├── core/                    # Dominio del negocio
│   ├── Task.java           # Entidad principal
│   ├── TaskState.java      # Enum de estados
│   └── TaskPriority.java   # Enum de prioridades
├── repository/             # Capa de acceso a datos
│   ├── TaskRepository.java # Interfaz del repositorio
│   └── inmemory/          # Implementaciones
│       └── InMemoryTaskRepository.java
├── services/               # Lógica de negocio
│   └── TaskService.java   # Servicio principal
├── ui/                     # Interfaz de usuario
│   └── TaskCLI.java       # CLI interactiva
└── tests/                  # Pruebas unitarias
    ├── TestRunner.java    # Ejecutor de pruebas
    ├── TaskTests.java     # Pruebas de Task
    ├── InMemoryTaskRepositoryTest.java
    └── TaskServiceTest.java
```

## Clases principales

### Task
- **Responsabilidad**: Representar una tarea con sus propiedades y ciclo de vida
- **Estados**: BACKLOG → ACTIVE → DONE/CANCELED → ARCHIVED
- **Métodos clave**: start(), complete(), cancel(), archive()
- **Validaciones**: Transiciones de estado controladas

### TaskRepository
- **Tipo**: Interfaz
- **Métodos**: save(), findAll(), findById(), findByPriority(), findByState(), archive()
- **Propósito**: Abstraer el acceso a datos para permitir múltiples implementaciones

### InMemoryTaskRepository
- **Implementación**: Almacenamiento en memoria usando ArrayList
- **Limitaciones**: Datos se pierden al reiniciar aplicación
- **Ventajas**: Simple, rápido para pruebas

### TaskService
- **Responsabilidad**: Lógica de negocio y validaciones
- **Validaciones**:
  - No actualizar tareas archivadas
  - Transiciones de estado válidas
  - IDs únicos
- **Dependencias**: TaskRepository (inyectado)

### TaskCLI
- **Responsabilidad**: Interfaz de usuario de consola
- **Características**:
  - Menú interactivo
  - Validación de entrada
  - Manejo de excepciones
- **Manejo de errores**: Try-catch para input exceptions

## Ciclo de desarrollo

### 1. Compilación
```bash
javac -d bin src/core/*.java src/repository/*.java src/repository/inmemory/*.java src/services/*.java src/ui/*.java src/tests/*.java
```

### 2. Ejecución
```bash
# Aplicación
java -cp bin ui.TaskCLI

# Pruebas
java -cp bin tests.TestRunner
```

### 3. Generación de documentación
```bash
javadoc -d docs -sourcepath src -subpackages core:repository:services:ui -encoding UTF-8 -charset UTF-8 -docencoding UTF-8
```

## Extensiones futuras

### Base de datos
1. Crear nueva implementación: `DatabaseTaskRepository`
2. Implementar `TaskRepository` usando JDBC/Hibernate
3. Actualizar `TaskCLI.main()` para inyectar la nueva implementación

### Interfaz web
1. Crear paquete `web/` con servlets/controllers
2. Implementar endpoints REST
3. Crear HTML/JS para frontend

### API REST
1. Agregar dependencias (Spring Boot o similar)
2. Crear controllers para CRUD operations
3. Documentar con OpenAPI/Swagger

### Autenticación
1. Agregar modelo User
2. Implementar login/registro
3. Asociar tareas con usuarios

## Pruebas

### Suite de pruebas
- **TaskTests**: 14 pruebas - Ciclo de vida y validaciones
- **InMemoryTaskRepositoryTest**: 9 pruebas - Operaciones CRUD
- **TaskServiceTest**: 12 pruebas - Lógica de negocio
- **Total**: 35 pruebas, todas pasan

### Ejecutar pruebas
```bash
java -cp bin tests.TestRunner
```

### Agregar nuevas pruebas
1. Seguir patrón existente en clases de test
2. Usar `assertTrue/False` para validaciones
3. Incluir casos positivos y negativos
4. Resetear estado entre pruebas (`Task.resetCounter()`)

## Contribución

### Proceso
1. Fork el repositorio
2. Crear rama feature: `git checkout -b feature/nueva-funcionalidad`
3. Implementar cambios
4. Agregar pruebas
5. Ejecutar suite completa
6. Commit y push
7. Crear Pull Request

### Estándares de código
- **Lenguaje**: Español para comentarios y documentación
- **Nombres**: camelCase para métodos/variables, PascalCase para clases
- **Javadoc**: Todos los métodos públicos documentados
- **Validaciones**: Input validation en UI layer
- **Excepciones**: Manejo apropiado, no silenciar

### Commit messages
```
tipo: descripción breve

- Detalle del cambio
- Otro detalle si aplica
```

Tipos: feat, fix, docs, test, refactor

## Agentes de documentación

### Project Documentation Generator
- **Archivo**: `documentation.agent.md`
- **Propósito**: Generar y mantener documentación del proyecto
- **Uso**: Invocar con `@file:documentation.agent.md` seguido de instrucción
- **Funciones**:
  - Generar README.md
  - Describir arquitectura
  - Documentar clases y módulos
  - Crear guías de usuario/desarrollador

### Cómo usar agentes
1. Mencionar el archivo del agente: `@file:documentation.agent.md`
2. Dar instrucción clara: "Genera README", "Crea guía de usuario"
3. El agente analizará el código y generará documentación apropiada

### Creación de nuevos agentes
1. Crear archivo `nombre.agent.md`
2. Definir propósito, cuándo usar, qué hace
3. Especificar herramientas preferidas
4. Incluir ejemplos de uso

## Solución de problemas

### Errores comunes
- **ClassNotFoundException**: Verificar classpath y compilación
- **NoSuchElementException**: Input handling en CLI
- **IllegalArgumentException**: Validar enums y parámetros

### Debugging
- Usar `System.out.println()` para logs temporales
- Ejecutar pruebas individuales
- Verificar estados de objetos

### Performance
- Implementación actual es O(n) para búsquedas
- Para producción, considerar índices o base de datos

## Recursos adicionales

- **Javadoc**: `docs/index.html`
- **Código fuente**: `src/` directory
- **Pruebas**: `src/tests/`
- **Repositorio**: GitHub - rama feature/task-repository-interface

---

*Para preguntas o contribuciones, crear issue en el repositorio.*</content>
<parameter name="filePath">DEVELOPER_GUIDE.md