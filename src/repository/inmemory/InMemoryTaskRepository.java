package repository.inmemory;

import core.Task;
import core.TaskPriority;
import core.TaskState;
import repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation of TaskRepository.
 * Stores tasks in a list for simplicity and testing purposes.
 * Not suitable for production as data is lost on application restart.
 */
public class InMemoryTaskRepository implements TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

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

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks); // Return a copy to prevent external modification
    }

    @Override
    public Optional<Task> findById(String id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Task> findByPriority(TaskPriority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Task> findByState(TaskState state) {
        return tasks.stream()
                .filter(task -> task.getState() == state)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

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