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
	
}
