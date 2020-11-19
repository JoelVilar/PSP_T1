package juanxxiii.psp_t1_insertByThread.vista;

import java.util.Scanner;

public class Menu {
	private final String APP_HEAD="Práctica del 1er Trimestre.";
	private final String ASK_NUM_OF_INSERTS="¿Cuántos registros desea insertar? ";
	private final String ASK_NUM_OF_THREADS="¿Cuántos Hilos desea utilizar? ";
	private final String ERROR_ON_RANGE="Valor no permitido, inténtelo de nuevo.";
	private Scanner keyB = new Scanner(System.in);
	public void showMenu() {
		System.out.println(APP_HEAD);
		int numOfInserts=-1;
		do{
			System.out.print(ASK_NUM_OF_INSERTS);
			numOfInserts=keyB.nextInt();
			if(numOfInserts<=0) System.out.println(ERROR_ON_RANGE);
		}while(numOfInserts<=0);

		do{
			System.out.print(ASK_NUM_OF_THREADS);
			numOfInserts=keyB.nextInt();
			if(numOfInserts<=0) System.out.println(ERROR_ON_RANGE);
		}while(numOfInserts<=0);
		
	}
}
