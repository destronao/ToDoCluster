package tests;

import core.Task;
import core.TaskPriority;

public class TaskTests {

	public static void main(String[] args) {
		Task t1, t2, t3, t4, t5;
		
		t1 = new Task();
		t2 = new Task("Prueba 2");
		t3 = new Task("Prueba 3", "Esta tiene descripcion");
		t4 = new Task("Prueba 4", TaskPriority.MEDIUM);
		t5 = new Task("Prueba 5", "Esta tiene descripcion y prioridad urgente", TaskPriority.URGENT);
		
		t1.setTitle("Prueba 1");
		t2.setPriority(TaskPriority.HIGH);
		t3.cancel();
		t3.complete();
		t4.setDescription("Le hemos puesto descripcion");
		t4.start();
		t5.complete();
		t5.start();
		t5.start();
		t5.complete();
		t5.complete();
		
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		System.out.println(t3.toString());
		System.out.println(t4.toString());
		System.out.println(t5.toString());
	}

}
