package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CompareFileWithArray {
	/**
	 * Permite verificar que los datos cargados desde el archivo de texto fueron guardados correctamente en el arreglo.
	 * @param array El arreglo de String a comparar.
	 * @param filePath ruta de arhivo para proceder a comparar con el arreglo.
	 * @return True si los datos en el arreglo son iguales y si no retorna false.
	 * @throws IOException Si la ruta especifica no es correcta. 
	 * Se lanzará esta excepcion cuando el tamaño del arhivo o del arreglo sean diferentes uno del otro.
	 */
	
	
	@SuppressWarnings("resource")
	public static boolean assertEqualsCompareFileWithArrangement(String[] array, String filePath) throws IOException, DifferentSizesException {
		boolean same = false;
		boolean salir = false;
		
		File file = new File(filePath);
		FileReader fr = new FileReader(file);
		BufferedReader lector = new BufferedReader(fr);
		String line = lector.readLine();
		int size = 0;
		while (line != null) {
			size++;
			line = lector.readLine();
		}
		lector.close();
		fr.close();

		if (array.length == size) {
			FileReader fa= new FileReader(file);
			BufferedReader lectora = new BufferedReader(fa);
			line = lectora.readLine();
			int i = 0;
			while (line != null && !salir) {
//				System.out.println("Expected"+"  "+"Actual");
//				System.out.println(line+"  "+array[i]);
				if (line.equalsIgnoreCase(array[i])) {
					same = true;
				}else {
					same = false;
					salir =true;
				}
				line= lectora.readLine();
				i++;
			}
			lectora.close();
			fa.close();
			
		}else {
			throw new DifferentSizesException("El tamaño del arhivo o del arreglo sean diferentes uno del otro, deben ser iguales.");
		}
		if (same == false) {
			throw new DifferentSizesException("Los datos contenidos no son iguales al arreglo de la muestra por favor revise el arhivo.");
			
		}
	return same;
		
	}
}
