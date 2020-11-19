package juanxxiii.psp_t1_insertByThread.controlador;

import juanxxiii.psp_t1_insertByThread.modelo.InsertThread;

public class ThreadManager {
	private final int numOfInserts;
	private final int numOfThreads;
	
	public ThreadManager(int inserts, int threads) {
		this.numOfInserts=inserts;
		this.numOfThreads=threads;
	}
	
	public void begin() {
		InsertThread thread;
		int nums[]=getNumsByThread();
		for(int x = 0; x<numOfThreads;x++) {
			thread = new InsertThread(nums[0]);
		}
	}
	
	public int[] getNumsByThread() {
		int exced=numOfInserts%numOfThreads;
		int numsByThread=(numOfInserts-exced)/numOfThreads;
		int numsAndExceded[]= {numsByThread,exced}; 
		return numsAndExceded;
	}
}
