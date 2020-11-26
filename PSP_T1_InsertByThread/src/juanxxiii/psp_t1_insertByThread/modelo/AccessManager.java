package juanxxiii.psp_t1_insertByThread.modelo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AccessManager{
	//private Semaphore semaforo= new Semaphore(3,true);
	private ExecutorService executor;
	private AtomicInteger cont;
	private int numOfThreads;
	public AccessManager(int numOfThreads,int numOfEmployees) {
		this.cont= new AtomicInteger(numOfEmployees);
		this.numOfThreads=numOfThreads;
		this.executor = Executors.newFixedThreadPool(numOfThreads);
	}
	
	public void insertData() {
		IntStream.range(0, numOfThreads).forEach(e-> executor.execute(()-> new ThreadWithSemaphore(this).start()));
		executor.shutdown();
		System.out.println("Proceso terminado correctamente.");
	}
/*
	public Semaphore getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
*/
	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public AtomicInteger getCont() {
		return cont;
	}

	public void setCont(AtomicInteger cont) {
		this.cont = cont;
	}
	
	
}
