package juanxxiii.psp_t1_insertByThread.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThreadWithSemaphore extends Thread{
	private final String URL = "jdbc:mariadb://37.134.98.200:3306/BBDD_PSP_1?serverTimeZone=UTC&user=joel&password=atila95";
	private final String INSERT_QUERY = "INSERT INTO EMPLEADOS (EMAIL, INGRESOS) VALUES (?,?)";
	private final String ERROR_MSG = "ERROR DURANTE LA INSERCIÓN. CAUSA: ";
	private final String END_PROCESS_MSG = "-->Hilo terminado correctamente.";
	private final String EMAIL="EMAIL@juanxxiii.net";
	private AccessManager accessManager;
	
	public ThreadWithSemaphore(AccessManager accessManager) {
		this.accessManager=accessManager;
	}
	@Override
	public void run() {
			int contador;
			while((contador=accessManager.getCont().getAndDecrement())>0) {
				/*try {
					accessManager.getSemaforo().acquire();*/
					insertNewEmployee();
					System.out.println("Empleado nº "
										+ contador
										+ " introducido exitosamente con el hilo: "
										+ this.getName());
					/*accessManager.getSemaforo().release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
	}
	
	public void insertNewEmployee() {
		try(Connection conect = DriverManager.getConnection(URL)){
			PreparedStatement statement = conect.prepareStatement(INSERT_QUERY);
				statement.setString(1, EMAIL);
				statement.setInt(2, (int)(Math.random() * 1000 + 1));
				statement.executeQuery();
			System.out.println(END_PROCESS_MSG);
		}catch(SQLException e) {
			System.out.println(ERROR_MSG + e.getMessage());
		}
	}
}
