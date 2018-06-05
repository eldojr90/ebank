package br.com.ebank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ebank.data.ConnectionDB;
import br.com.ebank.model.Cliente;

public class ClienteDAO {

	private Connection con;

	public ClienteDAO() {
		super();
		this.con = ConnectionDB.getConnection();
	}
	
	public boolean insert(Cliente u) {
		
		PreparedStatement ps = null;
		
		try {
			
			String sql = "INSERT INTO cliente(nome, cpf, senha, agencia, conta, saldo) " + 
						 "VALUES (?, ?, upper(md5(?)), ?, ?,?);";
			
			ps = this.con.prepareStatement(sql);
			ps.setString(1, u.getNome());
			ps.setString(2, u.getCpf());
			ps.setString(3, u.getSenha());
			ps.setInt(4, u.getAgencia());
			ps.setInt(5, u.getConta());
			ps.setDouble(6, u.getSaldo());
			
			return ps.executeUpdate() == 1;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	public Cliente findById(int id) {
		
		String sql = "SELECT * FROM cliente WHERE id = ?";
		
		try {
			
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			return new Cliente(rs.getInt("id"),rs.getString("nome"),rs.getString("cpf"),rs.getString("senha"),
							   rs.getInt("agencia"),rs.getInt("conta"),rs.getDouble("saldo"));
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return null;
			
		} 
		
		
	}
	
	public int login(int ag, int cc, String senha) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {

			String sql = "SELECT id "
						+ "FROM cliente "
						+ "WHERE agencia = ? "
						+ "AND conta = ? "
						+ "AND senha = upper(md5(?)); ";

			ps = this.con.prepareStatement(sql);
			ps.setInt(1, ag);
			ps.setInt(2, cc);
			ps.setString(3, senha);
			
			rs = ps.executeQuery();
			rs.next();
			
			return rs.getInt(1);
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			return -1;
			
		} 
		
	}
	
	public boolean existeNome(String nome) {
		
		String sql = "SELECT (SELECT COUNT(*) FROM cliente WHERE nome = ?) = 1 v";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			
			ps = this.con.prepareStatement(sql);
			ps.setString(1, nome);
			
			rs = ps.executeQuery();
			rs.next();
			
			return rs.getBoolean(1);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public boolean existeCpf(String cpf) {
		
		String sql = "SELECT (SELECT COUNT(*) FROM cliente WHERE cpf = ?) = 1 v";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			
			ps = this.con.prepareStatement(sql);
			ps.setString(1, cpf);
			
			rs = ps.executeQuery();
			rs.next();
			
			return rs.getBoolean(1);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public boolean existeConta(int conta, int agencia) {
		
		String sql = "SELECT (SELECT COUNT(*) FROM cliente WHERE conta = ? AND agencia = ?) = 1 v";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = this.con.prepareStatement(sql);
			ps.setInt(1, conta);
			ps.setInt(2, agencia);
			
			rs = ps.executeQuery();
			rs.next();
			
			return rs.getBoolean(1);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public boolean saldoDisponivel(int conta, double valorTransf) {
		
		String sql = "SELECT (SELECT saldo FROM cliente WHERE conta = ?) >= ? v";
		
		try {
			
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, conta);
			ps.setDouble(2, valorTransf);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			sql = sql.replace("conta = ?", "conta = "+conta);
			
			System.out.println(sql);
			System.out.println("Saldo "+(rs.getBoolean(1)?"":"in")+"disponível");
			
			return rs.getBoolean(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public double getSaldo(int conta) {
		
		String sql = "SELECT saldo FROM cliente WHERE conta = ?";
		
		try {
			
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setInt(1, conta);
		
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			return rs.getDouble(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return 0;
		
	}
	
	public String getNome(String cpf) {
		
		String sql = "SELECT nome FROM cliente WHERE cpf = ?";
		
		try {
			
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setString(1, cpf);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			return rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public boolean debitar(int conta, double valor) {
	
		double novoSaldo = this.getSaldo(conta) - valor;
		
		System.out.println("Débito - novo saldo: "+novoSaldo);
		
		String sql = "UPDATE cliente SET saldo = ? WHERE conta = ?;";
		
		try {

			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setDouble(1, novoSaldo);
			ps.setInt(2, conta);
			
			return ps.executeUpdate() == 1;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	public boolean creditar(int conta, double valor) {
		
		double novoSaldo = this.getSaldo(conta) + valor;
		
		System.out.println("Crédito - novo saldo: "+novoSaldo);
		
		String sql = "UPDATE cliente SET saldo = ? WHERE conta = ?;";
		
		try {

			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setDouble(1, novoSaldo);
			ps.setInt(2, conta);
			
			return ps.executeUpdate() == 1;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
		
	}
	
}
