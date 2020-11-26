package juanxxiii.psp_t1_insertByThread.vista;

import java.util.Scanner;

import juanxxiii.psp_t1_insertByThread.controlador.ThreadManager;
import juanxxiii.psp_t1_insertByThread.modelo.AccessManager;

public class Menu {
	private final String APP_HEAD="Práctica del 1er Trimestre.";
	private final String ASK_NUM_OF_INSERTS="¿Cuántos registros desea insertar? ";
	private final String ASK_NUM_OF_THREADS="¿Cuántos Hilos desea utilizar? ";
	private final String ERROR_ON_RANGE="Valor no permitido, inténtelo de nuevo.";
	private final String BYE_MSG="Adiós.";
	private final String MENU_DIALOG="¿Qué desea hacer?\n"
			+ "1.Insertar registros en la base de datos.\n"
			+ "0.Salir";
	private Scanner keyB = new Scanner(System.in);
	
	public void showMenu() {
		System.out.println(APP_HEAD);
		int option=-1;
		do {
			System.out.println(MENU_DIALOG);
			option = keyB.nextInt();
			switch(option) {
			case 1:
				insertDataMenuPool();
				break;
			case 0:
				System.out.println(BYE_MSG);
				break;
			default:
				System.out.println(ERROR_ON_RANGE);
				break;
			}
		}while(option!=0);
	}

	private void insertDataMenu() {
		int numOfInserts=-1;
		do{
			System.out.print(ASK_NUM_OF_INSERTS);
			numOfInserts=keyB.nextInt();
			if(numOfInserts<=0) System.out.println(ERROR_ON_RANGE);
		}while(numOfInserts<=0);

		int numOfThreads=-1;
		do{
			System.out.print(ASK_NUM_OF_THREADS);
			numOfThreads=keyB.nextInt();
			if(numOfThreads<=0) System.out.println(ERROR_ON_RANGE);
		}while(numOfThreads<=0);
		new ThreadManager(numOfInserts, numOfThreads).begin();
	}
	
	private void insertDataMenuPool() {
		
		int numOfInserts=-1;
		do{
			System.out.print(ASK_NUM_OF_INSERTS);
			numOfInserts=keyB.nextInt();
			if(numOfInserts<=0) System.out.println(ERROR_ON_RANGE);
		}while(numOfInserts<=0);

		int numOfThreads=-1;
		do{
			System.out.print(ASK_NUM_OF_THREADS);
			numOfThreads=keyB.nextInt();
			if(numOfThreads<=0) System.out.println(ERROR_ON_RANGE);
		}while(numOfThreads<=0);
		
		new AccessManager(numOfThreads, numOfInserts).insertData();
	}
}
