package Jugadores;


import EspacioJuego.EstadoJuego;
import EspacioJuego.Ficha;
import java.util.Map;
import java.util.Hashtable;

/**
 * Clase EvaluadorTV.
 * Para aprendizaje con refuerzo utilizando una tabla de valor. La tabla se implementa mediante una
 * tabla hash indexada por EstadoJuego que contiene las estimaciones de valor correspondientes.
 * 
 * @author Lorenzo Mandow
 * @version 2013-11-22
 */
public class EvaluadorTV implements Evaluador {

    /**
        @important
        Para cada estado visitado debemos guardar el valor, y este valor se irá actualizando
        a medida que vaya pasando por el mismo estado
     */

    public Map<EstadoJuego,Double> tv;   //tabla de valor
    
    public static double VICTORIA = 1.0;
    public static double EMPATE = 0.1;      //Tenemos una ligera querencia por el empate.
    public static double DERROTA = -1.0;

    /**
        @important
        Se puede cambiar el valor alfa que es la tasa de aprendizaje
     */
    private double alfa = 0.05;

    /**
     * Constructor.
     */
    public EvaluadorTV() {
        this.tv = new Hashtable<EstadoJuego,Double>();
    }
    
    public EvaluadorTV(double alfa) {
        this.tv = new Hashtable<EstadoJuego,Double>();
        this.alfa = alfa;
    }

    @Override
    public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin) {
        return this.valor(estado);
        }

    /**
     * @description
     *  Devuelve el valor para el estado indicado como parámetro
     * @param estado
     * @return
     */
    public double valor(EstadoJuego estado){
        Double resultado = tv.get(estado);  //recompensa esperada
        if (resultado != null){
            return resultado;
        } else {
            return EMPATE;
        }
    }
    
    public void esVictoria(EstadoJuego estado){
        tv.put(estado, VICTORIA);
    }
    
    public void esDerrota(EstadoJuego estado){
        tv.put(estado, DERROTA);
    }
    
    public void esEmpate(EstadoJuego estado){
        tv.put(estado, EMPATE);
    }

    /**
        @description
        Esta función es la que actualiza el valor del estado (La famosa fórmula)
     * @param estado
     * @param sucesor
     */
    public void actualizaDT(EstadoJuego estado, EstadoJuego sucesor){
        double valor = this.valor(estado);  //valor actual de estado  
        //actualización por diferencias temporales:
        this.tv.put(estado, valor + alfa *(this.valor(sucesor) - valor)); 
    }
}
