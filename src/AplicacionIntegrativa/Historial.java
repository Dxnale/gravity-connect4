package AplicacionIntegrativa;

public class Historial {

   private int victoriasJ1;
   private int victoriasJ2;
   private int empates;

   public Historial() {
      this.victoriasJ1 = 0;
      this.victoriasJ2 = 0;
      this.empates = 0;
   }

   public void ingresarPartida(int vJ1, int vJ2, int empates) {
      
      this.victoriasJ1 += vJ1;
      this.victoriasJ2 += vJ2;
      this.empates += empates;

   }

   public void showHistorial(String nombre1,String nombre2) {
      
      System.out.println("\nTabla de puntajes"+"\n" + nombre1 + ": " + this.victoriasJ1 + "\n" + nombre2 + ": " + this.victoriasJ2);
      System.out.println("Empates: " + this.empates);

   }
   
}
