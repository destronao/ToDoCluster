package repository.inmemory;

import core.Task;
import core.TaskPriority;
import core.TaskState;
import repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación en memoria de TaskRepository.
 * Almacena tareas en una lista para simplicidad y propósitos de prueba.
 * No es adecuada para producción ya que los datos se pierden al reiniciar la aplicación.
 * 
 * @author Equipo de desarrollo
 * @version 1.0
 */
public class InMemoryTaskRepository implements TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    /**
     * Constructor por defecto.
     */
    public InMemoryTaskRepository() {
        // Constructor vacío
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Task task) {
        // Check if task already exists by ID
        Optional<Task> existing = findById(task.getId());
        if (existing.isPresent()) {
            // Update existing task
            int index = tasks.indexOf(existing.get());
            tasks.set(index, task);
        } else {
            // Add new task
            tasks.add(task);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks); // Return a copy to prevent external modification
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Task> findById(String id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> findByPriority(TaskPriority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> findByState(TaskState state) {
        return tasks.stream()
                .filter(task -> task.getState() == state)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean archive(String id) {
        Optional<Task> taskOpt = findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            return task.archive();
        }
        return false;
    }
}