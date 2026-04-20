package services;

import core.Task;
import core.TaskPriority;
import core.TaskState;
import repository.TaskRepository;

import java.util.List;
import java.util.Optional;

/**
 * Clase de servicio para gestionar la lógica de negocio de tareas.
 * Actúa como intermediario entre el repositorio y la capa de presentación (CLI).
 * Encapsula todas las operaciones relacionadas con tareas y reglas de negocio.
 * 
 * @author Equipo de desarrollo
 * @version 1.0
 */
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Constructor que inyecta la dependencia TaskRepository.
     * 
     * @param taskRepository el repositorio a usar para el acceso a datos
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Crea una nueva tarea con los detalles proporcionados.
     * 
     * @param title el título de la tarea
     * @param description la descripción de la tarea
     * @param priority la prioridad de la tarea
     */
    public void createTask(String title, String description, TaskPriority priority) {
        Task newTask = new Task(title, description, priority);
        taskRepository.save(newTask);
    }

    /**
     * Recupera todas las tareas.
     * 
     * @return una lista de todas las tareas
     */
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    /**
     * Actualiza el estado de una tarea identificada por su ID.
     * 
     * @param id el ID de la tarea
     * @param newState el nuevo estado a establecer
     * @return true si la tarea fue encontrada y actualizada, false en caso contrario
     */
    public boolean updateState(String id, TaskState newState) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            if (task.getState() == TaskState.ARCHIVED) {
                return false; // No permitir actualizar estado de tareas archivadas
            }
            task.setState(newState);
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    /**
     * Actualiza la prioridad de una tarea identificada por su ID.
     * 
     * @param id el ID de la tarea
     * @param newPriority la nueva prioridad a establecer
     * @return true si la tarea fue encontrada y actualizada, false en caso contrario
     */
    public boolean updatePriority(String id, TaskPriority newPriority) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            if (task.getState() == TaskState.ARCHIVED) {
                return false; // No permitir actualizar prioridad de tareas archivadas
            }
            task.setPriority(newPriority);
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    /**
     * Filtra tareas por estado.
     * 
     * @param state el estado para filtrar
     * @return una lista de tareas con el estado especificado
     */
    public List<Task> filterByState(TaskState state) {
        return taskRepository.findByState(state);
    }

    /**
     * Filtra tareas por prioridad.
     * 
     * @param priority la prioridad para filtrar
     * @return una lista de tareas con la prioridad especificada
     */
    public List<Task> filterByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority);
    }

    /**
     * Archiva una tarea identificada por su ID.
     * 
     * @param id el ID de la tarea
     * @return true si la tarea fue archivada, false en caso contrario
     */
    public boolean archiveTask(String id) {
        return taskRepository.archive(id);
    }
}