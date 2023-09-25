package AplicacionIntegrativa;

public class Jugador2 {

   private final char FICHA = 79;
   private String nombre;
   private int jugadas;

   public Jugador2(String nombre) {
      this.nombre = nombre;
      this.jugadas = 18;
   }

   public void setJugadas(int jugadas) {
      this.jugadas = jugadas;
   }

   public int getJugadas() {
      return jugadas;
   }

   public char getFICHA() {
      return FICHA;
   }

   public String getNombre() {
      return nombre;
   }

}
