package juanxxiii.psp_t1_insertByThread.controlador;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
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
		IntStream.range(0, numOfThreads)
					.forEach(e-> executor.execute(()-> new InsertThread(this).start()));
		executor.shutdown();
		System.out.println("Proceso terminado correctamente.");
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
