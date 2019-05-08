package model;
/**
 * Se lanzará esta excepcion cuando el tamaño del arhivo o del arreglo sean diferentes uno del otro.
 * @author osscar
 *
 */
public class DifferentSizesException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DifferentSizesException(String mensaje) {
		super(mensaje);
	}
}
