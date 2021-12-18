package jdbc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.bean.Client;

public class DBManagerClassClient {
	
	Connection con = null;

	public DBManagerClassClient() {
		con = DBConfig.con;
	}

	public boolean createClient(Client a) throws SQLException {
		boolean isAdded = false;

		// code to add account in DB
		String sqlQuery = "insert into assignment1.client values(?,?,?,?,?,?)";
		// String sqlQuery = "insert into rapipay.account values("+
		// a.getAccountNumber()+",'"+getAccountholderName()+"',?,"+Date.valueOf(a.getDob())+",?)";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setInt(1, a.getClientID());
		ps.setString(2, a.getClientUsername());
		ps.setString(3, a.getClientPassword());
		ps.setInt(4, a.getWalletBalance());
		ps.setString(5, a.getClientState());
		ps.setLong(6, a.getPhoneNumber());
		int i = ps.executeUpdate(); // insert , delete , update , returns how many rows effected

		if (i == 1)
			isAdded = true;

		return isAdded;
	}

	public Client getClientBasedOnClientId(int clientId) throws SQLException {
		Client a = null;

		String sqlQuery = "select * from assignment1.client where clientId = ?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setInt(1, clientId);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement

		while (rs.next()) {
			a = new Client();
			a.setClientID(rs.getInt("clientId"));
			a.setClientUsername(rs.getString("clientUsername"));
			a.setClientPassword(rs.getString("clientPassword"));
			a.setWalletBalance(rs.getInt("walletBalance"));
			a.setClientState(rs.getString("clientState"));
			a.setPhoneNumber(rs.getLong("phoneNumber"));
		}

		return a;
	}

	public boolean updatePhone(long num, int id) throws SQLException {
		String sqlQuery = "Update assignment1.client set phoneNumber=? where clientId=?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setLong(1, num);
		ps.setInt(2, id);

		int i = ps.executeUpdate();

		boolean isAdded = false;
		if (i == 1)
			isAdded = true;

		return isAdded;

	}
	
	public List<Client> getClientBasedOnId(int clientid) throws SQLException {
		List<Client> list = new ArrayList<>();
		Client c = null;
		String sqlQuery = "Select * from assignment1.client where clientId=?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.setInt(1, clientid);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			c = new Client();

			c.setClientID(rs.getInt("clientId"));
			c.setClientUsername(rs.getString("clientUsername"));
			c.setClientPassword(rs.getString("clientPassword"));
			c.setWalletBalance(rs.getInt("walletBalance"));
			c.setClientState(rs.getString("clientState"));
			c.setPhoneNumber(rs.getLong("phoneNumber"));
			list.add(c);
		}

		return list;
	}

	public List<Client> getClientBasedOnState(String clientState) throws SQLException {
		List<Client> list = new ArrayList<>();
		Client c = null;
		String sqlQuery = "Select * from assignment1.client where clientState=?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ps.setString(1, clientState);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			c = new Client();

			c.setClientID(rs.getInt("clientId"));
			c.setClientUsername(rs.getString("clientUsername"));
			c.setClientPassword(rs.getString("clientPassword"));
			c.setWalletBalance(rs.getInt("walletBalance"));
			c.setClientState(rs.getString("clientState"));
			c.setPhoneNumber(rs.getLong("phoneNumber"));
			list.add(c);
		}

		return list;
	}

	public List<Client> getClientBasedOnWalletBalance(int walletBal)throws SQLException
	{
		List<Client> list = new ArrayList<>();
		Client c=null;
		
		String sqlQuery = "Select * from assignment1.client where walletBalance>?";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setInt(1, walletBal);
		
		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement
		
		while(rs.next())
		{
			c =new Client();
			c.setClientID(rs.getInt("clientId"));
			c.setClientUsername(rs.getString("clientUsername"));
			c.setClientPassword(rs.getString("clientPassword"));
			c.setWalletBalance(rs.getInt("walletBalance"));
			c.setClientState(rs.getString("clientState"));
			c.setPhoneNumber(rs.getLong("phoneNumber"));
			list.add(c);
		}
		
		return list;
	}
	
	public int  countTotalClients()throws SQLException
	{
		int count=0;
		String sqlQuery = "Select count(*) from assignment1.client ";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			count=rs.getInt(1);
		}
	
		return count;
	}
	
	public int  totalWalletBalance()throws SQLException
	{
		int count=0;
		String sqlQuery = "Select sum(walletBalance) from assignment1.client ";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			count=rs.getInt(1);
		}
	
		return count;
	}
	
	public void displayNameAndPhoneNumber(String state , int balance)throws SQLException
	{
		
		String sqlQuery = "Select clientUsername , phoneNumber from assignment1.client where clientState =? and walletBalance>?";
		
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setString(1,state);
		ps.setInt(2,balance);
		
		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement
		
		while(rs.next())
		{
			
	        System.out.print(rs.getString("clientUsername")+"\t");
			System.out.println(rs.getLong("phoneNumber"));
			
			
		}
		
	}
	
	public void displaytotalWalletBalanceInAscendingOrder() throws SQLException
	{
		String sqlQuery = "Select sum(walletBalance) as balance, clientState as state from assignment1.client group by clientState order by sum(walletBalance) ";
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.print(rs.getInt("balance")+"\t");
			System.out.println(rs.getString("state"));
			
		}
		
	}
	


}
