package algebralinear;

import java.util.ArrayList;

public class GeradorSistemas {
	
	private ArrayList<String> content;
	private Utils utils;
	
	public GeradorSistemas( ) {
		content = new ArrayList<String>( );
		utils = new Utils( );
	}
	
	private void run( ) {
		int repetitions = 60, dimension = 5;
		FileUtils.load("resources/head.tex", content);
		content.add("\\begin{multicols*}{2}");
		for ( int k = 0; k < repetitions; k++ ) {
			content.add("\\begin{equation*}");
			content.add("\\begin{cases}");
			
			int[ ] variavel = new int[ dimension ];
			for ( int i = 0; i < dimension; i++ ) {
				variavel[ i ] = - 10 + ( int ) ( 20 * Math.random( ) );
				while ( variavel[ i ] == 0 ) {
					variavel[ i ] = - 10 + ( int ) ( 20 * Math.random( ) );
				}
			}
			int[ ][ ] coeficiente = new int[ dimension ][ dimension ];
			while ( utils.det( coeficiente ) == 0 ) {
				for ( int i = 0; i < dimension; i++ ) {
					for ( int j = 0; j < dimension; j++ ) {
						coeficiente[ i ][ j ] = - 10 + ( int ) ( 20 * Math.random( ) );
						while ( coeficiente[ i ][ j ] == 0 ) {
							coeficiente[ i ][ j ] = - 10 + ( int ) ( 20 * Math.random( ) );
						}
					}
				}
			}
			int[ ] vetorColuna = new int[ dimension ];
			for ( int i = 0; i < dimension; i++ ) {
				vetorColuna[ i ] = 0;
				for ( int j = 0; j < dimension; j++ ) {
					vetorColuna[ i ] = vetorColuna[ i ] + variavel[ j ] * coeficiente[ i ][ j ];
				}
			}
			// simplificacao das linhas:
			int[ ] fatorComum = new int[ dimension ];
			for ( int i = 0; i < dimension; i++ ) {
				fatorComum[ i ] = coeficiente[ i ][ 0 ];
				for ( int j = 1; j < dimension; j++ ) {
					fatorComum[ i ] = utils.mdc( fatorComum[ i ], coeficiente[ i ][ j ] );
				}
				fatorComum[ i ] = utils.mdc( fatorComum[ i ], vetorColuna[ i ] );
				fatorComum[ i ] = fatorComum[ i ] < 0 ? - fatorComum[ i ] : fatorComum[ i ];
				for ( int j = 0; j < dimension; j++ ) {
					coeficiente[ i ][ j ] = coeficiente[ i ][ j ] / fatorComum[ i ];
				}
				vetorColuna[ i ] = vetorColuna[ i ] / fatorComum[ i ]; 
			}
			String line = "";
			String[ ] label = { "x", "y", "z", "u", "v", "w" };
			for ( int i = 0; i < dimension; i++ ) {
				line = "";
				for ( int j = 0; j < dimension; j++ ) {
					if ( j > 0 && coeficiente[ i ][ j ] > 0 ) {
						System.out.print( "+" );
						line = line.concat( "+" );
					}
					if ( coeficiente[ i ][ j ] == 1 ) {
						System.out.print( label[ j ] );
						line = line.concat( label[ j ] );
					}
					else {
						if ( coeficiente[ i ][ j ] == -1 ) {
							System.out.print( "-" + label[ j ] );
							line = line.concat( "-" + label[ j ] );
						}
						else {
							System.out.print(String.valueOf(coeficiente[ i ][ j ]).concat(label[j]));
							line = line.concat(String.valueOf(coeficiente[ i ][ j ]).concat(label[j]));
						}
					}
				}
				System.out.println( "=" + String.valueOf( vetorColuna[ i ] ) );
				line = line.concat("=" + String.valueOf( vetorColuna[ i ] ));
				if ( i < dimension - 1 ) {
					line = line.concat(" \\\\");
				}
				content.add(line);
			}
			content.add("\\end{cases}");
			content.add("\\end{equation*}");
			content.add("\\begin{equation*}");
			System.out.print("Solução = (");
			line = "\\text{Solução = }\\left(";
			for ( int j = 0; j < dimension; j++ ) {
				if ( j > 0 ) {
					System.out.print(",");
					line = line.concat(",");
				}
				System.out.print(variavel[ j ]);
				line = line.concat(String.valueOf(variavel[ j ]));
			}
			System.out.println(")");
			line = line.concat("\\right)");
			content.add(line);
			content.add("\\end{equation*}");
			content.add("\\vspace{\\baselineskip}");
		}
		ArrayList<String> tail = new ArrayList<String>( );
		FileUtils.load("resources/tail.tex", tail);
		for ( String line : tail ) {
			content.add( line );
		}
		FileUtils.save("resources/sistemas_lineares.tex", content);
		System.out.println("done.");
	}
	
	public static void main( String[ ] args ) {
		GeradorSistemas obj = new GeradorSistemas( );
		obj.run( );
	}

}
