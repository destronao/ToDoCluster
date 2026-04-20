# Agentes de Documentación

Este proyecto incluye agentes especializados para automatizar tareas de documentación y desarrollo. Los agentes son archivos Markdown con instrucciones específicas que guían la generación de contenido.

## Agente: Project Documentation Generator

### Archivo
`documentation.agent.md`

### Propósito
Generar y mantener documentación clara y estructurada para el proyecto, enfocándose en README, arquitectura y guías de uso.

### Cuándo usarlo
- Al iniciar un nuevo proyecto que necesita documentación inicial
- Cuando se actualiza la funcionalidad y se requiere actualizar docs
- Para describir arquitectura, capas y organización de paquetes
- Al documentar ejecución del proyecto y uso de CLI
- Para crear guías específicas para usuarios finales y desarrolladores

### Qué hace
- **Análisis**: Examina la estructura del repositorio y archivos Java principales
- **Generación README**: Crea README.md con:
  - Descripción general del proyecto
  - Funcionalidades actuales
  - Estructura de carpetas y paquetes
  - Instrucciones de compilación y ejecución
- **Documentación de módulos**: Documenta clases, interfaces y componentes principales
- **Estilo**: Escribe de forma clara, concisa y profesional

### Requisitos del agente
- **No especulativo**: Basado únicamente en código y estructura reales
- **Lenguaje accesible**: Dirigido a desarrolladores
- **Estado actual**: Incluye notas sobre estado del proyecto y extensiones posibles
- **Consistencia**: Mantiene lenguaje español para este proyecto

### Herramientas preferidas
- `list_dir`: Inspeccionar árbol de archivos
- `read_file`: Entender clases clave y contenido
- `create_file` / `replace_string_in_file`: Generar/actualizar documentación
- `run_in_terminal`: Solo para verificar compilación/ejecución cuando necesario

### Ejemplos de uso
```
@file:documentation.agent.md Genera la documentación del proyecto en README.md
@file:documentation.agent.md Describe la arquitectura de este proyecto Java y cómo ejecutarlo
@file:documentation.agent.md Crea documentación para desarrolladores con instrucciones de compilación y uso
@file:documentation.agent.md realiza el javadoc de las clases usando la misma estructura y lenguaje
@file:documentation.agent.md Creame documentación con markdown, quiero un readme explicando el proyecto, un archivo diseñado para el usuario final, otro para desarrolladores, y que expliquen los agentes
```

### Cómo funciona internamente
1. **Análisis de contexto**: Lee archivos del proyecto para entender estructura
2. **Generación de contenido**: Crea archivos Markdown basados en patrones
3. **Validación**: Asegura que el contenido sea preciso y útil
4. **Formato**: Usa Markdown con encabezados, listas y bloques de código

### Beneficios
- **Consistencia**: Documentación uniforme en estilo y formato
- **Eficiencia**: Generación rápida de documentación completa
- **Mantenibilidad**: Actualización automática basada en código
- **Accesibilidad**: Guías claras para diferentes audiencias

## Creación de nuevos agentes

### Estructura de un agente
```markdown
---
name: nombre-del-agente
description: Descripción breve del propósito
---

# Nombre del Agente

## Purpose
Explicación detallada del propósito

## Cuándo usarlo
Lista de situaciones apropiadas

## Qué hace
Descripción de acciones realizadas

## Requisitos
Reglas y restricciones

## Herramientas preferidas
Lista de herramientas a usar

## Ejemplos de prompt
Ejemplos de cómo invocar al agente
```

### Mejores prácticas
- **Específico**: Cada agente debe tener un propósito claro
- **Documentado**: Incluir ejemplos y casos de uso
- **Consistente**: Seguir formato estándar
- **Modular**: Un agente por responsabilidad

## Agentes en este proyecto

### Actuales
- **Project Documentation Generator**: Para documentación general y README

### Futuros posibles
- **Test Generator**: Para crear suites de pruebas
- **Code Review Agent**: Para análisis de calidad de código
- **Deployment Agent**: Para automatizar despliegue
- **API Generator**: Para crear documentación de APIs

## Uso de agentes

### Sintaxis
```
@file:nombre.agente.md [instrucción específica]
```

### Ejemplos en este proyecto
- Documentación Javadoc generada
- README, USER_GUIDE.md, DEVELOPER_GUIDE.md creados
- Arquitectura documentada
- Guías de contribución

### Integración con desarrollo
Los agentes pueden ser parte del flujo de desarrollo:
1. Implementar funcionalidad
2. Ejecutar agente para actualizar documentación
3. Commit de cambios y documentación juntos

---

*Los agentes mejoran la productividad al automatizar tareas repetitivas de documentación.*</content>
<parameter name="filePath">AGENTS.md