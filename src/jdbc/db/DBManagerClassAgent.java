package jdbc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.bean.Agent;

public class DBManagerClassAgent {

	Connection con = null;

	public DBManagerClassAgent() {
		con = DBConfig.con;
	}

	public boolean createAgent(Agent a) throws SQLException {
		boolean isAdded = false;

		String sqlQuery = "insert into assignment1.agent values(?,?,?,?,?,?,?)";
		// String sqlQuery = "insert into rapipay.account values("+
		// a.getAccountNumber()+",'"+getAccountholderName()+"',?,"+Date.valueOf(a.getDob())+",?)";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setInt(1, a.getAgentid());
		ps.setString(2, a.getAgentName());
		ps.setString(3, a.getAgentState());
		ps.setString(4, a.getLinkedBankAccountName());
		ps.setLong(5, a.getBankAccountNumber());
		ps.setLong(6, a.getPhoneNumber());
		ps.setLong(7, a.getWalletBalance());

		int i = ps.executeUpdate(); // insert , delete , update , returns how many rows effected

		if (i == 1)
			isAdded = true;

		return isAdded;
	}

	public Agent getAccountBasedOnAgentId(int agentId) throws SQLException {
		Agent a = null;

		String sqlQuery = "select * from assignment1.agent where agentId = ?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setInt(1, agentId);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement

		while (rs.next()) {
			a = new Agent();
			a.setAgentid(rs.getInt("agentId"));
			a.setAgentName(rs.getString("agentName"));
			a.setAgentState(rs.getString("agentState"));
			a.setLinkedBankAccountName(rs.getString("linkedBankAccountName"));
			a.setBankAccountNumber(rs.getLong("bankAccountNumber"));
			a.setPhoneNumber(rs.getLong("phoneNumber"));
			a.setWalletBalance(rs.getInt("walletBalance"));
		}

		return a;
	}

	public boolean updatePh(long phone, int id) throws SQLException {
		boolean isAdded = false;

		// code to add account in DB
		String sqlQuery = "Update assignment1.agent set phoneNumber=? where agentId=?";
		// String sqlQuery = "insert into rapipay.account values("+
		// a.getAccountNumber()+",'"+getAccountholderName()+"',?,"+Date.valueOf(a.getDob())+",?)";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setLong(1, phone);
		ps.setInt(2, id);

		int i = ps.executeUpdate(); // insert , delete , update , returns how many rows effected

		if (i == 1)
			isAdded = true;

		return isAdded;
	}

	public List<Agent> getAgentBasedOnState(String agentState) throws SQLException {
		List<Agent> acc = new ArrayList<>();
		Agent a = null;

		String sqlQuery = "Select * from assignment1.agent where agentState=?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setString(1, agentState);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement

		while (rs.next()) {
			a = new Agent();
			a.setAgentid(rs.getInt("agentId"));
			a.setAgentName(rs.getString("agentName"));
			a.setAgentState(rs.getString("agentState"));
			a.setLinkedBankAccountName(rs.getString("linkedBankAccountName"));
			a.setBankAccountNumber(rs.getLong("bankAccountNumber"));
			a.setPhoneNumber(rs.getLong("phoneNumber"));
			a.setWalletBalance(rs.getInt("walletBalance"));
			acc.add(a);
		}

		return acc;
	}

	public List<Agent> getAgentBasedOnWalletbalance(int walletBal) throws SQLException {
		List<Agent> acc = new ArrayList<>();
		Agent a = null;

		String sqlQuery = "Select * from assignment1.agent where walletBalance=?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setInt(1, walletBal);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement

		while (rs.next()) {
			a = new Agent();
			a.setAgentid(rs.getInt("agentId"));
			a.setAgentName(rs.getString("agentName"));
			a.setAgentState(rs.getString("agentState"));
			a.setLinkedBankAccountName(rs.getString("linkedBankAccountName"));
			a.setBankAccountNumber(rs.getLong("bankAccountNumber"));
			a.setPhoneNumber(rs.getLong("phoneNumber"));
			a.setWalletBalance(rs.getInt("walletBalance"));
			acc.add(a);
		}

		return acc;
	}

	public List<Agent> getAgentBasedOnBankAcc(String bnkAcc) throws SQLException {
		List<Agent> acc = new ArrayList<>();
		Agent a = null;

		String sqlQuery = "Select * from assignment1.agent where linkedBankAccountName=?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setString(1, bnkAcc);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement

		while (rs.next()) {
			a = new Agent();
			a.setAgentid(rs.getInt("agentId"));
			a.setAgentName(rs.getString("agentName"));
			a.setAgentState(rs.getString("agentState"));
			a.setLinkedBankAccountName(rs.getString("linkedBankAccountName"));
			a.setBankAccountNumber(rs.getLong("bankAccountNumber"));
			a.setPhoneNumber(rs.getLong("phoneNumber"));
			a.setWalletBalance(rs.getInt("walletBalance"));
			acc.add(a);
		}

		return acc;
	}

	public int getAgentCount() throws SQLException {

		String sqlQuery = "Select * from assignment1.agent";

		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement
		int count = 0;
		while (rs.next()) {
			count++;
		}

		return count;
	}

	public int getAgentCountOnTotalBalance() throws SQLException {

		String sqlQuery = "Select Sum(walletBalance) as Total_Balance from assignment1.agent";

		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement
		int count = 0;
		while (rs.next()) {
			count++;
		}

		return count;
	}

	public void displayAgentStateWallet(String state, int balance) throws SQLException {
		// TODO Auto-generated method stub

		String sqlQuery = "Select agentName as Name,phoneNumber as Phone from assignment1.agent where agentState=? and walletBalance=?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setString(1, state);
		ps.setInt(2, balance);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement

		while (rs.next()) {

			System.out.println(rs.getString("Name") + "    " + rs.getLong("Phone"));
		}

	}

	public void displayAgentStateSort(String state) throws SQLException {
		// TODO Auto-generated method stub

		String sqlQuery = "Select agentState, sum(walletBalance) from assignment1.agent group by agentState order by sum(walletBalance)";

		PreparedStatement ps = con.prepareStatement(sqlQuery);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement

		while (rs.next()) {
			System.out.println(rs.getString("agentState") + "	wallet balance --  " + rs.getInt("sum(walletBalance)"));
		}

	}

}
