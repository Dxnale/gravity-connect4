package AplicacionIntegrativa;

public class Tablero {

   //tamaño fijo del tablero como propiedad de su clase.
   private static final int FILAS = 6;
   private static final int COLUMNAS = 6;
   private char[][] tablero = new char[FILAS][COLUMNAS];

   //metodo constructor inicializa el tablero con espacios vacios.
   public Tablero() {
      for (int i = 0; i < FILAS; i++) {
         for (int j = 0; j < COLUMNAS; j++) {
            tablero[i][j] = 32; //Utilizando ASCII.
         }
      }
   }

   //metodo para mostrar por pantalla el tablero.
   public void showTablero() {

      //bucle para mostrar las coordenadas de las columnas.
      for (int i = 1; i <= COLUMNAS; i++) {
         char col = 64;
         System.out.print("  " + (char) (col + i) + " ");
      }
      System.out.println();

      //bucle para dibujar las lineas del tablero y sus coordenadas de filas.
      for (int i = 0; i < FILAS; i++) {
         System.out.print((i + 1)); //Se muestran las coordenadas de las filas.
         for (int j = 0; j < COLUMNAS; j++) {
            System.out.print("| " + tablero[i][j] + " "); //Crea las lineas verticales
         }
         System.out.println("|");
         for (int j = 0; j < COLUMNAS; j++) {
            System.out.print(" ---"); //Crea las lineas horizontales.
         }
         System.out.println();
      }
   }

   //metodo para procesar las jugadas.
   public boolean jugar(String coordenadas, char ficha) {

      //El string de coordenadas ya validadas se convierte en dos enteros.
      int coordenadaC = (int) coordenadas.charAt(0) - 65;
      int coordenadaF = (int) coordenadas.charAt(1) - 49;
      boolean movimientoValido = false; //Se utiliza un valor booleano para varificar el movimiento.

      //Si el movimiento esta dentro del rango, y la casilla esta desocupada se cambia el booleano.
      if ((coordenadaC >= 0 && coordenadaC < 6) && (coordenadaF >= 0 && coordenadaF < 6)) {
         if (tablero[coordenadaF][coordenadaC] == 32) {
            movimientoValido = true;
         } else {
            System.out.println("Casilla ocupada.");
         }
      } else {
         System.out.println("Coordenadas invalidas.");
      }

      if (movimientoValido) { //Si el booleano cambió exitosamente el movimiento se ejecuta.
         tablero[coordenadaF][coordenadaC] = ficha;
         this.showTablero(); //Se imprime el tablero antes de la gravedad.
         this.gravedad(coordenadaF, coordenadaC, ficha);//se ejecuta el metodo de gravedad.
         this.showTablero();//se imprime el tablero despues de la gravedad.
         movimientoValido = false;
         return true; //y el metodo retorna verdadero, como jugada ejecutada.
      } else { //De lo contrario, se repite la jugada.
         System.out.println("Repita su jugada.");
         return false; //y el metodo retorna false como jugada fallida.
      }

   }

   //metodo que contiene la mecanica de gravedad.
   public void gravedad(int fila, int columna, char ficha) {

      //Se asignan valores (coordenadas) con respecto al borde más cercano.
      int distArriba = fila;
      int distAbajo = FILAS - 1 - fila;
      int distIzquierda = columna;
      int distDerecha = COLUMNAS - 1 - columna;

      //Se determina la menor distancia entre todos los bordes.
      int minDistancia = Math.min(Math.min(distArriba, distAbajo), Math.min(distIzquierda, distDerecha));

      //Se ejecuta metodo para en que direccion será el movimiento.
      String mov = this.determinarMov(distArriba, distAbajo, distIzquierda, distDerecha, minDistancia);

      //Utilizando esa misma direccion se ejecutan los metodos de cada movimiento.
      switch (mov) {
         case "UP_LEFT":
            movDiagonalUpLeft(fila, columna, ficha);
            break;
         case "UP_RIGHT":
            movDiagonalUpRight(fila, columna, ficha);
            break;
         case "DOWN_LEFT":
            movDiagonalDownLeft(fila, columna, ficha);
            break;
         case "DOWN_RIGHT":
            movDiagonalDownRight(fila, columna, ficha);
            break;
         case "UP":
            movUp(fila, columna, ficha);
            break;
         case "RIGHT":
            movRight(fila, columna, ficha);
            break;
         case "DOWN":
            movDown(fila, columna, ficha);
            break;
         case "LEFT":
            movLeft(fila, columna, ficha);
            break;
         case "NONE":
            // No hay movimiento
            break;
      }

   }

   //Funcion para determinar la direccion de la gravedad.
   private String determinarMov(int distArriba, int distAbajo, int distIzquierda, int distDerecha, int minDistancia) {

      //Compara las distancias con respecto a los bordes y las diagonales, y retorna una direccion.
      if (distArriba == distIzquierda && distArriba == minDistancia) {
         return "UP_LEFT";
      } else if (distArriba == distDerecha && distArriba == minDistancia) {
         return "UP_RIGHT";
      } else if (distAbajo == distIzquierda && distAbajo == minDistancia) {
         return "DOWN_LEFT";
      } else if (distAbajo == distDerecha && distAbajo == minDistancia) {
         return "DOWN_RIGHT";
      } else if (distArriba == minDistancia) {
         return "UP";
      } else if (distAbajo == minDistancia) {
         return "DOWN";
      } else if (distDerecha == minDistancia) {
         return "RIGHT";
      } else if (distIzquierda == minDistancia) {
         return "LEFT";
      } else {
         return "NONE";
      }
   }

   /*
   Conjunto de metodos para movimiento en cada una de las direcciones.
   En ellos se verifica que la siguiente posicion en la tabla esté vacia y dentro del rango
   para luego mover la ficha en esa direccion.
    */
   private void movDiagonalUpLeft(int fila, int columna, char ficha) {
      while (fila - 1 >= 0 && columna - 1 >= 0 && tablero[fila - 1][columna - 1] == 32) {
         tablero[fila - 1][columna - 1] = ficha;
         tablero[fila][columna] = 32;
         fila--;
         columna--;

         System.out.println("La gravedad está haciendo lo suyo...");

      }
   }

   private void movDiagonalUpRight(int fila, int columna, char ficha) {
      while (fila - 1 >= 0 && columna + 1 < COLUMNAS && tablero[fila - 1][columna + 1] == 32) {
         tablero[fila - 1][columna + 1] = ficha;
         tablero[fila][columna] = 32;
         fila--;
         columna++;

         System.out.println("La gravedad está haciendo lo suyo...");
      }
   }

   private void movDiagonalDownRight(int fila, int columna, char ficha) {
      while (fila + 1 < FILAS && columna + 1 < COLUMNAS && tablero[fila + 1][columna + 1] == 32) {
         tablero[fila + 1][columna + 1] = ficha;
         tablero[fila][columna] = 32;
         fila++;
         columna++;

         System.out.println("La gravedad está haciendo lo suyo...");
      }
   }

   private void movDiagonalDownLeft(int fila, int columna, char ficha) {
      while (fila + 1 < FILAS && columna - 1 >= 0 && tablero[fila + 1][columna - 1] == 32) {
         tablero[fila + 1][columna - 1] = ficha;
         tablero[fila][columna] = 32;
         fila++;
         columna--;

         System.out.println("La gravedad está haciendo lo suyo...");
      }
   }

   private void movUp(int fila, int columna, char ficha) {
      while (fila > 0 && tablero[fila - 1][columna] == 32) {
         tablero[fila - 1][columna] = ficha;
         tablero[fila][columna] = 32;
         fila--;

         System.out.println("La gravedad está haciendo lo suyo...");
      }
   }

   private void movRight(int fila, int columna, char ficha) {
      while (columna < COLUMNAS - 1 && tablero[fila][columna + 1] == 32) {
         tablero[fila][columna + 1] = ficha;
         tablero[fila][columna] = 32;
         columna++;

         System.out.println("La gravedad está haciendo lo suyo...");
      }
   }

   private void movDown(int fila, int columna, char ficha) {
      while (fila < FILAS - 1 && tablero[fila + 1][columna] == 32) {
         tablero[fila + 1][columna] = ficha;
         tablero[fila][columna] = 32;
         fila++;

         System.out.println("La gravedad está haciendo lo suyo...");
      }
   }

   private void movLeft(int fila, int columna, char ficha) {
      while (columna > 0 && tablero[fila][columna - 1] == 32) {
         tablero[fila][columna - 1] = ficha;
         tablero[fila][columna] = 32;
         columna--;

         System.out.println("La gravedad está haciendo lo suyo...");
      }
   }
   
   //Metodo para escanear todo el tablero segun la ficha que recibe verificando cada direccion y buscando 4 fichas consecutivas.
   public boolean hayCuatroEnLinea(char ficha) {
      for (int fila = 0; fila < FILAS; fila++) {
         for (int columna = 0; columna < COLUMNAS; columna++) {
            if (tablero[fila][columna] == ficha) {
               if (verificarHorizontal(fila, columna, ficha) || verificarVertical(fila, columna, ficha) || verificarDiagonalIzqDer(fila, columna, ficha) || verificarDiagonalDerIzq(fila, columna, ficha)) {
                  return true; //si se cumple cualquiera de las condiciones en verificacion se retorna verdadero.
               }
            }
         }
      }
      return false; //Si se termina de recorrer el tablero sin coincidencias se retorna false.
   }

   
   /*Conjunto de metodos para verificar la existencia de 4 en linea segun la ficha recibida en todas direcciones.*/
   
   private boolean verificarHorizontal(int fila, int columna, char ficha) {
      if (columna + 3 < COLUMNAS) {
         return tablero[fila][columna + 1] == ficha
                 && tablero[fila][columna + 2] == ficha
                 && tablero[fila][columna + 3] == ficha;
      }
      return false;
   }

   private boolean verificarVertical(int fila, int columna, char ficha) {
      if (fila + 3 < FILAS) {
         return tablero[fila + 1][columna] == ficha
                 && tablero[fila + 2][columna] == ficha
                 && tablero[fila + 3][columna] == ficha;
      }
      return false;
   }

   private boolean verificarDiagonalIzqDer(int fila, int columna, char ficha) {
      if (fila + 3 < FILAS && columna + 3 < COLUMNAS) {
         return tablero[fila + 1][columna + 1] == ficha
                 && tablero[fila + 2][columna + 2] == ficha
                 && tablero[fila + 3][columna + 3] == ficha;
      }
      return false;
   }

   private boolean verificarDiagonalDerIzq(int fila, int columna, char ficha) {
      if (fila + 3 < FILAS && columna - 3 >= 0) {
         return tablero[fila + 1][columna - 1] == ficha
                 && tablero[fila + 2][columna - 2] == ficha
                 && tablero[fila + 3][columna - 3] == ficha;
      }
      return false;
   }

}
