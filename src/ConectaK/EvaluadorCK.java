package ConectaK;


import EspacioJuego.EstadoJuego;
import EspacioJuego.Ficha;
import EspacioJuego.Tablero;
import Jugadores.Evaluador;

/**
 * Clase EvaluadorCK que implementa un evaluador heurístico para el problema del Conecta-K.
 * basado en una matriz de posibilidades.
 * La matriz de posibilidades recoge, para cada posición del tablero, las posibilidades de
 * conectar k fichas en las que interviene.
 * La evaluación se realiza de la siguiente manera: dado un estado del juego, se calcula el
 * número de posibilidades de conectar k fichas que le quedan a MAX (A), y el número de 
 * posibilidades de conectar k fichas que le quedan a MIN (I). El resultado de la evaluación
 * es A - I.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * 
 * @version 2013-10-22
 *
 */
public class EvaluadorCK implements Evaluador {

	private MatrizPosibilidades matriz;

	/**
	 * Constructor.
	 * Crea la matriz de posibilidades.
	 * 
	 * @param nFilas		Número de filas.
	 * @param nColumnas		Número de columnas.
	 * @param k				Longitud ganadora.
	 */
	public EvaluadorCK(int nFilas, int nColumnas, int k) {
		matriz = new MatrizPosibilidades(nFilas, nColumnas, k);
	}

	@Override
	public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin) {
		int n = matriz.numPosibilidades();
		Tablero tab = ((ConectaK)estado).tablero();	
		int[] posiMax = new int[n];
		int[] posiMin = new int[n];
		
		// Inicialmente se consideran todas las posibilidades abiertas (1) para ambos jugadores.
		for (int i = 0; i < n; i++) {
			posiMax[i] = 1;
			posiMin[i] = 1;
		}
		
		// A continuación se marcan aquellas posibilidades cerradas (0) por las fichas del oponente.
		for (int f = 0; f < tab.nFilas(); f++) {
			for (int c = 0; c < tab.nColumnas(); c++) {
				for (Integer op : matriz.posibilidades(f, c)) {
					if (tab.contenido(f, c).equals(fmin)) {
						posiMax[op] = 0;
					} else if (tab.contenido(f, c).equals(fmax)) {
						posiMin[op] = 0;
					}
				}
			}
		}
		return sumatorioArray(posiMax) - sumatorioArray(posiMin);
	}
	
	/**
	 * 
	 * @param a Array.
	 * @return	Suma de todos los elementos del array unidimensional a.
	 */
	private int sumatorioArray(int[] a) {
		int suma = 0;
		
		for (int i = 0; i < a.length; i++) {
			suma += a[i];
		}
		return suma;
	}
}
