package core;

/**
 * Enum que representa los niveles de prioridad de una tarea.
 * Las prioridades ayudan a organizar y priorizar el trabajo.
 * 
 * @author Equipo de desarrollo
 * @version 1.0
 */
public enum TaskPriority {
	/**
	 * Prioridad baja, para tareas no urgentes.
	 */
	LOW,
	
	/**
	 * Prioridad media, para tareas importantes pero no críticas.
	 */
	MEDIUM,
	
	/**
	 * Prioridad alta, para tareas importantes que requieren atención.
	 */
	HIGH,
	
	/**
	 * Prioridad urgente, para tareas críticas que necesitan acción inmediata.
	 */
	URGENT
}
