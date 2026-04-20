package tests;

import core.Task;
import core.TaskPriority;
import core.TaskState;

public class TaskTests {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        Task.resetCounter();
        
        Task t1, t2, t3, t4, t5;

        t1 = new Task();
        t2 = new Task("Prueba 2");
        t3 = new Task("Prueba 3", "Esta tiene descripcion");
        t4 = new Task("Prueba 4", TaskPriority.MEDIUM);
        t5 = new Task("Prueba 5", "Esta tiene descripcion y prioridad urgente", TaskPriority.URGENT);

        t1.setTitle("Prueba 1");
        assertEquals("Prueba 1", t1.getTitle(), "t1 title debe ser Prueba 1");

        t2.setPriority(TaskPriority.HIGH);
        assertEquals(TaskPriority.HIGH, t2.getPriority(), "t2 debe tener prioridad HIGH");

        assertTrue(t3.cancel(), "t3 debe poder ser cancelada desde BACKLOG");
        assertEquals(TaskState.CANCELED, t3.getState(), "t3 debe estar en estado CANCELED después de cancelar");
        assertTrue(!t3.complete(), "t3 no debe completarse cuando ya está cancelada");

        t4.setDescription("Le hemos puesto descripcion");
        assertEquals("Le hemos puesto descripcion", t4.getDescription(), "t4 debe actualizar la descripción");
        assertTrue(t4.start(), "t4 debe iniciar correctamente");
        assertEquals(TaskState.ACTIVE, t4.getState(), "t4 debe estar en estado ACTIVE después de iniciar");

        assertTrue(!t5.complete(), "t5 no debe completarse antes de iniciar");
        assertTrue(t5.start(), "t5 debe iniciar correctamente");
        assertTrue(!t5.start(), "t5 no debe iniciar dos veces seguidas");
        assertTrue(t5.complete(), "t5 debe completarse después de iniciar");
        assertTrue(!t5.complete(), "t5 no debe completarse dos veces seguidas");
        assertEquals(TaskState.DONE, t5.getState(), "t5 debe quedar en estado DONE");

        System.out.println();
        System.out.println("=== Resultados de TaskTests ===");
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
