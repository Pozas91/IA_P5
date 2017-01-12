package ConectaK;

import java.util.ArrayList;
import java.util.List;

import EspacioJuego.EstadoJuego;
import EspacioJuego.Ficha;
import EspacioJuego.Tablero;
import EspacioJuego.Movimiento;

/**
 * Juego del conecta-k con dos jugadores que usan fichas diferentes y se alternan en sus movimientos.
 * Esta clase representa los distintos estados en que puede encontrarse el juego del conecta-k.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class ConectaK implements EstadoJuego {

    private static Ficha ficha1 = new Ficha('X');	// Ficha del jugador 1.
	private static Ficha ficha2 = new Ficha('O');	// Ficha del jugador 2.
    
	private TableroCK tablero;						// Tablero del juego.
	private boolean turno1;							// ¿Le toca mover al jugador 1?
    private int longGanadora;						// Longitud ganadora (K).
	
    //Los siguientes valores se conservan por motivos de eficiencia:
    private Movimiento ultimoMov;					// Último movimiento realizado.
    private int it;									// Número de movimientos (ply) realizados (iteraciones del juego).
	
	
	/**
	 * Constructor de un estado inicial del juego (tablero vacío, le toca al primer jugador).
	 * 
	 * @param nFil 	Número de filas.
	 * @param nCol 	Número de columnas.
	 * @param k		Longitud ganadora.
	 */
	public ConectaK (int nFil, int nCol, int k) {
		turno1 = true;
		tablero = new TableroCK(nFil, nCol);
	    longGanadora = k;
		
	    ultimoMov = null;
	    it = 0;
	}
	
	/**
	 * Constructor que crea un nuevo estado.
	 * 
	 * @param it		Número de jugadas.
	 * @param turno1		¿Le toca jugar al primer jugador?
	 * @param tablero	Tablero.
	 * @param ultimoMov	Último movimiento realizado.
	 * @param k			Longitud ganadora.
	 */
	private ConectaK (int it, boolean turno1, TableroCK tablero, Movimiento ultimoMov, int k) {
		this.longGanadora = k;
		this.it = it;
		this.turno1 = turno1;
		this.tablero = tablero;
		this.ultimoMov = ultimoMov;
	}
	
	/**
	 * 
	 * @return Longitud Ganadora.
	 */
	public int longGanadora () {
		return longGanadora;
	}
	
	public Tablero tablero() {
		return tablero;
	}
	
	/**
	 * 
	 * @return Ficha del jugador al que le toca jugar.
	 */
	@Override
	public Ficha fichaActual () {
		return turno1 ? ficha1 : ficha2;
	}
	
	/**
	 * 
	 * @return Ficha del jugador contrario al que le toca jugar.
	 */
	@Override
	public Ficha fichaOtro () {
		return turno1 ? ficha2 : ficha1;
	}
	
	/**
	 * 
	 * @param c Columna.
	 * @return	Verdadero si es posible soltar una ficha en la columna c.
	 */
	public boolean columnaLibre (int c) {
		return 0 <= c && c < tablero.nColumnas() && tablero.columnaLibre(c);
	}

	@Override
	public boolean agotado() {
		return it == tablero.nFilas()*tablero.nColumnas();
	}

	@Override
	public Ficha ganador() {
		Ficha fichaGanador = null;
		
		if (ultimoMov != null) {
			int f = ultimoMov.f();
			int c = ultimoMov.c();
			int n1,n2,n3,n4;
			n1 = tablero.conectadas(f, c, 1, 0);
			n2 = tablero.conectadas(f, c, 0, 1);
			n3 = tablero.conectadas(f, c, -1, 1);
			n4 = tablero.conectadas(f, c, 1, 1);
			int max = Math.max(Math.max(Math.max(n1, n2), n3), n4);
			if (longGanadora <= max) {
				fichaGanador = fichaOtro();
			}
		}
		return fichaGanador;
	}

	@Override
	public List<EstadoJuego> hijos() {
		List<EstadoJuego> h = new ArrayList<EstadoJuego>();
		
		for (int i = 0; i < tablero.nColumnas(); i++) {
			if (columnaLibre(i)) {
				h.add(elegirSucNth(i));
			}
		}
		return h;
	}

	/**
	 * 
	 * @param c Columna.
	 * @return	Estado resultado de mover en la columna c.
	 */
	
	public EstadoJuego elegirSucNth (int c) {
		ConectaK nuevoEstado = new ConectaK(it+1, !turno1, new TableroCK(tablero), null, longGanadora);
		nuevoEstado.ultimoMov = nuevoEstado.tablero.soltarFicha(c, fichaActual());
		return nuevoEstado;
	}
		
	@Override
	public boolean turno1() {
		return turno1;
	}
	
	/**
	 *  El estado viene definido unívocamente por la situación del tablero y el turno.
	 */
	@Override
	public String toString () {
		return "\n" + tablero.toString() + "\n" + (turno1 ? "1" : "2");
	}
	
	 /**
     * Por defecto utilizaremos el código hash de la cadena generada por toString(). Por
     * ese motivo, toString() debe devolver una cadena que identifique unívocamente el
     * estado.
     * 
     * @return codigo hash del estado para indexar una tabla hash (requerido por HashMap).
     */
    
    public int hashCode ()
    {
       return this.toString().hashCode();
    }
    
    public boolean equals(Object e) {
        return (e instanceof ConectaK) && this.toString().equals(e.toString());
    }
	
	public void ver () {
		String res = "";
		
		tablero.ver();
		
		res += "It: " + it + '\n';
		res += "Longitud ganadora: " + longGanadora + '\n';
		res += "Último movimiento: " + ultimoMov + '\n';
		res += "Ficha jugador 1: " + ficha1.toString() + "  Ficha jugador 2: " + ficha2.toString() + '\n';
		res += "Le toca jugar al jugador ";
		res += turno1 ? "1" : "2";
		res += '\n';
		System.out.println(res);
	}
}
