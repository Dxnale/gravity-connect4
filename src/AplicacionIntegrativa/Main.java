package AplicacionIntegrativa;

public class Main {
   
   public static boolean res;

   public static void main(String[] args) {
      
      //Se inicia el juego (se solicitan nombres de jugadores).
      Conecta4 conecta4 = new Conecta4();   
      
      do { //Se ejecuta el juego mientras devuelva un valor true (desea seguir jugando).
        res = conecta4.play();
      } while (res);
      
   }
}
