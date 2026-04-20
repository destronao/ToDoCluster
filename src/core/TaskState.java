package core;

/**
 * Enum que representa los posibles estados de una tarea en el sistema de gestión.
 * Los estados definen el ciclo de vida de una tarea desde su creación hasta su archivación.
 * 
 * @author Equipo de desarrollo
 * @version 1.0
 */
public enum TaskState {
	/**
	 * Estado inicial de una tarea, antes de ser iniciada.
	 */
	BACKLOG,
	
	/**
	 * Estado de una tarea que está siendo trabajada activamente.
	 */
	ACTIVE,
	
	/**
	 * Estado de una tarea que ha sido completada exitosamente.
	 */
	DONE,
	
	/**
	 * Estado de una tarea que ha sido cancelada y no se completará.
	 */
	CANCELED,
	
	/**
	 * Estado de una tarea archivada, que ya no está activa en el sistema.
	 */
	ARCHIVED
}
