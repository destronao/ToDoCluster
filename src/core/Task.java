package core;

/**
 * Representa una tarea en el sistema de gestión de tareas.
 * Una tarea tiene un identificador único, título, descripción, estado y prioridad.
 * Proporciona métodos para gestionar el ciclo de vida de la tarea, incluyendo
 * iniciar, completar, cancelar y archivar.
 * 
 * @author Equipo de desarrollo
 * @version 1.0
 */
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
	
	
	/**
	 * Constructor por defecto. Crea una tarea sin título con valores predeterminados.
	 */
	public Task() {
		id = generateId();
		title = "Untitled";
		description = "";
		state = DEFAULT_STATE;
		priority = DEFAULT_PRIORITY;
	}
	
	/**
	 * Constructor con título. Crea una tarea con el título especificado y valores predeterminados para el resto.
	 * 
	 * @param title el título de la tarea
	 */
	public Task(String title) {
		id = generateId();
		this.title = title;
		description = "";
		state = DEFAULT_STATE;
		priority = DEFAULT_PRIORITY;
	}
	
	/**
	 * Constructor con título y descripción. Crea una tarea con título y descripción especificados.
	 * 
	 * @param title el título de la tarea
	 * @param description la descripción de la tarea
	 */
	public Task(String title, String description) {
		id = generateId();
		this.title = title;
		this.description = description;
		state = DEFAULT_STATE;
		priority = DEFAULT_PRIORITY;
	}
	
	/**
	 * Constructor con título y prioridad. Crea una tarea con título y prioridad especificados.
	 * 
	 * @param title el título de la tarea
	 * @param priority la prioridad de la tarea
	 */
	public Task(String title, TaskPriority priority) {
		id = generateId();
		this.title = title;
		description = "";
		state = DEFAULT_STATE;
		this.priority = priority;
	}
	
	/**
	 * Constructor completo. Crea una tarea con título, descripción y prioridad especificados.
	 * 
	 * @param title el título de la tarea
	 * @param description la descripción de la tarea
	 * @param priority la prioridad de la tarea
	 */
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

	/**
	 * Reinicia el contador de IDs. Útil para pruebas.
	 */
	public static void resetCounter() {
		counter = 1;
	}

	/**
	 * Obtiene el título de la tarea.
	 * 
	 * @return el título de la tarea
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Establece el título de la tarea.
	 * 
	 * @param title el nuevo título de la tarea
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Obtiene la descripción de la tarea.
	 * 
	 * @return la descripción de la tarea
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Establece la descripción de la tarea.
	 * 
	 * @param description la nueva descripción de la tarea
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtiene la prioridad de la tarea.
	 * 
	 * @return la prioridad de la tarea
	 */
	public TaskPriority getPriority() {
		return priority;
	}

	/**
	 * Establece la prioridad de la tarea.
	 * 
	 * @param priority la nueva prioridad de la tarea
	 */
	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	/**
	 * Obtiene el identificador único de la tarea.
	 * 
	 * @return el ID de la tarea
	 */
	public String getId() {
		return id;
	}

	/**
	 * Obtiene el estado actual de la tarea.
	 * 
	 * @return el estado de la tarea
	 */
	public TaskState getState() {
		return state;
	}
	
	/**
	 * Establece el estado de la tarea.
	 * 
	 * @param state el nuevo estado de la tarea
	 */
	public void setState(TaskState state) {
		this.state = state;
	}
	
	/**
	 * Inicia la tarea, cambiando su estado de BACKLOG a ACTIVE.
	 * Solo puede iniciarse si está en estado BACKLOG.
	 * 
	 * @return true si la tarea se inició correctamente, false en caso contrario
	 */
	public boolean start() {
		if (state != TaskState.BACKLOG) {
			System.out.println("La tarea ya esta iniciada.");
			return false;
		} 

		System.out.println("Tarea iniciada");
		state = TaskState.ACTIVE;
		return true;
	}
	
	/**
	 * Completa la tarea, cambiando su estado a DONE.
	 * Solo puede completarse si no está en BACKLOG, CANCELED o ya DONE.
	 * 
	 * @return true si la tarea se completó correctamente, false en caso contrario
	 */
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
	
	/**
	 * Cancela la tarea, cambiando su estado a CANCELED.
	 * No puede cancelarse si ya está DONE.
	 * 
	 * @return true si la tarea se canceló correctamente, false en caso contrario
	 */
	public boolean cancel() {
		if (state == TaskState.DONE) {
			System.out.println("Imposible cancelar la tarea ya se completó.");
			return false;
		}

		System.out.println("Tarea cancelada");
		state = TaskState.CANCELED;
		return true;
	}
	
	/**
	 * Archiva la tarea, cambiando su estado a ARCHIVED.
	 * Solo puede archivarse si está en estado DONE o CANCELED.
	 * 
	 * @return true si la tarea se archivó correctamente, false en caso contrario
	 */
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

	/**
	 * Devuelve una representación en cadena de la tarea, incluyendo ID, título, prioridad y estado.
	 * 
	 * @return la representación en cadena de la tarea
	 */
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
