package Jugadores;

import java.util.List;
import java.util.Random;

import EspacioJuego.EstadoJuego;

/**
 * Jugador aleatorio.
 * Este jugador puede jugar a cualquier juego que implemente la interfaz EstadoJuego.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 *
 * @versión: 21-10-2013
 *
 */
public class JugadorAleatorio implements Jugador {

	private static Random rd = new Random();
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		List<EstadoJuego> lh = e.hijos();
		int n = rd.nextInt(lh.size());
		return lh.get(n);
	}
	
}
