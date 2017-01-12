package ConectaK;

import EspacioJuego.Tablero;
import EspacioJuego.Ficha;
import EspacioJuego.Movimiento;

/**
 * Tablero para el juego del Conecta-K.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * 
 * @version 21-10-2013
 * 
 */
public class TableroCK extends Tablero {
    public static final Ficha pVacia = new Ficha(' ');
    
    /**
     * Constructor que crea un tablero nFil x nCol con las casillas inicialmente vacías.
     * 
     * @param nFil Número de filas del tablero.
     * @param nCol Número de columnas del tablero.
     */
    public TableroCK (int nFil, int nCol) {
        super(nFil, nCol, TableroCK.pVacia);
    }
    
    public TableroCK (Tablero tab) {
        super(tab);
    }
    
    /**
     * Inserta la ficha en la fila adecuada al soltarla por la columna c.
     * Además devuelve el movimiento realizado.
     * 
     * @param c     Columna.
     * @param ficha Ficha.
     * 
     * @return Movimiento realizado.
     */
    public Movimiento soltarFicha (int c, Ficha ficha) {
        int f = 0;
        int nFilas = nFilas();
        
        while (f < nFilas && contenido(f,c).equals(TableroCK.pVacia)) {
            f++;
        }
        if (f != 0) {
            ponerFicha(f-1,c,ficha);
            return new Movimiento(f-1, c);
        } else {
            return null;
        }
    }
    
    /**
     * 
     * @param c Columna.
     * @return  Verdadero si es posible soltar alguna ficha en la columna c.
     */
    public boolean columnaLibre (int c) {
        return contenido(0,c).equals(TableroCK.pVacia); 
    }
    
    /**
     * 
     * @param f     Fila.
     * @param c     Columna.
     * @param df    Dirección fila.
     * @param dc    Dirección columna.
     * @return      Número de fichas iguales conectadas con (f,c) en la línea definida por la dirección df, dc.
     */
    public int conectadas (int f, int c, int df, int dc) {
        Ficha ficha = contenido(f,c);
                
        return contar(f,c,df,dc,ficha) + contar(f,c,-df,-dc,ficha) - 1;
    }
    
    private int contar (int f, int c, int df, int dc, Ficha ficha) {
        int ac = 0;
        
        while (posValida(f,c) && contenido(f,c).equals(ficha)) {
            ac++;
            f = f + df;
            c = c + dc;
        }
        return ac;
    }
   
   /**
     *
     * Utiliza el método toString heredado de la clase padre, añadiendo en la parte inferior un pequeño 
     * indicador que facilita localizar visualmente la columna
     *
     */
   
    public void ver () {
        
        String res = "";
        
        res = this.toString();
        
        for (int c = 0; c < nColumnas(); c++) {
            res += "==";
        }
        res = res.substring(0, res.length()-1);
        res += '\n';
        for (int c = 0; c < nColumnas(); c++) {
            res += c%10 + " ";
        }
        res += '\n';
        
        System.out.println(res);   
    }
}
