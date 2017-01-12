package Jugadores;

import java.util.List;

import EspacioJuego.EstadoJuego;
import EspacioJuego.Ficha;

/**
 * Jugador que elige el siguiente movimiento evaluando los sucesores
 * de la posición actual, y propagando los valores mediante la estrategia
 * de profundización progresiva con llamadas sucesivas a MiniMax con cotas
 * de profundidad cada vez mayores. Se establece un límite de tiempo para
 * la exploración, devolviéndose el resultado de la evaluación MiniMax más
 * profunda que se pudo completar.
 * 
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * 
 * @versión: 2013-11-18
 *
 */
public class JugadorMinimaxLimitado extends JugadorEvaluar {

    private long limiteT;       // Límite de tiempo.
    
    /**
     * Constructor.
     * 
     * @param ev        Evaluador.
     * @param limiteT   Límite de tiempo.
     */
    public JugadorMinimaxLimitado (Evaluador ev, long limiteT) {
        super(ev);
        this.limiteT = limiteT;
    }
    
    @Override
    public EstadoJuego mueve(EstadoJuego e) {
        EstadoJuego resultado = minimaxLimitado(e, e.fichaActual(), e.fichaOtro());
        if (resultado == null) {
            return  barajar(e.hijos()).get(0);      // Por si no le da tiempo.
        } else {
            return resultado;
        }
    }
    
    /**
     * Realiza la evaluación MINIMAX de un estado dado un límite de tiempo. Para ello se emplea
     * una estrategia de profundización progresiva. Se realizan llamadas a negamaxConAlarma con
     * límites de profundidad crecientes. Cuando se alcanza el límite de tiempo, se devuelve el
     * resultado de la llamada más profunda que se pudo completar.
     * 
     * @param e     Estado.
     * @param fmax  Ficha de MAX.
     * @param fmin  Ficha de MIN.
     * @return      Mejor movimiento.
     */
    public EstadoJuego minimaxLimitado(EstadoJuego e, Ficha fmax, Ficha fmin) {
        Alarma a = new Alarma(limiteT);
        EstadoJuego movimiento = null;
        double valor = Double.NEGATIVE_INFINITY;
        int cota = 0;
        VE ve2;
        while (!a.alarma()) {
            ve2 = negamaxConAlarma(e, cota, fmax, fmin, a, 1);
            if (ve2 != null) {
                movimiento = ve2.e();
                valor = ve2.v();
            }
            System.out.printf("\nProf: %d , Valor: %f", cota, valor); 
            cota = cota + 1;
        }
        
        System.out.println("Profundidad máxima explorada: " + (cota - 1));
        System.out.println("Valor: " + valor);
        
        return movimiento;
    }
    
    /**
     * Realiza una evaluación MINIMAX de un estado dado en un tiempo límite. Si el tiempo se acaba
     * durante la evaluación, devuelve null.
     * 
     * 
     * @param e         Estado.
     * @param prof      Profundidad de búsqueda.
     * @param fmax      Ficha de MAX.
     * @param fmin      Ficha de MIN.
     * @param alarma    Alarma.
     * @param signo     Tomará inicialmente el valor 1, e irá alternando de signo con la profundidad.
     * @return          Objeto VE con el mejor estado sucesor y su evaluación minimax, o null si se agotó el tiempo..
     */
    public VE negamaxConAlarma (EstadoJuego e, int prof, Ficha fmax, Ficha fmin, Alarma alarma, int signo) {
        EstadoJuego mejorE = null;
        Double mejorV = Double.NEGATIVE_INFINITY;
        VE ve2;
        
        if (alarma.alarma()) {   //se acabó el tiempo
            return null; 
        } else if (prof == 0 || e.ganador()!=null || e.agotado()) {
            mejorV = signo*evalua(e, fmax, fmin);
        } else {
            for (EstadoJuego e2 : ordenar(e.hijos())) {
                ve2 = negamaxConAlarma(e2, prof-1, fmax, fmin, alarma, -signo);
                if (ve2 != null) {
                    if ((-(ve2.v()) >= mejorV) || (mejorE == null)) {
                        mejorV = -(ve2.v());
                        mejorE = e2;
                    }
                } else {
                    return null;      //se acabó el tiempo
                }
            }
        };
        return new VE (mejorV, mejorE);
    }
    
    List<EstadoJuego> ordenar (List<EstadoJuego> l) {
        return super.barajar(l);    
    }
    
}
