package Jugadores;

import EspacioJuego.EstadoJuego;
import EspacioJuego.Ficha;

/**
 * Interfaz Evaluador que deben implementar los objetos evaluadores heurísticos.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * 
 * @versión: 2013-10-22
 *
 */
public interface Evaluador {

	/**
	 * 
	 * @param estado	Estado del juego.
	 * @param fmax		Ficha de MAX.
	 * @param fmin		Ficha de MIN.	
	 * @return			Evaluación del estado suponiendo que MAX juega con la ficha fmax y MIN con fmin.
	 */
	public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin);

}
