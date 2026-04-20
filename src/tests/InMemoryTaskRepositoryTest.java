package tests;

import core.Task;
import core.TaskPriority;
import core.TaskState;
import repository.InMemory.InMemoryTaskRepository;
import repository.TaskRepository;

import java.util.List;

public class InMemoryTaskRepositoryTest {

    public static void main(String[] args) {
        TaskRepository repository = new InMemoryTaskRepository();

        System.out.println("=== Pruebas de InMemoryTaskRepository ===\n");

        // Crear tareas
        Task task1 = new Task("Tarea 1", "Descripción 1", TaskPriority.HIGH);
        Task task2 = new Task("Tarea 2", TaskPriority.MEDIUM);
        Task task3 = new Task("Tarea 3", "Descripción 3", TaskPriority.LOW);

        System.out.println("1. Guardando tareas...");
        repository.save(task1);
        repository.save(task2);
        repository.save(task3);
        System.out.println("Tareas guardadas.\n");

        // Listar todas
        System.out.println("2. Listando todas las tareas:");
        List<Task> allTasks = repository.findAll();
        allTasks.forEach(System.out::println);
        System.out.println();

        // Buscar por ID
        System.out.println("3. Buscando tarea por ID 'T0001':");
        repository.findById("T0001").ifPresentOrElse(
            task -> System.out.println("Encontrada: " + task),
            () -> System.out.println("No encontrada")
        );
        System.out.println("Buscando tarea por ID 'T9999':");
        repository.findById("T9999").ifPresentOrElse(
            task -> System.out.println("Encontrada: " + task),
            () -> System.out.println("No encontrada")
        );
        System.out.println();

        // Filtrar por prioridad
        System.out.println("4. Filtrando por prioridad HIGH:");
        List<Task> highPriority = repository.findByPriority(TaskPriority.HIGH);
        highPriority.forEach(System.out::println);
        System.out.println();

        // Filtrar por estado
        System.out.println("5. Filtrando por estado BACKLOG:");
        List<Task> backlogTasks = repository.findByState(TaskState.BACKLOG);
        backlogTasks.forEach(System.out::println);
        System.out.println();

        // Completar y archivar una tarea
        System.out.println("6. Completando y archivando tarea 'T0001':");
        task1.complete(); // Cambiar estado a DONE
        repository.save(task1); // Actualizar en repositorio
        boolean archived = repository.archive("T0001");
        System.out.println("Archivada: " + archived);
        System.out.println("Estado actual de T0001:");
        repository.findById("T0001").ifPresent(System.out::println);
        System.out.println();

        // Intentar archivar una tarea en BACKLOG
        System.out.println("7. Intentando archivar tarea en BACKLOG 'T0002':");
        boolean archived2 = repository.archive("T0002");
        System.out.println("Archivada: " + archived2);
        System.out.println();

        // Listar todas después de cambios
        System.out.println("8. Listando todas las tareas después de cambios:");
        allTasks = repository.findAll();
        allTasks.forEach(System.out::println);
        System.out.println();

        System.out.println("=== Pruebas completadas ===");
    }
}</content>
<parameter name="filePath">f:\GithubProjects\MyTasks-1\src\tests\InMemoryTaskRepositoryTest.java