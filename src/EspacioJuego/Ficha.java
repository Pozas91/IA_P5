package EspacioJuego;

/**
 * Representación de las fichas para los juegos.
 *  
 * @author José Miguel Horcas Aguilera
 * 
 * @version 2010
 *
 */
public class Ficha {

	private char f;			// Representación de la ficha.
	
	
	/**
	 * Constructor.
	 * 
	 * @param f	Caracter que representa la ficha.
	 */
	public Ficha (char c) {
		this.f = c;
	}
	
	/**
	 * Selector.
	 * @return	Caracter que representa la ficha.
	 */
	public char f () {
		return f;
	}
	
	public boolean equals (Object o) {
		return o instanceof Ficha && this.f() == ((Ficha) o).f();
	}
	
	public String toString () {
		return "" + f();
	}
}
