package br.com.RosaCake.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection conectar() throws ClassNotFoundException {
		Connection conexao = null;
		
		Class.forName("org.h2.Driver");
		
		try {
			conexao = DriverManager.getConnection("jdbc:h2:C:\\DataBase_RosaCake/rosaCake_dataBase;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE", "rosaCake", "123");
			
			//AUTORECONNECT=TRUE
			//AUTO_SERVER=TRUE
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexao;
	}
	
}
