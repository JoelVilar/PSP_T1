package juanxxiii.psp_t1_insertByThread.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import juanxxiii.psp_t1_insertByThread.controlador.AccessManager;

/**
 * Clase que gestiona el menú y los datos que introducimos en este.
 * @author Atila
 *
 */
public class Menu {
	private static final int BEGIN_COUNT = 0;
	private static final int ERROR_VALUE = -1;
	private static final String ERROR_NAN_MSG = "Por favor, introduzca un número.";
	private static final String APP_HEAD = "Práctica del 1er Trimestre. Aplicación para inserción de datos.";
	private static final String ASK_NUM_OF_INSERTS = "¿Cuántos registros desea insertar? ";
	private static final String ASK_NUM_OF_THREADS = "¿Cuántos Hilos desea utilizar? ";
	private static final String ERROR_ON_RANGE = "Valor no permitido, inténtelo de nuevo.";
	private static final String BYE_MSG = "Adiós.";
	private static final String MENU_DIALOG = "¿Qué desea hacer?\n"
			+ "1.Insertar registros en la base de datos.\n"
			+ "0.Salir";
	private Scanner keyB = new Scanner(System.in);
	
	/**
	 * Método que muestra el menú en el que el usuario escogerá si insertar datos o abortar.
	 * Repetido hasta que este decida salir.
	 */
	public void showMenu() {
		System.out.println(APP_HEAD);
		int option=ERROR_VALUE;
		do {
			System.out.println(MENU_DIALOG);
			option = getOption();
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
	
	/**
	 * Método que nos pedirá un número de registros a insertar y número de hilos que queremos
	 * utilizar para ello.
	 */
	private void insertDataMenuPool() {

		int numOfInserts=ERROR_VALUE;
		do{
			System.out.print(ASK_NUM_OF_INSERTS);
			numOfInserts=getOption();
			if(numOfInserts<=BEGIN_COUNT) System.out.println(ERROR_ON_RANGE);
		}while(numOfInserts<=BEGIN_COUNT);

		int numOfThreads=ERROR_VALUE;
		do{
			System.out.print(ASK_NUM_OF_THREADS);
			numOfThreads=getOption();
			if(numOfThreads<=BEGIN_COUNT) System.out.println(ERROR_ON_RANGE);
		}while(numOfThreads<=BEGIN_COUNT);
		/*
		 * Instanciamos un objeto de la clase AccessManager, que controlará el flujo de los hilos
		 * y los registros.
		 * */
		new AccessManager(numOfThreads, numOfInserts).insertData();
	}
	
	/**
	 * Método que pide al usuario que introduzca un número y lo devuelve, controlando sus excepciones.
	 * @return
	 */
	public int getOption() {
		boolean check=false;
		int option=0;
		do {
			try {
				option = keyB.nextInt();
				check=true;
			} catch(InputMismatchException e) {
				keyB.nextLine();
				System.out.println(ERROR_NAN_MSG);
			}
		}while(!check);
		return option;
	}
}
