package Jugadores;

/**
 * Alarma.
 *  
 * @author José Miguel Horcas Aguilera
 * 
 * @version 2010
 *
 */

public class Alarma {

	private long inicio;
	private long tiempo;
	
	/**
	 * Crea una alarma.
	 * 
	 * @param tiempo Tiempo.
	 */
	public Alarma(long tiempo) {
		this.inicio = System.currentTimeMillis();
		this.tiempo = tiempo*1000;
	}
	
	/**
	 * La alarma se activará después de s segundos con una precisión de (+ -) 1 seg.
	 * 
	 * @param s Número de segundos.
	 */
	public void conectar (long s) {
		inicio = System.currentTimeMillis();
		tiempo = s*1000+1000;
	}
	
	/**
	 * ¿Se activó ya la alarma?
	 * 
	 * @return Verdadero si se activó ya la alarma; falso en caso contrario.
	 */
	public boolean alarma () {
		return tiempo <= System.currentTimeMillis()-inicio;
	}
}
