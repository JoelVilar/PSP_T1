package juanxxiii.psp_t1_insertByThread.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertThread extends Thread{
	private final String URL = "jdbc:mariadb://37.134.98.200:3306/BBDD_PSP_1?serverTimeZone=UTC&user=joel&password=atila95";
	private final String INSERT_QUERY = "INSERT INTO EMPLEADOS (EMAIL, INGRESOS) VALUES (?,?)";
	private final String ERROR_MSG = "ERROR DURANTE LA INSERCIÓN. CAUSA: ";
	private final String END_PROCESS_MSG = "-->Hilo terminado correctamente.";
	private final String EMAIL="EMAIL@juanxxiii.net";
	private int numOfInserts;
	
	public InsertThread(int inserts) {
		this.numOfInserts=inserts;
	}
	
	@Override
	public void run() {
		try(Connection conect = DriverManager.getConnection(URL)){
			ResultSet insert;
			PreparedStatement statement = conect.prepareStatement(INSERT_QUERY);
			for(int x = 0; x<numOfInserts;x++) {
				statement.setString(1, EMAIL);
				statement.setInt(2, (int)(Math.random() * 1000 + 1));
				insert = statement.executeQuery();
			}
			System.out.println(END_PROCESS_MSG);
		}catch(SQLException e) {
			System.out.println(ERROR_MSG + e.getMessage());
		}
	}
}
