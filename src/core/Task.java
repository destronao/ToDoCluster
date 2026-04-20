package core;

public class Task {
	// (Fecha limite,... )
	
	//  toString()
	// estaPendiente, estaCompletada...
	
	
	
	private static int counter = 1;

	private final TaskState DEFAULT_STATE = TaskState.BACKLOG;
	private final TaskPriority DEFAULT_PRIORITY = TaskPriority.LOW;
	
	private String id;
	private String title;
	private String description; 
	private TaskState state;
	private TaskPriority priority;
	
	
	public Task() {
		id = generateId();
		title = "Untitled";
		description = "";
		state = DEFAULT_STATE;
		priority = DEFAULT_PRIORITY;
	}
	
	public Task(String title) {
		id = generateId();
		this.title = title;
		description = "";
		state = DEFAULT_STATE;
		priority = DEFAULT_PRIORITY;
	}
	
	public Task(String title, String description) {
		id = generateId();
		this.title = title;
		this.description = description;
		state = DEFAULT_STATE;
		priority = DEFAULT_PRIORITY;
	}
	
	public Task(String title, TaskPriority priority) {
		id = generateId();
		this.title = title;
		description = "";
		state = DEFAULT_STATE;
		this.priority = priority;
	}
	
	public Task(String title, String description, TaskPriority priority) {
		id = generateId();
		this.title = title;
		this.description = description;
		state = DEFAULT_STATE;
		this.priority = priority;
	}

	private String generateId() {
		return String.format("T%04d", counter++);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public String getId() {
		return id;
	}

	public TaskState getState() {
		return state;
	}
	
	public boolean start() {
		if (state != TaskState.BACKLOG) {
			System.out.println("La tarea ya esta iniciada.");
			return false;
		} 

		System.out.println("Tarea iniciada");
		state = TaskState.ACTIVE;
		return true;
	}
	
	public boolean complete() {
		if (state == TaskState.BACKLOG) {
			System.out.println("La tarea no fue iniciada aun.");
			return false;
		}
		if (state == TaskState.CANCELED) {
			System.out.println("La tarea fue cancelada no se pudo completar.");
			return false;
		}
		if (state == TaskState.DONE) {
			System.out.println("La tarea ya estaba completada.");
			return false;
		}

		System.out.println("Tarea completada");
		state = TaskState.DONE;
		return true;
	}
	
	public boolean cancel() {
		if (state == TaskState.DONE) {
			System.out.println("Imposible cancelar la tarea ya se completó.");
			return false;
		}

		System.out.println("Tarea cancelada");
		state = TaskState.CANCELED;
		return true;
	}
	
	public boolean archive() {
		if (state == TaskState.ARCHIVED) {
			System.out.println("La tarea ya está archivada.");
			return false;
		}
		if (state == TaskState.BACKLOG || state == TaskState.ACTIVE) {
			System.out.println("No se puede archivar una tarea que no está completada o cancelada.");
			return false;
		}

		System.out.println("Tarea archivada");
		state = TaskState.ARCHIVED;
		return true;
	}

	@Override
	public String toString() {
		return "[Task " + id + "] - " + title + " - " + priorityToString() + " - " + stateToString()
				+ "\n" + description;
						
	}
	
	private String priorityToString () {
		if (priority == TaskPriority.LOW) {
			return "Prioridad baja";
		}
		if (priority == TaskPriority.MEDIUM) {
			return "Prioridad media";
		}
		if (priority == TaskPriority.HIGH) {
			return "Prioridad alta";
		}
		return "Prioridad urgente";
	}
	
	private String stateToString() {
		if (state == TaskState.BACKLOG) {
			return "No iniciada";
		}
		if (state == TaskState.ACTIVE) {
			return "En proceso";
		}
		if (state == TaskState.DONE) {
			return "Completada";
		}
		if (state == TaskState.CANCELED) {
			return "Cancelada";
		}
		return "Archivada";
	}
	
}
