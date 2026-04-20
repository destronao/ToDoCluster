package repository;

import java.util.List;
import java.util.Optional;

/**
 * Contract for task data access and storage.
 * This interface defines the basic operations needed to manage tasks,
 * allowing for different implementations (in-memory, database, etc.).
 */
public interface TaskRepository {

    /**
     * Saves a task. If the task is new, it creates it; if it exists, updates it.
     * @param task the task to save
     */
    void save(Task task);

    /**
     * Retrieves all tasks.
     * @return a list of all tasks
     */
    List<Task> findAll();

    /**
     * Finds a task by its ID.
     * @param id the task ID
     * @return an Optional containing the task if found, or empty if not
     */
    Optional<Task> findById(String id);

    /**
     * Finds tasks by priority.
     * @param priority the priority to filter by
     * @return a list of tasks with the specified priority
     */
    List<Task> findByPriority(TaskPriority priority);

    /**
     * Finds tasks by state.
     * @param state the state to filter by
     * @return a list of tasks with the specified state
     */
    List<Task> findByState(TaskState state);

    /**
     * Archives a task by its ID. This typically changes the task's state to ARCHIVED.
     * @param id the task ID
     * @return true if the task was archived, false if not found or already archived
     */
    boolean archive(String id);
}