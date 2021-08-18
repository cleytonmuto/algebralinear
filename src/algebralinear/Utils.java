package algebralinear;

public class Utils {
    
    public Utils( ) {
        
    }
    
    public double[ ][ ] int2dbl( int[ ][ ] matrix ) {
        double[ ][ ] dblMatrix = new double[ matrix.length ][ matrix.length ];
        for ( int i = 0; i < matrix.length; i++ ) {
            for ( int j = 0; j < matrix.length; j++ ) {
                dblMatrix[ i ][ j ] = matrix[ i ][ j ];
            }
        };
        return dblMatrix;
    }
    
    public int[ ][ ] dbl2int( double[ ][ ] matrix ) {
        int[ ][ ] intMatrix = new int[ matrix.length ][ matrix.length ];
        for ( int i = 0; i < matrix.length; i++ ) {
            for ( int j = 0; j < matrix.length; j++ ) {
                intMatrix[ i ][ j ] = ( int ) matrix[ i ][ j ];
            }
        };
        return intMatrix;
    }
    
    public double det( double[ ][ ] matrix ) {
        if ( matrix.length == 1 ) {
            return matrix[ 0 ][ 0 ];
        }
        if ( matrix.length == 2 ) {
            return matrix[ 0 ][ 0 ] * matrix[ 1 ][ 1 ] - matrix[ 0 ][ 1 ] * matrix[ 1 ][ 0 ]; 
        }
        double sum = 0;
        for ( int j = 0; j < matrix.length; j++ ) {
            sum = sum + Math.pow( -1, 0 + j ) * matrix[ 0 ][ j ] * det( subMatrix( matrix, 0, j ) );
        }
        return sum;
    }
    
    public int det( int[ ][ ] matrix ) {
        return ( int ) det( int2dbl( matrix ) );
    }
    
    private double[ ][ ] subMatrix( double[ ][ ] matrix, int row, int column ) {
        double[ ][ ] sub = new double[ matrix.length - 1 ][ matrix.length - 1 ];
        int subRow = 0;
        for ( int i = 0; i < matrix.length; i++ ) {
            int subColumn = 0;
            for ( int j = 0; j < matrix.length; j++ ) {
                if ( i != row && j != column ) {
                    sub[ subRow ][ subColumn ] = matrix[ i ][ j ];
                    subColumn++;
                }
            }
            if ( i != row ) {
                subRow++;
            }
        }
        return sub;
    }
    
    private int[ ][ ] subMatrix( int[ ][ ] matrix, int row, int column ) {
        return dbl2int( subMatrix( int2dbl( matrix ), row, column ) );
    }
    
    private double[ ][ ] adjunta( double[ ][ ] matrix ) {
        double[ ][ ] adj = new double[ matrix.length ][ matrix.length ];
        for ( int i = 0; i < matrix.length; i++ ) {
            for ( int j = 0; j < matrix.length; j++ ) {
                adj[ i ][ j ] = Math.pow( -1, i + j ) * det( subMatrix( matrix, i, j ) );
            }
        }
        return adj;
    }
    
    private int[ ][ ] adjunta( int[ ][ ] matrix ) {
        return dbl2int( adjunta( int2dbl( matrix ) ) );
    }
    
    private double[ ][ ] inverse( double[ ][ ] matrix ) {
        double d = det( matrix ); 
        if ( d == 0 ) {
            System.err.println( "ERROR: Matrix not inversible. Null determinant.");
            return null;
            
        }
        double[ ][ ] transAdj = transpose( adjunta( matrix ) );
        double[ ][ ] inv = new double[ matrix.length ][ matrix.length ];
        for ( int i = 0; i < matrix.length; i++ ) {
            for ( int j = 0; j < matrix.length; j++ ) {
                inv[ i ][ j ] = transAdj[ i ][ j ] / d;
            }
        }
        return inv;
    }
    
    private int[ ][ ] inverse( int[ ][ ] matrix ) {
        return dbl2int( inverse( int2dbl( matrix ) ) );
    }
    
    private void run( ) {
        int n = 3;
        double[ ][ ] matriz = new double[ n ][ n ];
        while ( det( matriz ) == 0 ) {
            for ( int i = 0; i < n; i++ ) {
                for ( int j = 0; j < n; j++ ) {
                    matriz[ i ][ j ] = 1 + ( int ) ( 10 * Math.random( ) );
                    if ( matriz[ i ][ j ] != 0.0 ) {
                        int random = ( int ) ( 2 * Math.random( ) );
                        if ( random == 1 ) {
                            matriz[ i ][ j ] = - matriz[ i ][ j ];
                        }
                    }
                }
            }
        }
        print( matriz );
        System.out.println( "det = " + det( matriz ) );
        System.out.println( "Transposta:" );
        print( transpose( matriz ) );
        System.out.println( "Inversa" );
        print( inverse( matriz ) );
    }
    
    public void print( double[ ][ ] matrix ) {
        for ( int i = 0; i < matrix.length; i++ ) {
            for ( int j = 0; j < matrix.length; j++ ) {
                if ( j > 0 ) {
                    System.out.print( " " );
                }
                if ( isInteger( matrix[ i ][ j ] ) ) {
                    System.out.print( ( int ) matrix[ i ][ j ] );
                }
                else {
                    System.out.print( matrix[ i ][ j ] );
                }
            }
            System.out.println( );
        }
        System.out.print( "matriz={" );
        for ( int i = 0; i < matrix.length; i++ ) {
            if ( i > 0 ) {
                System.out.print( "," );
            }
            System.out.print( "{" );
            for ( int j = 0; j < matrix.length; j++ ) {
                if ( j > 0 ) {
                    System.out.print( "," );
                }
                if ( isInteger( matrix[ i ][ j ] ) ) {
                    System.out.print( ( int ) matrix[ i ][ j ] );
                }
                else {
                    System.out.print( matrix[ i ][ j ] );
                }
            }
            System.out.print( "}" );
        }
        System.out.println( "};" );
    }
    
    public void print( int[ ][ ] matrix ) {
        print( int2dbl( matrix ) );
    }
    
    private boolean isInteger( double value ) {
        return Math.floor( value ) == Math.ceil( value );
    }
    
    public double[ ][ ] transpose( double[ ][ ] matrix ) {
        double[ ][ ] trans = new double[ matrix.length ][ matrix.length ];
        for ( int i = 0; i < matrix.length; i++ ) {
            for ( int j = 0; j < matrix.length; j++ ) {
                trans[ j ][ i ] = matrix[ i ][ j ];
            }
        }
        return trans;
    }
    
    public int[ ][ ] transpose( int[ ][ ] matrix ) {
        return dbl2int( transpose( int2dbl( matrix ) ) );
    }
    
    public static void main( String[ ] args ) {
        Utils obj = new Utils( );
        obj.run( );
    }
    
}
