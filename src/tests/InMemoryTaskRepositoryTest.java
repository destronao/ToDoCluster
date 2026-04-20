package tests;

import core.Task;
import core.TaskPriority;
import core.TaskState;
import repository.inmemory.InMemoryTaskRepository;
import repository.TaskRepository;

import java.util.List;

public class InMemoryTaskRepositoryTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        Task.resetCounter();
        
        TaskRepository repository = new InMemoryTaskRepository();

        System.out.println("=== Pruebas de InMemoryTaskRepository ===\n");

        Task task1 = new Task("Tarea 1", "Descripción 1", TaskPriority.HIGH);
        Task task2 = new Task("Tarea 2", TaskPriority.MEDIUM);
        Task task3 = new Task("Tarea 3", "Descripción 3", TaskPriority.LOW);

        repository.save(task1);
        repository.save(task2);
        repository.save(task3);

        assertEquals(3, repository.findAll().size(), "Debe haber 3 tareas después de guardar");

        assertTrue(repository.findById("T0001").isPresent(), "T0001 debe encontrarse");
        assertTrue(repository.findById("T9999").isEmpty(), "T9999 no debe encontrarse");

        List<Task> highPriority = repository.findByPriority(TaskPriority.HIGH);
        assertEquals(1, highPriority.size(), "Debe haber 1 tarea con prioridad HIGH");

        List<Task> backlogTasks = repository.findByState(TaskState.BACKLOG);
        assertEquals(3, backlogTasks.size(), "Todas las tareas iniciales deben estar en BACKLOG");

        task1.start();
        task1.complete();
        repository.save(task1);
        boolean archived = repository.archive("T0001");
        assertTrue(archived, "T0001 debe archivarse después de completar");

        repository.findById("T0001").ifPresent(task ->
            assertEquals(TaskState.ARCHIVED, task.getState(), "T0001 debe estar en estado ARCHIVED después de archivar")
        );

        boolean archived2 = repository.archive("T0002");
        assertTrue(!archived2, "T0002 no debe archivarse desde BACKLOG");

        assertEquals(3, repository.findAll().size(), "El número total de tareas no debe cambiar después de archivar");

        System.out.println();
        System.out.println("=== Resultados de InMemoryTaskRepositoryTest ===");
        System.out.println("OK: " + passed + "  Fail: " + failed);
    }

    private static void assertTrue(boolean condition, String message) {
        if (condition) {
            passed++;
            System.out.println("OK: " + message);
        } else {
            failed++;
            System.out.println("Fail: " + message);
        }
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null ? actual == null : expected.equals(actual)) {
            passed++;
            System.out.println("OK: " + message);
        } else {
            failed++;
            System.out.println("Fail: " + message + " (esperado=" + expected + ", actual=" + actual + ")");
        }
    }
}
