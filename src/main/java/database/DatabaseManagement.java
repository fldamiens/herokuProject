package database;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import metier.Approval;
import metier.ManualResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URISyntaxException;
import java.net.URI;
import java.sql.*;
import org.apache.commons.dbcp2.*;

public class DatabaseManagement {
	
	private BasicDataSource connectionPool;

	public DatabaseManagement() throws Exception{
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		connectionPool = new BasicDataSource();
	
		if (dbUri.getUserInfo() != null) {
			connectionPool.setUsername(dbUri.getUserInfo().split(":")[0]);
			connectionPool.setPassword(dbUri.getUserInfo().split(":")[1]);
		}
		connectionPool.setDriverClassName("org.postgresql.Driver");
		connectionPool.setUrl(dbUrl);
		connectionPool.setInitialSize(1);
	}
	
	public Approval insertData(Approval approval) throws Exception{	
		Connection connection = connectionPool.getConnection();

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS approval (id SERIAL PRIMARY KEY, idAccount varchar(225), name varchar(100), manualResponse varchar(20))");
		
		PreparedStatement st = connection.prepareStatement("INSERT INTO approval (idAccount, name, manualResponse) VALUES (?, ?, ?)");
		st.setString(1, approval.getIdAccount());
		st.setString(2, approval.getName());
		st.setString(3, approval.getManualResponse().toString());
		st.executeUpdate();
		st.close();
		
		stmt.close();
		return approval;
	}
	
	public void deleteData(String idApproval) throws Exception{	
		Connection connection = connectionPool.getConnection();

		PreparedStatement st = connection.prepareStatement("DELETE FROM approval WHERE idAccount = ?");
		st.setString(1, idApproval);
		st.executeUpdate();
		st.close();
	}
	
	public List<Approval> selectDatas() throws Exception{
		List<Approval> result = new ArrayList();
		Connection connection = connectionPool.getConnection();

		PreparedStatement st = connection.prepareStatement("SELECT * FROM approval");
		ResultSet rs = st.executeQuery();
		while (rs.next()){
			Approval tmp = new Approval(rs.getString("idAccount"), rs.getString("name"), ManualResponse.valueOf(rs.getString("manualResponse").toString()));
			result.add(tmp);   
		}
		rs.close();
		st.close();
		return result;
	}
	
	public Approval selectData(String id) throws Exception{
		Approval result = null;
		Connection connection = connectionPool.getConnection();

		PreparedStatement st = connection.prepareStatement("SELECT * FROM approval WHERE idAccount = ?");
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		while (rs.next()){
			Approval tmp = new Approval(rs.getString("idAccount"), rs.getString("name"), ManualResponse.valueOf(rs.getString("manualResponse").toString()));
			result = tmp;   
		}
		rs.close();
		st.close();
		return result;
	}
}
