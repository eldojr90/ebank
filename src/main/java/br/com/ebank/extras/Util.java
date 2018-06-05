package br.com.ebank.extras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.ebank.data.ConnectionDB;

public class Util {

	public static String convertePraReais(double v) {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(v);
	}
	
	public static boolean validaParametros(String[] parametros) {
		for(String p:parametros)
			if(p == "") return false;  
		return true;
	}
	
	public static boolean validaAtributos(Object[] atributos) {
		for(Object a:atributos)
			if(a == null) return false;  
		return true;
	}
	
	public static String md5(String str) {
		
		try {
			
			PreparedStatement ps = ConnectionDB.getConnection().prepareStatement("select upper(md5(?)) md5;");
			ps.setString(1, str);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			return rs.getString(1);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return "";
		
	}
	
}
