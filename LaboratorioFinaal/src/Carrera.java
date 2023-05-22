

import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Carrera extends Thread{
    private JLabel etiqueta;
    private Juego1 auto;

    public Carrera(JLabel etiqueta, Juego1 auto) {
        this.etiqueta = etiqueta;
        this.auto = auto;
    }
    @Override
   public void run(){
       int auto1=0;
       int auto2=0;
       while (true){
           try{
               sleep((int)(Math.random()*1000));
               auto1=auto.getPrimerAuto().getLocation().x;
               auto2=auto.getSegundoAuto().getLocation().x;
               if(auto1<auto.getMeta().getLocation().x-125&&auto2<auto.getMeta().getLocation().x-125){
                   etiqueta.setLocation(etiqueta.getLocation().x+10,etiqueta.getLocation().y);
                   auto.repaint();  
               }else{
                   break;
               }
           }catch(Exception e){
               System.out.println(e);
           }
           if(etiqueta.getLocation().x>=auto.getMeta().getLocation().x-125){
               if (auto1>auto2){
                   JOptionPane.showMessageDialog(null, "GANÓ EL AUTO AZUL");
               }else if(auto2>auto1){
                   JOptionPane.showMessageDialog(null, "GANÓ EL AUTO ROJO");
               }
               else{
                   JOptionPane.showMessageDialog(null, "EMPATE");
               }
           }
       }
   } 
}
