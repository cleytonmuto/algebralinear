package algebralinear;

public class LUDecomposition {
	
	public LUDecomposition( ) {
		
	}
	
	private void run( ) {
		Utils utils = new Utils( );
		int n = 3;
		int[ ][ ] matriz = new int[ n ][ n ];
		while ( utils.det( matriz ) == 0 ) {
			for ( int i = 0; i < n; i++ ) {
                for ( int j = 0; j < n; j++ ) {
                    matriz[ i ][ j ] = 1 + ( int ) ( 10 * Math.random( ) );
                    if ( matriz[ i ][ j ] != 0 ) {
                        int random = ( int ) ( 2 * Math.random( ) );
                        if ( random == 1 ) {
                            matriz[ i ][ j ] = - matriz[ i ][ j ];
                        }
                    }
                }
            }
		}
		System.out.println("matriz original:");
		utils.print( matriz );
		double[ ][ ] L = new double[ n ][ n ];
		double[ ][ ] U = new double[ n ][ n ];
		for ( int i = 0; i < n; i++ ) {
			for ( int j = 0; j < n; j++ ) {
				L[ i ][ j ] = 0.0;
				U[ i ][ j ] = 0.0;
			}
		}
		for ( int k = 0; k < n; k++ ) {
			L[ k ][ k ] = 1.0;
			U[ 0 ][ k ] = ( double ) matriz[ 0 ][ k ];
		}
		for ( int i = 1; i < n; i++ ) {
			L[ i ][ 0 ] = ( double ) matriz[ i ][ 0 ] / U[ 0 ][ 0 ];
		}
		U[ 1 ][ 1 ] = ( double ) matriz[ 1 ][ 1 ] - L[ 1 ][ 0 ] * U[ 0 ][ 1 ];
		U[ 1 ][ 2 ] = ( double ) matriz[ 1 ][ 2 ] - L[ 1 ][ 0 ] * U[ 0 ][ 2 ];
		L[ 2 ][ 1 ] = ( ( double ) matriz[ 2 ][ 1 ] - L[ 2 ][ 0 ] * U[ 0 ][ 1 ] ) / U[ 1 ][ 1 ];
		U[ 2 ][ 2 ] = ( double ) matriz[ 2 ][ 2 ] - L[ 2 ][ 0 ] * U[ 0 ][ 2 ] - L[ 2 ][ 1 ] * U[ 1 ][ 2 ];
		System.out.println("matriz L:");
		utils.print( L );
		System.out.println("matriz U:");
		utils.print( U );
		System.out.println("matriz L*U");
		utils.print( utils.dbl2int( utils.multiply(L,U) ) );
		System.out.println( "done." );
	}
	
	public static void main( String[ ] args) {
		LUDecomposition obj = new LUDecomposition( );
		obj.run ();
	}

}
