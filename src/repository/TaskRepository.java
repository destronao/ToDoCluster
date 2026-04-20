package repository;

import core.Task;
import core.TaskPriority;
import core.TaskState;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el acceso y almacenamiento de datos de tareas.
 * Define las operaciones básicas necesarias para gestionar tareas,
 * permitiendo diferentes implementaciones (en memoria, base de datos, etc.).
 * 
 * @author Equipo de desarrollo
 * @version 1.0
 */
public interface TaskRepository {

    /**
     * Guarda una tarea. Si la tarea es nueva, la crea; si existe, la actualiza.
     * 
     * @param task la tarea a guardar
     */
    void save(Task task);

    /**
     * Recupera todas las tareas.
     * 
     * @return una lista de todas las tareas
     */
    List<Task> findAll();

    /**
     * Busca una tarea por su ID.
     * 
     * @param id el ID de la tarea
     * @return un Optional que contiene la tarea si se encuentra, o vacío si no
     */
    Optional<Task> findById(String id);

    /**
     * Busca tareas por prioridad.
     * 
     * @param priority la prioridad para filtrar
     * @return una lista de tareas con la prioridad especificada
     */
    List<Task> findByPriority(TaskPriority priority);

    /**
     * Busca tareas por estado.
     * 
     * @param state el estado para filtrar
     * @return una lista de tareas con el estado especificado
     */
    List<Task> findByState(TaskState state);

    /**
     * Archiva una tarea por su ID. Esto típicamente cambia el estado de la tarea a ARCHIVED.
     * 
     * @param id el ID de la tarea
     * @return true si la tarea fue archivada, false si no se encontró o ya estaba archivada
     */
    boolean archive(String id);
}