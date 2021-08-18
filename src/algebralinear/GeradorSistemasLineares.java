package algebralinear;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class GeradorSistemasLineares {
    
    private final int EXERCISES = 1000;
    private final int N = 6; // grau da matriz = quantidade de equacoes
    
    public GeradorSistemasLineares( ) {
        
    }
    
    private void run( ) {
        Utils utils = new Utils( );
        try {
            FileReader fr = new FileReader( "resources/header.tex" );
            BufferedReader br = new BufferedReader( fr );
            FileWriter fw = new FileWriter( "resources/output.tex" );
            BufferedWriter bw = new BufferedWriter( fw );
            String line = "";
            while ( ( line = br.readLine( ) ) != null ) {
                bw.write( line + "\n" );
            }
            br.close( );
            fr.close( );
            for ( int k = 0; k < EXERCISES; k++ ) {
                int[ ][ ] coeficient = new int[ N ][ N ];
                while ( utils.det( coeficient ) == 0 ) {
                    for ( int i = 0; i < N; i++ ) {
                        for ( int j = 0; j < N; j++ ) {
                            coeficient[ i ][ j ] = 1 + ( int ) ( 10 * Math.random( ) );
                            int random = ( int ) ( 2 * Math.random( ) );
                            if ( random == 1 ) {
                                coeficient[ i ][ j ] = - coeficient[ i ][ j ];
                            }
                        }
                    }
                }
                int[ ] solution = new int[ N ];
                for ( int i = 0; i < N; i++ ) {
                    int value = 1 + ( int ) ( 10 * Math.random( ) );
                    int random = ( int ) ( 2 * Math.random( ) );
                    if ( random == 1 ) {
                        value = - value;
                    }
                    solution[ i ] = value;
                }
                int[ ] sum = new int[ N ];
                for ( int i = 0; i < N; i++ ) {
                    for ( int j = 0; j < N; j++ ) {
                        sum[ i ] = sum[ i ] + ( coeficient[ i ][ j ] * solution[ j ] );
                    }
                }
                String[ ] variable = {"x","y","z","u","v","w"};
                bw.write( "\\begin{equation*}\n" );
                bw.write( "\\begin{cases}\n" );
                for ( int i = 0; i < N; i++ ) {
                    for ( int j = 0; j < N; j++ ) {
                        if ( j > 0 && coeficient[ i ][ j ] > 0 ) {
                            bw.write( "+" );
                        }
                        if ( coeficient[ i ][ j ] == -1 ) {
                            bw.write( "-" );
                        }
                        else if ( coeficient[ i ][ j ] != 1 ) {
                            bw.write( String.valueOf( coeficient[ i ][ j ] ) );
                        }
                        bw.write( variable[ j ] );
                    }
                    bw.write( "=" + String.valueOf( sum[ i ] ) + " \\\\\n");
                }
                bw.write( "\\end{cases}\n" );
                bw.write( "\\end{equation*}\n" );
                String strSolution = "";
                for ( int i = 0; i < N; i++ ) {
                    if ( i > 0 ) {
                        strSolution += ",";
                    }
                    strSolution += solution[ i ];
                }
                bw.write( "\\begin{equation*}\n" );
                bw.write( "\\text{Solução = }\\{" + strSolution + "\\}\n" );
                bw.write( "\\end{equation*}\n" );
                bw.write( "\\vspace{\\baselineskip}\n" );
            }
            bw.write( "\\end{document}\n" );
            bw.close( );
            fw.close( );
        }
        catch ( IOException e ) {
            e.printStackTrace( );
        }
        System.out.println( "done." );
    }
    
    public static void main( String[ ] args ) {
        GeradorSistemasLineares obj = new GeradorSistemasLineares( );
        obj.run( );
    }
    
}
