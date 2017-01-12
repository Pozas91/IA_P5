package Jugadores;

import EspacioJuego.EstadoJuego;

/**
 * Representación de un jugador.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 *
 * @versión: 2010
 *
 */
public interface Jugador {

	/**
	 * 
	 * Se supone que los jugadores son legales, en el sentido de que devolverán
	 * un sucesor legal del estado e.
	 * 
	 * @param e Estado.
	 * @return	Estado resultante de que el jugador mueva en el estado e.
	 */
	public EstadoJuego mueve (EstadoJuego e);
	
}
