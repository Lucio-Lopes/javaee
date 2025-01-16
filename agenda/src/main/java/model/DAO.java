package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	//conexao
	//parametros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "Ornq933@";
	
	//metodo de conexao
	
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			return con;
		} catch (Exception e) {
			
			return null;
			
		}
		
	}
	//teste de conexao
	public void teste() {
		
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void inserirContato(Javabeans contato) {
		
		String create = "insert into contatos (nome,fone,email) values(?,?,?)";
		
		
		try {
			//abrir conexao
			Connection con = conectar();
			//preparar a query
			PreparedStatement pst = con.prepareStatement(create);
			//substituir as '?'
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			//executar query
			pst.executeUpdate();
			//Encerrar a conexao com o bd
			con.close();
			
		}catch(Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	public ArrayList<Javabeans> listarContatos(){
		//Criando um objeto para acessar a classe javabeans
		ArrayList<Javabeans> contatos = new ArrayList<>();
		
		String read = "select * from contatos order by nome";
		try {
			//abrir conexao com bd
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			//o laço abaixo irá executar enquanto existir contatos
			while(rs.next()){
				
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				
				contatos.add(new Javabeans(id,nome,fone,email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public void selecionarContato(Javabeans contato) {
		
		String read2 = "select * from contatos where id=?";
		
		try {
			
			Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(read2);
				pst.setString(1, contato.getId());
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					
					contato.setId(rs.getString(1));
					contato.setNome(rs.getString(2));
					contato.setFone(rs.getString(3));
					contato.setEmail(rs.getString(4));
					
				}
				con.close();
				
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	public void alterarContato(Javabeans contato) {
		
		String create = "update contatos set nome=?, fone=?, email=? where id=?";
		try {
			
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1,contato.getNome());
			pst.setString(2,contato.getFone());
			pst.setString(3,contato.getEmail());
			pst.setString(4,contato.getId());
			
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deletarContato(Javabeans contato) {
		
		String del = "delete from contatos where id=?";
		
		try {
			
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(del);
			pst.setString(1,contato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}
