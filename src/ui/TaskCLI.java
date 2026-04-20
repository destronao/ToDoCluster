package ui;

import core.Task;
import core.TaskPriority;
import core.TaskState;
import repository.TaskRepository;
import repository.inmemory.InMemoryTaskRepository;
import services.TaskService;

import java.util.List;
import java.util.Scanner;

/**
 * Command Line Interface for the Task Management System.
 * Provides a menu-driven interface for users to manage tasks.
 */
public class TaskCLI {

    private final TaskService taskService;
    private final Scanner scanner;

    public TaskCLI(TaskService taskService) {
        this.taskService = taskService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Sistema de Gestión de Tareas ===");
        System.out.println("Bienvenido al CLI de gestión de tareas.\n");

        boolean running = true;
        while (running) {
            showMenu();
            try {
                int choice = getIntInput("Seleccione una opción: ");
                System.out.println();

                switch (choice) {
                    case 1:
                        createTask();
                        break;
                    case 2:
                        listTasks();
                        break;
                    case 3:
                        updateTaskState();
                        break;
                    case 4:
                        updateTaskPriority();
                        break;
                    case 5:
                        filterByState();
                        break;
                    case 6:
                        filterByPriority();
                        break;
                    case 7:
                        archiveTask();
                        break;
                    case 8:
                        running = false;
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("No hay más entrada. Saliendo...");
                running = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Crear nueva tarea");
        System.out.println("2. Listar todas las tareas");
        System.out.println("3. Actualizar estado de tarea");
        System.out.println("4. Actualizar prioridad de tarea");
        System.out.println("5. Filtrar tareas por estado");
        System.out.println("6. Filtrar tareas por prioridad");
        System.out.println("7. Archivar tarea");
        System.out.println("8. Salir");
        System.out.println();
    }

    private void createTask() {
        System.out.println("=== Crear Nueva Tarea ===");
        String title = getStringInput("Título: ");
        String description = getStringInput("Descripción: ");
        TaskPriority priority = getPriorityInput("Prioridad (LOW/MEDIUM/HIGH/URGENT): ");

        taskService.createTask(title, description, priority);
        System.out.println("Tarea creada exitosamente.");
    }

    private void listTasks() {
        System.out.println("=== Lista de Tareas ===");
        List<Task> tasks = taskService.listTasks();
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
                System.out.println("---");
            }
        }
    }

    private void updateTaskState() {
        System.out.println("=== Actualizar Estado de Tarea ===");
        String id = getStringInput("ID de la tarea: ");
        TaskState newState = getStateInput("Nuevo estado (BACKLOG/ACTIVE/DONE/CANCELED): ");

        boolean success = taskService.updateState(id, newState);
        if (success) {
            System.out.println("Estado actualizado exitosamente.");
        } else {
            System.out.println("Error: Tarea no encontrada o no se puede actualizar.");
        }
    }

    private void updateTaskPriority() {
        System.out.println("=== Actualizar Prioridad de Tarea ===");
        String id = getStringInput("ID de la tarea: ");
        TaskPriority newPriority = getPriorityInput("Nueva prioridad (LOW/MEDIUM/HIGH/URGENT): ");

        boolean success = taskService.updatePriority(id, newPriority);
        if (success) {
            System.out.println("Prioridad actualizada exitosamente.");
        } else {
            System.out.println("Error: Tarea no encontrada o no se puede actualizar.");
        }
    }

    private void filterByState() {
        System.out.println("=== Filtrar por Estado ===");
        TaskState state = getStateInput("Estado (BACKLOG/ACTIVE/DONE/CANCELED/ARCHIVED): ");
        List<Task> tasks = taskService.filterByState(state);

        System.out.println("Tareas con estado " + state + ":");
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas con ese estado.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
                System.out.println("---");
            }
        }
    }

    private void filterByPriority() {
        System.out.println("=== Filtrar por Prioridad ===");
        TaskPriority priority = getPriorityInput("Prioridad (LOW/MEDIUM/HIGH/URGENT): ");
        List<Task> tasks = taskService.filterByPriority(priority);

        System.out.println("Tareas con prioridad " + priority + ":");
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas con esa prioridad.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
                System.out.println("---");
            }
        }
    }

    private void archiveTask() {
        System.out.println("=== Archivar Tarea ===");
        String id = getStringInput("ID de la tarea: ");

        boolean success = taskService.archiveTask(id);
        if (success) {
            System.out.println("Tarea archivada exitosamente.");
        } else {
            System.out.println("Error: Tarea no encontrada o no se puede archivar.");
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                if (!scanner.hasNextLine()) {
                    throw new RuntimeException("No input available");
                }
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
            }
        }
    }

    private TaskPriority getPriorityInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt).toUpperCase();
            try {
                return TaskPriority.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Prioridad inválida. Use LOW, MEDIUM, HIGH o URGENT.");
            }
        }
    }

    private TaskState getStateInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt).toUpperCase();
            try {
                return TaskState.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Estado inválido. Use BACKLOG, ACTIVE, DONE, CANCELED o ARCHIVED.");
            }
        }
    }

    public static void main(String[] args) {
        // Initialize dependencies
        TaskRepository repository = new InMemoryTaskRepository();
        TaskService service = new TaskService(repository);
        TaskCLI cli = new TaskCLI(service);

        // Run the CLI
        cli.run();
    }
}