package juanxxiii.psp_t1_insertByThread.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import juanxxiii.psp_t1_insertByThread.controlador.AccessManager;

/**
 * Clase que hereda de Thread la cual, al ejecutarse, mientras pueda acceder al contador
 * de un objeto AccessManager insertará un nuevo registro en la base de datos.
 * @author Atila
 *
 */
public class InsertThread extends Thread{
	private static final int MIN_VALUE_RANDOM = 10;
	private static final int MAX_VALUE_RANDOM = 990;
	private static final String URL = "jdbc:mysql://localhost/BBDD_PSP_1?serverTimeZone=UTC&user=DAM2020_PSP&password=DAM2020_PSP";
	private static final String INSERT_QUERY = "INSERT INTO EMPLEADOS (EMAIL, INGRESOS) VALUES (?,?)";
	private static final String ERROR_MSG = "ERROR DURANTE LA INSERCIÓN. CAUSA: ";
	private static final String END_PROCESS_MSG = "-->Hilo terminado correctamente.";
	private static final String EMAIL="EMAIL@juanxxiii.net";
	private static final String NUM_EMPLEADO_MSG = "Empleado nº ";
	private static final String WHICH_THREAD_MSG = " introducido exitosamente con el hilo: ";
	private static final int COND_WHILE = 0;
	private static final int EMAIL_COLUMN = 1;
	private static final int INGRESOS_COLUMN = 2;
	private AccessManager accessManager;
	
	/**
	 * Constructor al cual le pasamos un objeto de la clase AccessManager.
	 * @param accessManager
	 */
	public InsertThread(AccessManager accessManager) {
		this.accessManager=accessManager;
	}
	
	/**
	 * Método que se ejecutará al comenzar el hilo. Inserta registros mientras
	 * el contador del objeto AccessManager de la clase sea superior a 0.
	 */
	@Override
	public void run() {
			/*
			 * Bucle while que utilizamos para que, en la primera y sucesivas iteraciones que
			 * este haga, pregunte y decremente en uno el valor del contador. Esto es así para
			 * que no se produzcan inconsistencias de memoria, y no pueda haber 2 o más hilos
			 * con un mismo valor. La clase AtomicInteger nos permite esto por su tipo de acceso.
			 * 
			 * Así que, a cada iteración del bucle, este pregunta por su acceso y al mismo tiempo,
			 * modifica el contador, evitando que un hilo acceda al recurso entre esos 2 pasos y
			 * se produzca la inconsistencia.
			 * 
			 * */
			int contador;
			while((contador=accessManager.getCont().getAndDecrement())>COND_WHILE) {
				insertNewEmployee(contador);
			}
			System.out.println(END_PROCESS_MSG);
	}
	
	/**
	 * Método para la inserción de un nuevo registro en la base de datos.
	 */
	public void insertNewEmployee(int contador) {
		/*
		 * Try con recursos, este permite que si se le pasa una clase que implemente
		 * la interface Closeable, este finalice al terminar el bloque try-catch sin
		 * necesidad de tener que utilizar un bloque finally.
		 * */
		try(Connection conect = DriverManager.getConnection(URL)){
			PreparedStatement statement = conect.prepareStatement(INSERT_QUERY);
				statement.setString(EMAIL_COLUMN, EMAIL);
				statement.setInt(INGRESOS_COLUMN, generateRandom());
				statement.executeQuery();
			System.out.println(NUM_EMPLEADO_MSG
					+ contador
					+ WHICH_THREAD_MSG
					+ this.getName());
		}catch(SQLException e) {
			System.out.println(ERROR_MSG + e.getMessage());
		}
	}
	
	/**
	 * Método que genera un número random entre 10 y 1000;
	 * @return
	 */
	private int generateRandom() {
		return (int)(Math.random() * MAX_VALUE_RANDOM + MIN_VALUE_RANDOM);
	}
}
