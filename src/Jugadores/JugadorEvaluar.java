package Jugadores;

import java.util.Collections;
import java.util.List;

import EspacioJuego.EstadoJuego;
import EspacioJuego.Ficha;

/**
 * Jugador que elige el siguiente movimiento evaluando los sucesores
 * inmediatos a la posición actual.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * 
 * @versión: 2013-11-18
 *
 */
public class JugadorEvaluar implements Jugador {
	
	public Evaluador evaluador;
	
	public JugadorEvaluar(Evaluador ev) {
		evaluador = ev;
	}
	
	/**
	 * 
	 * Genera todos los hijos del estado recibido, y devuelve el que reciba una mayor
	 * evaluación. La lista de hijos se baraja antes de analizarla para evitar un comportamiento
	 * determinista, es decir, si hay varios hijos con la evaluación óptima, distintas
	 * llamada al método pueden devolver distintos hijos óptimos.
	 * 
	 * @param e el estado del juego a partir del cual debe elegirse el movimiento.
	 * 
	 * @return el estado del juego resultante a la realización del movimiento elegido.
	 */
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		EstadoJuego mejorE = null;
		double mejorV = Double.NEGATIVE_INFINITY;
		double v2;
		
		for (EstadoJuego e2 : barajar(e.hijos())) {
			v2 = evalua(e2, e.fichaActual(), e.fichaOtro());
			if ((v2 > mejorV)|| (mejorE == null)) {
				mejorV = v2;
				mejorE = e2;
			}
		}
		return mejorE;
	}
	
	/**
	 * Función de evaluación válida para cualquier juego que implemente la interfaz EstadoJuego. 
	 * Devuelve un valor entre Double.NEGATIVE_INFINITY y Double.POSITIVE_INFINITY.
	 *  
	 * @param e				Estado del juego.
	 * @param fmax			Ficha de MAX.
	 * @param fmin			Ficha de MIN.
	 * @param evaluador		Evaluador heurístico.
	 * @return				Double.POSITIVE_INFINITY si el estado es final y ganador para MAX.
	 * 						Double.NEGATIVE_INFINITY si el estado es final y perdedor para MAX.
	 * 						El valor de la función heurística en otro caso.
	 */
	public double evalua (EstadoJuego e, Ficha fmax, Ficha fmin) {
		Ficha ganador = e.ganador();
		double resultado;
		
		if (ganador == null) {
			resultado = evaluador.evaluacion(e, fmax, fmin);
		} else if (ganador.equals(fmax)) {
			resultado = Double.POSITIVE_INFINITY;
		} else {
			resultado = Double.NEGATIVE_INFINITY;
		}
		return resultado;
	}

	/**
	 * Baraja aleatoriamente una lista de EstadoJuego.
	 * 
	 * @param l Lista de EstadoJuego.
	 * @return	Lista barajada aleatoriamente.
	 */
	List<EstadoJuego> barajar (List<EstadoJuego> l) {
		Collections.shuffle(l);
		return l;
	}
}
