/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursivitat;

/**
 *
 * @author Tomeu
 */
    public class Recursivitat {
    final int numFilas;
    final int numColumnas;
    int[][] tablero;
    int     contador;
 
    public Recursivitat(int nf) {
        numFilas    = nf;
        numColumnas = nf;
        tablero     = new int[nf][nf];
    }
 
    public void mostrarTablero() {
        for(int i=0; i<tablero.length; i++) {
            for(int j=0; j<tablero[i].length; j++) {
                if(tablero[i][j]>0) System.out.printf("  %2d  |", tablero[i][j]);
                else System.out.printf("      |", tablero[i][j]);
            }
            System.out.println();
            for(int j=0; j<tablero[i].length; j++) System.out.print("------+");
            System.out.println();
        }
    }
 
    public boolean resolverProblema(int f, int c, int num) {
            contador++;
            tablero[f][c] = num;
            if(num==numFilas) return true;
            int[][] posibles = buscarPosibles();
            for(int i=0; i<posibles.length; i++) {
                if(resolverProblema(posibles[i][0], posibles[i][1], num+1)) return true;
            }
            tablero[f][c]=0;
            return false;
    }
 
    int[][] buscarPosibles() {
        int[][] resp = new int[numFilas*numColumnas][2];
        int[] sumaFilas = new int[numColumnas];
        int[] sumaColumnas = new int[numFilas];
        int     pos  = 0;
        for(int i=0; i<numFilas; i++) {
            for(int j=0; j<numColumnas; j++) {
                if(tablero[i][j]>0) {
                    sumaFilas[i]++;
                    sumaColumnas[j]++;
                }
            }
        }
        for(int i=0; i<numFilas; i++) {
            if(sumaFilas[i]>0) continue;
            for(int j=0; j<numColumnas; j++) {
                if(sumaColumnas[j]>0) continue;
                if(i>0 && j>0             && tablero[i-1][j-1] > 0) continue;
                if(i>0 && j<numColumnas-1 && tablero[i-1][j+1] > 0) continue;
                if(i<numFilas-1 && j>0    && tablero[i+1][j-1] > 0) continue;
                if(i<numFilas-1 && j<numColumnas-1 && tablero[i+1][j+1] > 0) continue;
                resp[pos][0]=i;
                resp[pos][1]=j;
                pos++;
            }
        }
        int[][] tmp = new int[pos][2];
        for(int i=0; i<pos; i++) { tmp[i][0] = resp[i][0]; tmp[i][1]=resp[i][1]; }
        return tmp;
    }
 
    boolean esValido(int f, int c) {
        if(f<0 || f>numFilas-1 || c<0 || c>numColumnas-1) return false;
        if(tablero[f][c]!=0) return false;
        return true;
    }
 
    public static void main(String[] args) {
        java.util.Random rnd  = new java.util.Random();
        int numFilas      = 8;
        int filaInicial   = rnd.nextInt(numFilas);
        int colInicial    = rnd.nextInt(numFilas);
        Recursivitat pr = new Recursivitat(8);
        pr.resolverProblema(1, 1,1);
        pr.mostrarTablero();
        System.out.printf("Cantidad de veces que entra al ciclo: %,d %n",  pr.contador);
    }
}
    
