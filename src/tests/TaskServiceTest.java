package tests;

import core.Task;
import core.TaskPriority;
import core.TaskState;
import repository.TaskRepository;
import repository.inmemory.InMemoryTaskRepository;
import services.TaskService;

import java.util.List;

public class TaskServiceTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        Task.resetCounter();

        TaskRepository repository = new InMemoryTaskRepository();
        TaskService service = new TaskService(repository);

        System.out.println("=== Pruebas de TaskService ===\n");

        // Crear tareas
        service.createTask("Tarea 1", "Descripción 1", TaskPriority.HIGH);
        service.createTask("Tarea 2", "", TaskPriority.MEDIUM);
        service.createTask("Tarea 3", "Descripción 3", TaskPriority.LOW);

        assertEquals(3, service.listTasks().size(), "Debe haber 3 tareas después de crear");

        // Actualizar estado
        assertTrue(service.updateState("T0001", TaskState.ACTIVE), "Debe poder actualizar estado a ACTIVE");
        repository.findById("T0001").ifPresent(task ->
            assertEquals(TaskState.ACTIVE, task.getState(), "T0001 debe estar en estado ACTIVE")
        );

        // Actualizar prioridad
        assertTrue(service.updatePriority("T0002", TaskPriority.HIGH), "Debe poder actualizar prioridad");
        repository.findById("T0002").ifPresent(task ->
            assertEquals(TaskPriority.HIGH, task.getPriority(), "T0002 debe tener prioridad HIGH")
        );

        // Filtrar por estado
        List<Task> activeTasks = service.filterByState(TaskState.ACTIVE);
        assertEquals(1, activeTasks.size(), "Debe haber 1 tarea en estado ACTIVE");

        // Filtrar por prioridad
        List<Task> highPriorityTasks = service.filterByPriority(TaskPriority.HIGH);
        assertEquals(2, highPriorityTasks.size(), "Debe haber 2 tareas con prioridad HIGH");

        // Completar tarea para poder archivar
        assertTrue(service.updateState("T0001", TaskState.DONE), "Debe poder completar T0001");
        repository.findById("T0001").ifPresent(task ->
            assertEquals(TaskState.DONE, task.getState(), "T0001 debe estar en estado DONE")
        );

        // Archivar tarea
        assertTrue(service.archiveTask("T0001"), "Debe poder archivar T0001");
        repository.findById("T0001").ifPresent(task ->
            assertEquals(TaskState.ARCHIVED, task.getState(), "T0001 debe estar archivada")
        );

        // Intentar actualizar tarea archivada
        assertTrue(!service.updateState("T0001", TaskState.DONE), "No debe poder actualizar estado de tarea archivada");

        System.out.println();
        System.out.println("=== Resultados de TaskServiceTest ===");
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