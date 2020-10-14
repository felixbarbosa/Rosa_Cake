package br.com.RosaCake.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import br.com.RosaCake.bean.Lancamento;
import br.com.RosaCake.bean.Lucro;
import br.com.RosaCake.bean.LucroAno;
import br.com.RosaCake.bean.LucroMes;
import br.com.RosaCake.bean.LucroSemana;
import br.com.RosaCake.conexao.Conexao;

public class Lancamentos {

	
	//static Statement stmt = null;
	//static ResultSet rs = null;
	
	
	
	public void inserir(String tipo, double valor, String descricao, String data, String pagamento, String parcelas, Conexao con) throws ParseException {
		
		Statement stmt = null;
		//ResultSet rs = null;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date = new java.sql.Date(format.parse(data).getTime());
		String query = "";
		
		if(parcelas.equals("")) {
			query = "INSERT INTO LANCAMENTO VALUES ('" +
					tipo + "', " + valor + ", '" + descricao + "', '" + date + "', '" + pagamento + "', " + "default" + ")";
		}
		else {
			query = "INSERT INTO LANCAMENTO VALUES ('" +
					tipo + "', " + valor + ", '" + descricao + "', '" + date + "', '" + pagamento + "', '" + parcelas + "')";
		}
		
		try {
			stmt = con.conectar().createStatement();
			stmt.execute(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
						
	}
	
	public boolean inserirLucro(double valor, java.sql.Date data, Conexao con) {
		
		/*SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date = new java.sql.Date(format.parse(data).getTime());*/
		
		Statement stmt = null;
		//ResultSet rs = null;
		
		String query = "INSERT INTO LUCRODIA VALUES (" +
						valor + ", '" + data + "')";
		
		try {
			stmt = con.conectar().createStatement();
			stmt.execute(query);
			
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
							
	}
	
	public void inserirLucroSemana(double valor, Conexao con) {
		
		/*SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date = new java.sql.Date(format.parse(data).getTime());*/
		
		Statement stmt = null;
		//ResultSet rs = null;
		
		String query = "INSERT INTO LUCROSEMANA VALUES (" +
						valor + ", null)";
		
		try {
			stmt = con.conectar().createStatement();
			stmt.execute(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
							
	}
	
	public void inserirLucroMes(double valor, Conexao con) {
		
		/*SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date = new java.sql.Date(format.parse(data).getTime());*/
		
		Statement stmt = null;
		//ResultSet rs = null;
		
		String query = "INSERT INTO LUCROMES VALUES (" +
						valor + ", null)";
		
		try {
			stmt = con.conectar().createStatement();
			stmt.execute(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
							
	}
	
	public void inserirLucroAno(double valor, Conexao con) {
		
		/*SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date = new java.sql.Date(format.parse(data).getTime());*/
		
		Statement stmt = null;
		//ResultSet rs = null;
		
		String query = "INSERT INTO LUCROANO VALUES (" +
						valor + ", null)";
		
		try {
			stmt = con.conectar().createStatement();
			stmt.execute(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
							
	}
	
	public List<Lancamento> consultarLancamentos(Conexao con){
		List<Lancamento> lista = new ArrayList<Lancamento>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM LANCAMENTO "
					 + "ORDER BY data";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				Lancamento l = new Lancamento();
				
				l.setTipo(rs.getString("tipo"));
				l.setValor(rs.getDouble("valor"));
				l.setDescricao(rs.getString("descricao"));
				l.setPagamento(rs.getString("pagamento"));
				l.setParcelas(rs.getString("parcelas"));
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String dataFormatada = df.format(rs.getDate("data"));
				
				l.setData(dataFormatada);

				lista.add(l);
			}
			return lista;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Lucro> consultarLucros(Conexao con){
		List<Lucro> listaLucro = new ArrayList<Lucro>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM LUCRODIA "
					 + "order by data";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				Lucro lucro = new Lucro();
				
				lucro.setLucro(rs.getDouble("valor"));
				
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String dataFormatada = df.format(rs.getDate("data"));
				
				lucro.setDia(dataFormatada);

				listaLucro.add(lucro);
			}
			return listaLucro;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<LucroSemana> consultarLucroSemana(Conexao con){
		List<LucroSemana> listaLucroSemana = new ArrayList<LucroSemana>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM LUCROSEMANA";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				LucroSemana lucroSemana = new LucroSemana();
				
				lucroSemana.setValor(rs.getDouble("valor"));
				
				lucroSemana.setSemana(rs.getInt("semana"));

				listaLucroSemana.add(lucroSemana);
			}
			return listaLucroSemana;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<LucroMes> consultarLucroMes(Conexao con){
		List<LucroMes> listaLucroMes = new ArrayList<LucroMes>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM LUCROMES";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				LucroMes lucroMes = new LucroMes();
				
				lucroMes.setValor(rs.getDouble("valor"));
				
				lucroMes.setMes(rs.getInt("mes"));

				listaLucroMes.add(lucroMes);
			}
			return listaLucroMes;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<LucroAno> consultarLucroAno(Conexao con){
		List<LucroAno> listaLucroAno = new ArrayList<LucroAno>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM LUCROANO";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				LucroAno lucroAno = new LucroAno();
				
				lucroAno.setValor(rs.getDouble("valor"));
				
				lucroAno.setAno(rs.getInt("ano"));

				listaLucroAno.add(lucroAno);
			}
			return listaLucroAno;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Lancamento> filtrarConsulta(String tipo, String data, String desc, Conexao con) throws ParseException{
		
		List<Lancamento> lista = new ArrayList<Lancamento>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		if(tipo.equals("") && desc.equals("") && !data.equals("")) {
			//Data
			java.sql.Date date = new java.sql.Date(format.parse(data).getTime());
			
			query = "SELECT * FROM LANCAMENTO "
					 + "WHERE data = '" + date + "'";
			
		}
		else if(data.equals("") && desc.equals("") && !tipo.equals("")) {
			//Tipo
			query = "SELECT * FROM LANCAMENTO "
					 + "WHERE tipo = '" + tipo + "'";
		}
		else if(data.equals("") && tipo.equals("") && !desc.equals("")) {
			//Descricao
			query = "select * from lancamento"
					+" where descricao ilike '%" + desc + "%'";
		}	
		else if(data.equals("") && !tipo.equals("") && !desc.equals("")) {
			//Tipo e Descricao
			query = "select * from lancamento"
					+" where descricao ilike '%" + desc + "%'"
					+ " and tipo = '" + tipo + "'";
		}
		else if(!data.equals("") && tipo.equals("") && !desc.equals("")) {
			//Data e Descricao
			java.sql.Date date = new java.sql.Date(format.parse(data).getTime());
			
			query = "select * from lancamento"
					+" where descricao ilike '%" + desc + "%'"
					+ " and data = '" + date + "'";
		}
		else if(!data.equals("") && !tipo.equals("") && desc.equals("")) {
			//Tipo e Data
			java.sql.Date date = new java.sql.Date(format.parse(data).getTime());
			
			query = "SELECT * FROM LANCAMENTO "
					 + "WHERE tipo = '" + tipo + "' "
					 + "AND data = '" + date + "'";
		}
		else if(data.equals("") && tipo.equals("") && desc.equals("")) {
			//Nenhum
			return consultarLancamentos(con);
		}
		else {
			//Todos
			java.sql.Date date = new java.sql.Date(format.parse(data).getTime());
			
			query = "SELECT * FROM LANCAMENTO "
					 + "WHERE tipo = '" + tipo + "' "
					 + "AND data = '" + date + "'"
					 + " AND descricao ilike '%" + desc + "%'";
		}
		
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				Lancamento l = new Lancamento();
				
				l.setTipo(rs.getString("tipo"));
				l.setValor(rs.getDouble("valor"));
				l.setDescricao(rs.getString("descricao"));
				l.setPagamento(rs.getString("pagamento"));
				l.setParcelas(rs.getString("parcelas"));
				
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String dataFormatada = df.format(rs.getDate("data"));
				
				l.setData(dataFormatada);

				lista.add(l);
			}
			return lista;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int totalDias(Conexao con) {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select count(*) quant from lucrodia";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				int total = rs.getInt("quant");
				
				return total;
			}
			else {
				return -1;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public int filtrarLucroDia(String data, Conexao con) throws ParseException {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date = new java.sql.Date(format.parse(data).getTime());
		
		String query = "select count(*) quant from lucrodia " + 
					   "where data = '" + date + "'";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				int total = rs.getInt("quant");
				
				return total;
			}
			else {
				return -1;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public int totalSemanas(Conexao con) {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select count(*) quant from lucrosemana";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				int total = rs.getInt("quant");
				
				return total;
			}
			else {
				return -1;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public int totalMeses(Conexao con) {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select count(*) quant from lucromes";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				int total = rs.getInt("quant");
				
				return total;
			}
			else {
				return -1;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public int totalAnos(Conexao con) {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select count(*) quant from lucroano";
		
		try {
			stmt = con.conectar().createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				int total = rs.getInt("quant");
				
				return total;
			}
			else {
				return -1;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void removerLancamento(String tipo, double valor, String desc, java.sql.Date data, Conexao con) {
		
		Statement stmt = null;
		//ResultSet rs = null;
		
		String query = "DELETE FROM lancamento "
					 + "WHERE tipo = '" + tipo + "' "
					 + "AND valor = " + valor
					 + " AND descricao = '" + desc + "' "
					 + "AND data = '" + data + "'";
		
		try {
			stmt = con.conectar().createStatement();
			stmt.execute(query);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void removerLucroDia(java.sql.Date data, Conexao con) {
		
		Statement stmt = null;
		//ResultSet rs = null;
		
		String query = "DELETE FROM lucrodia "
					 + "WHERE data = '" + data + "'";
		
		try {
			stmt = con.conectar().createStatement();
			stmt.execute(query);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
