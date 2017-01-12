package ConectaK;

import java.io.*;

import EspacioJuego.EstadoJuego;
import Jugadores.Jugador;

/**
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * 
 * @version 21-10-2013
 *
 */
public class JugadorHumanoCK implements Jugador {

	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		ConectaK estado = (ConectaK) e;
		int c;
		
		do {
			c = pedirMovimiento();
		} while (!estado.columnaLibre(c));
		return estado.elegirSucNth(c);
	}

	/**
	 * Pide una columna sobre la que soltar una ficha.
	 * 
	 * @return Columna.
	 */
	private int pedirMovimiento () {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String linea;
		
		System.out.println("¿En qué columna soltamos la ficha?");
		try {
			linea = br.readLine(); 
		} catch (IOException e) {
			return -1;
		}
		return Integer.parseInt(linea);
	}
}
