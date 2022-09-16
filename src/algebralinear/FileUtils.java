package algebralinear;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Utility class to load and save lists
 * @author Cleyton
 *
 */
public class FileUtils {
	
	public FileUtils( ) {
		
	}
	
	public static void load( String path, ArrayList<String> list ) {
		try {
			FileInputStream fis = new FileInputStream( path );
			InputStreamReader isr = new InputStreamReader( fis, StandardCharsets.UTF_8 );
			BufferedReader br = new BufferedReader( isr );
			String line = "";
			while ( ( line = br.readLine( ) ) != null ) {
				list.add( line );
			}
			br.close( );
			isr.close( );
			fis.close( );
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
	}
	
	public static void save( String path, ArrayList<String> list ) {
		try {
			FileOutputStream fos = new FileOutputStream( path );
			OutputStreamWriter osw = new OutputStreamWriter( fos, StandardCharsets.UTF_8 );
			BufferedWriter bw = new BufferedWriter( osw );
			for ( String line : list ) {
				bw.write( line + "\n" );
			}
			bw.close( );
			osw.close( );
			fos.close( );
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
	}
	
	public static String formatName( String name ) {
		String[ ] immutable = { "da", "das", "de", "do", "dos", "e" };
		String[ ] token = name.split( "\\s" ); 
		String formatted = "";
		for ( int i = 0; i < token.length; i++ ) {
			boolean isImmutable = false;
			token[ i ] = token[ i ].toLowerCase( );
			for ( int j = 0; j < immutable.length; j++ ) {
				if ( token[ i ].equals( immutable[ j ] )  ) {
					isImmutable = true;
				}
			}
			if ( !isImmutable ) {
				token[ i ] = String.valueOf( token[ i ].charAt( 0 ) ).toUpperCase( ).concat( token[ i ].substring( 1, token[ i ].length() ) );
			}
			if ( i > 0 ) {
				formatted = formatted + " ";
			}
			formatted = formatted + token[ i ];
		}
		return formatted;
	}
	
	public static int lev( String a, String b ) { // Levenshtein distance
		if ( b.length( ) == 0 ) {
			return a.length( );
		}
		if ( a.length( ) == 0 ) {
			return b.length( );
		}
		if ( a.charAt( 0 ) == b.charAt( 0 ) ) {
			return lev( tail( a ), tail( b ) );
		}
		return 1 + ( int ) Math.min( lev( tail( a ), b ), Math.min( lev( a, tail( b ) ), lev( tail( a ), tail( b ) ) ) );
	}
	
	private static String tail( String x ) {
		return x.substring( 1 );
	}
	
	private void run( ) {
		String[ ] palavras = { "gato", "mato", "pato", "mata", "prato", "grato", "lata", "fato", "faca" };
		for ( int i = 0; i < palavras.length - 1; i++ ) {
			for ( int j = i + 1; j < palavras.length; j++ ) {
				System.out.println( "lev(" + palavras[ i ] + "," + palavras[ j ] + ") = " + lev(palavras[ i ], palavras[ j ]));
			}
		}
	}
	
	public static void main( String[ ] args ) {
		FileUtils obj = new FileUtils( );
		obj.run( );
	}

}
