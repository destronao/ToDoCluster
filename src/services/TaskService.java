package services;

import core.Task;
import core.TaskPriority;
import core.TaskState;
import repository.TaskRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing task business logic.
 * Acts as an intermediary between the repository and the presentation layer (CLI).
 * Encapsulates all task-related operations and business rules.
 */
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Constructor that injects the TaskRepository dependency.
     * @param taskRepository the repository to use for data access
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Creates a new task with the given details.
     * @param title the title of the task
     * @param description the description of the task
     * @param priority the priority of the task
     */
    public void createTask(String title, String description, TaskPriority priority) {
        Task newTask = new Task(title, description, priority);
        taskRepository.save(newTask);
    }

    /**
     * Retrieves all tasks.
     * @return a list of all tasks
     */
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    /**
     * Updates the state of a task identified by its ID.
     * @param id the task ID
     * @param newState the new state to set
     * @return true if the task was found and updated, false otherwise
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
     * Updates the priority of a task identified by its ID.
     * @param id the task ID
     * @param newPriority the new priority to set
     * @return true if the task was found and updated, false otherwise
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
     * Filters tasks by state.
     * @param state the state to filter by
     * @return a list of tasks with the specified state
     */
    public List<Task> filterByState(TaskState state) {
        return taskRepository.findByState(state);
    }

    /**
     * Filters tasks by priority.
     * @param priority the priority to filter by
     * @return a list of tasks with the specified priority
     */
    public List<Task> filterByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority);
    }

    /**
     * Archives a task identified by its ID.
     * @param id the task ID
     * @return true if the task was archived, false otherwise
     */
    public boolean archiveTask(String id) {
        return taskRepository.archive(id);
    }
}