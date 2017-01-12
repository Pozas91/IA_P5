package EspacioJuego;

import java.util.List;

/**
 * Representación de los estados de un juego.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * 
 * Versión: 21-10-2013
 *
 */
public interface EstadoJuego {

	/**
	 * 
	 * @return Lista de estados directamente accesibles.
	 */
	public List<EstadoJuego> hijos ();
	
	
	/**
	 * 
	 * @return Ficha del jugador que ha ganado, o null si nadie ganó.
	 */
	public Ficha ganador ();
	
	/**
	 * 
	 * @return Verdadero si el estado es final y hay empate, falso en otro caso.
	 */
	public boolean agotado ();
	
	/**
	 * 
	 * @return Verdadero si es el turno del primer jugador, falso en otro caso.
	 */
	public boolean turno1 ();
	
	/**
	 * 
	 * @return Ficha del jugador al que le toca jugar.
	 */
	public Ficha fichaActual ();
	
	/**
	 * 
	 * @return Ficha del jugador contrario al que le toca jugar.
	 */
	public Ficha fichaOtro ();
	
	/**
	 * 
	 * @return Descripción del estado. La cadena debe identificar unívocamente
	 * al estado.
	 */
	public String toString ();
	
	/**
	 * 
	 * Muestra por pantalla el estado.
	 * 
	 */
	public void ver ();
	
	
}
