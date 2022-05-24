/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static java.lang.Thread.sleep;
import java.util.Calendar;
import javax.swing.JLabel;

/**
 *
 * @author Samuel David Ortiz
 */
public class Reloj1 extends Thread{
    
    private int  hora, minuto, segundo;
    boolean estado;
    JLabel lblReloj;
    
    public Reloj1(){
        
    }
    
    public String hora(){
        Calendar calendario = Calendar.getInstance();
        setHora(calendario.get(Calendar.HOUR_OF_DAY));
        setMinuto(calendario.get(Calendar.MINUTE));
        setSegundo(calendario.get(Calendar.SECOND));
        
        String hr;
        hr = null;

        hr = (getHora()<=9?"0"+getHora():getHora()) + ":" + (getMinuto()<=9?"0"+getMinuto():getMinuto()) + ":" + (getSegundo()<=9?"0"+getSegundo():getSegundo());
        return hr;
    }
    
    public void run() {
        estado = true;
        for (;;) {
            if (estado == true) {
                try {
                    sleep(1);
                    this.lblReloj.setText(hora());
                    //lblReloj.setFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("alarm clock.ttf").openStream()));
                } catch (Exception e) {

                }
            } else {
                break;
            }
        }
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }
    
    public JLabel getLblReloj() {
        return lblReloj;
    }

    public void setLblReloj(JLabel lblReloj) {
        this.lblReloj = lblReloj;
    }
    
}
