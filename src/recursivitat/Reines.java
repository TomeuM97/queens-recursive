package recursivitat;

public class Reines {
    final int numFilas;
    int[]   tablero;
    int     contador;
    int     correctas=0;
 
    public Reines(int nf) {
        numFilas    = nf;
        tablero     = new int[nf];
        for(int i=0; i<tablero.length; i++) tablero[i]=-1;
    }
 
    public void mostrarTablero() {
        for(int i=0; i<tablero.length; i++) {
            for(int j=0; j<tablero.length; j++) {
                if(tablero[i]==j) System.out.printf("  %2d  |", tablero[i]+1);
                else System.out.printf("      |");
            }
            System.out.println();
            for(int j=0; j<tablero.length; j++) System.out.print("------+");
            System.out.println();
        }
        System.out.println();
    }
 
    public boolean resolverProblema(int fila, int columna) {
        //mostrarTablero();
        contador++;
        tablero[fila]=columna;
        correctas++;
        if(correctas==numFilas) return true;
        int[] posibles = buscarPosibles(fila+1);
        for(int c: posibles) {
            if(resolverProblema(siguienteFila(), c)) return true;
        }
        tablero[fila]=-1;
        correctas--;
        return false;
    }
 
    public int siguienteFila() {
        for(int i=0; i<tablero.length; i++) if(tablero[i]==-1) return i;
        return -1;
    }
 
    int[] buscarPosibles(int num) {
        int[] resp = new int[numFilas];
        int pos=numFilas;
        for(int i=0; i<resp.length; i++) resp[i]=i;
        for(int i=0; i<numFilas; i++) {
            if(tablero[i]!=-1) {
                resp[tablero[i]]=-1;
                pos--;
            }
        }
        for(int i=0; i<resp.length; i++) {
            if(resp[i]==-1) continue;
            for(int j=0; j<num; j++) {
                double fabs = Math.abs((num-j)*1.0/(resp[i]-tablero[j]));
                if(fabs==1.0 && resp[i]!=-1) {
                    resp[i]=-1;
                    pos--;
                    break;
                }
            }
        }
        int[] tmp = new int[pos];
        int i=0;
        for(int x: resp) if(x!=-1) tmp[i++] = x;
        return tmp;
    }
 
    public static void main(String[] args) {
        java.util.Random rnd  = new java.util.Random();
        int numFilas      = 8;
        int filaInicial   = rnd.nextInt(numFilas);
        int colInicial    = rnd.nextInt(numFilas);
        Reines pr = new Reines(numFilas);
        pr.resolverProblema(filaInicial, colInicial);
        pr.mostrarTablero();
        System.out.printf("Cantidad de veces que entra al ciclo: %,d %n",  pr.contador);
    }
}

