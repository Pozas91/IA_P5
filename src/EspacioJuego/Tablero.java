package EspacioJuego;


/**
 * Representación de un tablero bidimensional básico para juegos.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * 
 * @version 08-10-2013
 *
 */
public class Tablero {

	private Ficha[][] matriz;	// Matriz.
		
	/**
	 * Constructor que crea un tablero nFil x nCol con las casillas inicialmente vacías.
	 * 
	 * @param nFil Número de filas del tablero.
	 * @param nCol Número de columnas del tablero.
	 * @param ini  Contenido de las casillas vacías.
	 */
	public Tablero (int nFil, int nCol, Ficha ini) {
		matriz = new Ficha[nFil][nCol];
		inicializar(ini);
	}
	
	/**
	 * Constructor de copia.
	 * 
	 * @param tab Tablero.
	 */
	public Tablero (Tablero tab) {
		matriz = new Ficha[tab.nFilas()][tab.nColumnas()];
		for (int f = 0; f < tab.nFilas(); f++) {
			System.arraycopy(tab.matriz[f], 0, matriz[f], 0, tab.nColumnas());
		}
	}
	
	/**
	 * Inicializa todas las posiciones del tablero con la ficha dada.
	 * 
	 * @param fIni ficha inicial de todas las posiciones del tablero.
	 */
	private void inicializar (Ficha fIni) {
		for (int f = 0; f < nFilas(); f++) {
			for (int c = 0; c < nColumnas(); c++) {
				matriz[f][c] = fIni;
			}
		}
	}
	
	/**
	 * 
	 * @return Número de filas del tablero.
	 */
	public int nFilas () {
		return matriz.length;
	}
	
	/**
	 * 
	 * @return Número de columnas del tablero.
	 */
	public int nColumnas () {
		return matriz[0].length;
	}
	
	/**
	 * 
	 * @param f Fila.
	 * @param c Columna.
	 * @return	Contenido del tablero en la fila f, columna c.
	 */
	public Ficha contenido (int f, int c) {
		return matriz[f][c];
	}
	
	/**
	 * 
	 * @param f Fila.
	 * @param c Columna.
	 * @return	Verdadero si (f,c) es una posición del tablero.
	 */
	public boolean posValida (int f, int c) {
		return f >= 0 && f < nFilas() && c >= 0 && c < nColumnas();
	}
	
	/**
	 * Pone la ficha en la posición (f,c) del tablero.
	 * 
	 * @param f		Fila.
	 * @param c		Columna.
	 * @param ficha	Ficha.
	 */
	public void ponerFicha (int f, int c, Ficha ficha) {
		matriz[f][c] = ficha;
	}
	
	public String toString () {
		String res = "";
		
		for (int f = 0; f < nFilas(); f++) {
			for (int c = 0; c < nColumnas(); c++) {
				res += matriz[f][c].toString() + " ";
			}
			res += '\n'; 
		}
		return res;
	}
	public void ver () {
		System.out.println(this.toString());
	}
	
}