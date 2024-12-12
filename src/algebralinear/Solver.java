package algebralinear;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Solver {
	
	public Solver( ) {
		
	}
	
	private void run( ) {
		try {
			FileReader fr = new FileReader( "resources/jacobi.in" );
			BufferedReader br = new BufferedReader( fr );
			int N = Integer.valueOf( br.readLine( ) ); // leitura do grau
			int[ ][ ] coeficient = new int[ N ][ N ];
			int[ ] sum = new int[ N ];
			for ( int i = 0; i < N; i++ ) {
				String line = "";
				line = br.readLine( );
				String[ ] token = line.split( "\\s" );
				for ( int j = 0; j < N; j++ ) {
					coeficient[ i ][ j ] = Integer.valueOf( token[ j ] );
				}
				sum[ i ] = Integer.valueOf( token[ N ] );
			}
			Utils utils = new Utils( );
			System.out.println("vetor soma");
			utils.print(sum);
			double[ ][ ] inverse = utils.inverse( utils.int2dbl( coeficient ) );
			System.out.println("matriz inversa");
			utils.print( inverse );
			double[ ] resultado = utils.multiply( inverse, utils.int2dbl( sum ) );
			utils.print( resultado );
			br.close( );
			fr.close( );
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
	}
	
	public static void main( String[ ] args ) {
		Solver obj = new Solver( );
		obj.run( );
	}

}
