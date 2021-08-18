package algebralinear;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Jacobi {
    
    public Jacobi( ) {
        
    }
    
    private void run( ) {
        int ITERACOES = 1000;
        try {
            FileReader fr = new FileReader( "resources/jacobi.in" );
            BufferedReader br = new BufferedReader( fr );
            int N = Integer.valueOf( br.readLine( ) );
            int[ ][ ] coeficient = new int[ N ][ N ];
            int[ ] vetorResultado = new int[ N ];
            double[ ][ ] solucao = new double[ ITERACOES ][ N ];
            for ( int i = 0; i < ITERACOES; i++ ) {
                for ( int j = 0; j < N; j++ ) {
                    solucao[ i ][ j ] = 0.0;
                }
            }
            for ( int i = 0; i < N; i++ ) {
                String line = br.readLine( );
                String[ ] token = line.split( "\\s" );
                for ( int j = 0; j < N; j++ ) {
                    coeficient[ i ][ j ] = Integer.valueOf( token[ j ] );
                }
                vetorResultado[ i ] = Integer.valueOf( token[ N - 1 ] );
            }
            for ( int iteracao = 1; iteracao < 11; iteracao++ ) {
                for ( int i = 0; i < N; i++ ) {
                    double somaProdutos = 0;
                    for ( int j = 0; j < N; j++ ) {
                        if ( i != j ) { 
                            somaProdutos = somaProdutos + coeficient[ i ][ j ] * solucao[ iteracao - 1 ][ j ];
                        }
                    }
                    solucao[ iteracao ][ i ] = ( 1.0 / ( double ) coeficient[ i ][ i ] ) * ( vetorResultado[ i ] - somaProdutos );
                }
            }
            for ( int iteracao = 0; iteracao < 11; iteracao++ ) {
                System.out.print( "ITERACAO " + iteracao + ":" );
                for ( int i = 0; i < N; i++ ) {
                    System.out.print( " " + solucao[ iteracao ][ i ] );
                }
                System.out.println( );
            }
            br.close( );
            fr.close( );
        }
        catch ( IOException e ) {
            e.printStackTrace( );
        }
    }
    
    public static void main( String[ ] args ) {
        Jacobi obj = new Jacobi( );
        obj.run( );
    }
    
}
