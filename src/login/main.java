package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class main {
	public static void main(String[] args)
	{
		
		try {
			
			Scanner console = new Scanner(System.in);
			String usuario, senha;
			
			System.out.println("Informe o usuário:");
			usuario = console.nextLine();
			System.out.println("Informe a senha:");
			senha = console.nextLine();
			
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://prof-ramon.database.windows.net:1433;database=Kanino";
			String user = "TSI";
			String pass = "SistemasInternet123";
			 
			//Class.forName(driver);
			

			
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select * from Usuario where loginUsuario = ? and senhaUsuario = ? and usuarioAtivo = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario);
			stmt.setString(2, senha); 
			stmt.setByte(3, (byte) 1);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				System.out.println("Usuario: " + usuario);
				System.out.println("Senha: " + senha);
				System.out.println("Autorizado!");
				
			}else{
				
				System.out.println("Não autorizado!");
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
			console.close();
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado! "+e);
		} catch (SQLException e) {
			System.out.println("Erro de conexão! "+e);
		}
		
		

	}
}