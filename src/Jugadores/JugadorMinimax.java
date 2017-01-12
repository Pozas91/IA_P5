package Jugadores;

import java.util.List;

import EspacioJuego.EstadoJuego;
import EspacioJuego.Ficha;


/**
 * Jugador que elige el siguiente movimiento evaluando los sucesores
 * de la posición actual a una profundidad dada, y propagando los
 * valores mediante la estrategia MiniMax.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * 
 * @versión: 2013-10-22
 *
 */
public class JugadorMinimax extends JugadorEvaluar {

    private int profMax;            // Profundidad máxima de búsqueda.
    
    /**
     * Constructor.
     * 
     * @param ev                Evaluador.
     * @param profundidadMaxima Profundidad máxima de búsqueda.
     */
    public JugadorMinimax(Evaluador ev, int profundidadMaxima) {
        super(ev);
        this.profMax = profundidadMaxima;
    }
    
    @Override
    public EstadoJuego mueve(EstadoJuego e) {
        VE resultado = negamax(e, this.profMax, e.fichaActual(), e.fichaOtro(), 1);
        
        System.out.println("Evaluación del movimiento: " + resultado.v());
        
        return resultado.e();
    }
    
  
    /**
     * Realiza una evaluación MINIMAX de un estado dado a una profundidad dada.
     * El cálculo se realiza mediante una función recursiva por la cola que implementa
     * el método de cálculo conocido como NEGAMAX: en cada nivel se calcula siempre el
     * máximo de los sucesores cambiado de signo, pero las evaluaciones se van cambiando
     * de signo según la profundidad, de modo que en los niveles MIN, calcular el máximo 
     * cambiado de signo de las evaluaciones cambiadas de signo equivale a calcular el mínimo.
     * 
     * @param e     Estado del juego.
     * @param prof  Profundiad de la búsqueda.
     * @param fmax  Ficha del jugador MAX.
     * @param fmin  Ficha del jugador MIN.
     * @param signo Tomará inicialmente el valor 1, e irá alternando de signo con la profundidad.
     * @return      Objeto VE con el mejor estado sucesor y su valoración minimax.
     */
    
    public VE negamax (EstadoJuego e, int prof, Ficha fmax, Ficha fmin, int signo) {
        EstadoJuego mejorE = null;
        double mejorV = Double.NEGATIVE_INFINITY;
        double v2;
        
        if (prof == 0 || e.ganador()!=null || e.agotado()) {
            mejorV = signo*evalua(e, fmax, fmin);
        } else {
            for (EstadoJuego e2 : ordenar(e.hijos())) {
                v2 = -(negamax(e2, prof-1, fmax, fmin, -signo).v());
                if ((v2 > mejorV) || (mejorE == null)) {
                    mejorV = v2;
                    mejorE = e2;
                }
            }
        }
        return new VE(mejorV, mejorE);
    }
    
    List<EstadoJuego> ordenar (List<EstadoJuego> l) {
        return super.barajar(l);    
    }
}
