package Jugadores;

import EspacioJuego.EstadoJuego;

    /**
     * Guarda una valoración double asociada a un objeto EstadoJuego.
     */
    public class VE {
       private double valor;
       private EstadoJuego estado;
             
       public VE (double v, EstadoJuego e){
           this.estado = e;
           this.valor = v;
       }
       /**
        * @return el valor asociado al objeto.
        */
       public double v(){
           return this.valor;
        }
       
       /**
        * @return el estado asociado al objeto. 
        */
       public EstadoJuego e() {
           return this.estado;
       }
    }
