package juanxxiii.psp_t1_insertByThread.controlador;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import juanxxiii.psp_t1_insertByThread.modelo.InsertThread;
/**
 * Clase que controla el reparto de trabajo entre un número de hilos específico,
 * accediendo estos a un recurso.
 * @author Atila
 *
 */
public class AccessManager{
	private static final String ERROR_HEAD_MSG = "ERROR: ";
	private static final int BEGIN_COUNT = 0;
	private ExecutorService executor;
	private AtomicInteger cont;
	private int numOfThreads;
	/**
	 * Constructor al cual le indicamos el número de hilos que usaremos y el número
	 * total de registros a insertar.
	 * @param numOfThreads
	 * @param numOfEmployees
	 */
	public AccessManager(int numOfThreads,int numOfEmployees) {
		this.cont= new AtomicInteger(numOfEmployees);
		this.numOfThreads=numOfThreads;
		this.executor = Executors.newFixedThreadPool(numOfThreads);
	}
	/**
	 * Método que ejecuta los hilos deseados.
	 */
	public void insertData() {
		ArrayList<InsertThread> threads = new ArrayList<>();
		IntStream.range(BEGIN_COUNT, numOfThreads)
					.forEach(i-> {
						threads.add(new InsertThread(this));
						executor.execute(()-> threads.get(i).start());
					});
		executor.shutdown();
		try {
			threads.get(numOfThreads-1).join();
		} catch (InterruptedException e) {
			System.out.println(ERROR_HEAD_MSG + e.getMessage());
		}
	}
	/**
	 * Devuelve el ExecutorService de la clase.
	 * @return
	 */
	public ExecutorService getExecutor() {
		return executor;
	}
	/**
	 * Modifica el ExecutorService de la clase.
	 * @param executor
	 */
	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}
	/**
	 * Devuelve el contador de la clase.
	 * @return
	 */
	public AtomicInteger getCont() {
		return cont;
	}
	/**
	 * Modifica el contador de la clase.
	 * @param cont
	 */
	public void setCont(AtomicInteger cont) {
		this.cont = cont;
	}
	
	
}
