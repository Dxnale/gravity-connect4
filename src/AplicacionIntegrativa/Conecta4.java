package AplicacionIntegrativa;

import java.util.*;

public class Conecta4 {

   private static Scanner sc = new Scanner(System.in);
   Historial tablaHistorial;
   private static String name1, name2;
   private static final int JUGADAS = 36;
   private static Jugador1 j1;
   private static Jugador2 j2;
   private static int victoriasJ1;
   private static int victoriasJ2;
   private static int empates;
   private static int played;

   public Conecta4() {

      tablaHistorial = new Historial();

      //Al iniciar el juego se pide los nombres de cada jugador.
      try {
         System.out.print("Jugador 1 — Ingrese nombre: ");
         name1 = sc.nextLine();
      } catch (InputMismatchException ex) {
         System.out.println("\n\n-- Opción invalida --\n\n");
         sc.next();
      }

      j1 = new Jugador1(name1); //se guarda el nombre del primer jugador.

      try {
         System.out.print("Jugador 2 — Ingrese nombre: ");
         name2 = sc.nextLine();
      } catch (InputMismatchException ex) {
         System.out.println("\n\n-- Opción invalida --\n\n");
         sc.next();
      }

      j2 = new Jugador2(name2);//se guarda el nombre del segundo jugador.

      //Se imprimen los nombres y las fichas asignadas a cada jugador.
      System.out.println("Jugador 1 " + j1.getNombre() + " su ficha: " + j1.getFICHA());
      System.out.println("Jugador 2 " + j2.getNombre() + " su ficha: " + j2.getFICHA());
      System.out.println("");

   }

   //metodo para facilitar el manejo de errores y entradas incorrectas.
   public String ingresoCoordenada() {
      String coordenada = new String();
      boolean valid = false;
      while (!valid) {
         try {
            System.out.print("Ingrese coordenadas: ");
            coordenada = sc.nextLine().toUpperCase();
            char character = coordenada.charAt(0);
            char characte2 = coordenada.charAt(1);
            valid = true;  // Si no hay error, salir del bucle
         } catch (StringIndexOutOfBoundsException e) {
            System.out.println();
         } catch (Exception e) {
            System.out.println();
         }
      }
      return coordenada; //una vez validadas las coordenadas se entrega como resultado del metodo.
   }

   public boolean play() {

      Tablero tablero = new Tablero(); //creacion del tablero en blanco.
      tablero.showTablero();

      System.out.println("Comienza el juego");

      //Se inicializan las variables de conteo.
      played = 0;
      victoriasJ1 = 0;
      victoriasJ2 = 0;
      empates = 0;

      //el primer bucle establece una cantidad limitada de jugadas por tablero.
      for (int i = 0; i < JUGADAS; i++) {
         System.out.println("Jugada: " + i);
         //para que se cumplan los turnos si el indice del bucle es par es turno del jugador uno.
         if (i % 2 == 0) {
            boolean result;
            do {
               System.out.println("Turno de " + j1.getNombre());
               String coordenada = this.ingresoCoordenada();//mediante el metodo de ingreso, se solicitan y validan las coordenadas.
               //si el metodo jugar resulta en un movimiento invalido se vuelve al bucle hasta que se ejecute un movimiento valido.
               result = tablero.jugar(coordenada, j1.getFICHA());
            } while (!result);

            j1.setJugadas(j1.getJugadas() - 1);
            if (tablero.hayCuatroEnLinea(j1.getFICHA())) {//Se escanea el tablero en busca de jugadas ganadoras.
               victoriasJ1 += 1; //ACA VA EL REGISTRO DEL GANADOR.
               break;
            }

         } else { //Si es impar es turno del jugador 2.
            boolean result;
            do {
               System.out.println("Turno de " + j2.getNombre());
               String coordenada = this.ingresoCoordenada();//mediante el metodo de ingreso, se solicitan y validan las coordenadas.
               //si el metodo jugar resulta en un movimiento invalido se vuelve al bucle hasta que se ejecute un movimiento valido.
               result = tablero.jugar(coordenada, j2.getFICHA());
            } while (!result);

            j2.setJugadas(j2.getJugadas() - 1);
            if (tablero.hayCuatroEnLinea(j2.getFICHA())) {//Se escanea el tablero en busca de jugadas ganadoras.
               victoriasJ2 += 1; //ACA VA LA REGISTRO DEL GANADOR.
               break;
            }
         }
         played += i; //Al terminar el bucle de jugadas se guarda la cantidad de jugadas totales.
      }

      if (played == JUGADAS - 1) { //Si la cantidad de jugadas totales se ha agotado sin un ganador es un empate.
         System.out.println("Empate");
         empates++;
      }

      //ACA VA LA LLAMADA AL HISTORIAL DE VICTORIAS Y DERROTAS.
      tablaHistorial.ingresarPartida(victoriasJ1, victoriasJ2, empates);
      tablaHistorial.showHistorial(j1.getNombre(), j2.getNombre()); //se imprime el historial

      System.out.println("¿Desea volver a jugar?");
      return this.playAgain(); //se utiliza como valor booleano el metodo para consultar si desea jugar nuevamente.

   }

   //Metodo que consulta al usuario si desea jugar nuevamente.
   public boolean playAgain() { //Se implementa un menu de dos opciones para validar el ingreso y que el metodo pueda retornar true o false.
      int menu;
      boolean valido = true;
      do {
         try {
            System.out.println("""
                         1- Volver a jugar
                         2- Salir
                         """);
            menu = sc.nextInt();
            switch (menu) {
               case 1:
                  return true;
               case 2:
                  valido = false;
                  return false;
               default:
                  System.out.println("\n\n-- Opción invalida --\n\n");
                  break;
            }

         } catch (InputMismatchException ex) {
            System.out.println("\n\n-- Opción invalida --\n\n");
            sc.nextLine();
         }
      } while (valido);
      return false;
   }

}
