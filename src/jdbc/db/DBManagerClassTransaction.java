package jdbc.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.bean.Agent;
import jdbc.bean.Transactions;

public class DBManagerClassTransaction {

	Connection con = null;

	public DBManagerClassTransaction() {
		con = DBConfig.con;
	}

	public boolean doTransaction() {
		try {
			int agentid = 9630;
			int clientid = 8630;

			int amountTransfer = 1500;
			int cashback = (int) (amountTransfer * 0.05);

			// for Agent
			String sqlQuery1 = "select walletBalance as WB from assignment1.agent where agentId = ?";

			PreparedStatement ps1 = con.prepareStatement(sqlQuery1);
			ps1.setInt(1, agentid);

			ResultSet rs1 = ps1.executeQuery(); // call executeQuery in select statement
			int agentOldBal = 0;
			while (rs1.next()) {
				agentOldBal = rs1.getInt("WB");
			}

			// for Client
			String sqlQuery2 = "select walletBalance as WB2 from assignment1.client where clientId = ?";

			PreparedStatement ps2 = con.prepareStatement(sqlQuery2);
			ps2.setInt(1, clientid);

			ResultSet rs2 = ps2.executeQuery(); // call executeQuery in select statement
			int clientOldBal = 0;
			while (rs2.next()) {
				clientOldBal = rs2.getInt("WB2");
			}

			int newWalletBalance_Agent = agentOldBal + amountTransfer;
			String q1 = "update assignment1.agent set walletBalance = ? where agentId = ?";

			int newWalletBalance_Client = clientOldBal - amountTransfer + cashback;
			String q2 = "update assignment1.client set walletBalance = ? where clientId  = ?";

			con.setAutoCommit(false);

			PreparedStatement ps3 = con.prepareStatement(q1);
			ps3.setInt(1, newWalletBalance_Agent);
			ps3.setInt(2, agentid);

			PreparedStatement ps4 = con.prepareStatement(q2);
			ps4.setInt(1, newWalletBalance_Client);
			ps4.setInt(2, clientid);

			int i1 = ps3.executeUpdate();
			int i2 = ps4.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

			return i1 == 1 & i2 == 1;

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;

	}

	public boolean avgCashbackPro() {
		try {
			int month = 10;
			// int bal = 300;
			// for Agent
			String sqlQuery1 = "update assignment1.client set walletBalance = walletBalance * 1.75 where clientId in( select ClientID from assignment1.transactions t where (select month(t.TDate)) = ? group by ClientID having avg(walletBalance)>1000)";

			PreparedStatement ps1 = con.prepareStatement(sqlQuery1);
			ps1.setInt(1, month);

			int i1 = ps1.executeUpdate();

			return i1 == 1;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean createTransaction(Transactions a) throws SQLException {
		boolean isAdded = false;

		// code to add account in DB
		String sqlQuery = "insert into assignment1.transactions values(?,?,?,?,?,?)";
		// String sqlQuery = "insert into rapipay.account values("+
		// a.getAccountNumber()+",'"+getAccountholderName()+"',?,"+Date.valueOf(a.getDob())+",?)";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setInt(1, a.getTId());
		ps.setDate(2, a.getTDate());
		ps.setInt(3, a.getAgentID());
		ps.setInt(4, a.getClientID());
		ps.setInt(5, a.getAmountOfTransacction());
		ps.setString(6, a.getTType());

		int i = ps.executeUpdate(); // insert , delete , update , returns how many rows effected

		if (i == 1)
			isAdded = true;

		return isAdded;
	}

	public void getTransactionBasedOnTdate(Date dt) throws SQLException {

		String sqlQuery = "Select clientUsername,phoneNumber,clientState,AmountofTransaction,TDate from assignment1.client,assignment1.transactions where transactions.ClientID =client.ClientID && TDate=?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setDate(1, dt);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement

		while (rs.next()) {

			System.out.println(rs.getString("clientUsername") + "  " + rs.getLong("phoneNumber") + " "
					+ rs.getString("clientState") + " " + rs.getInt("AmountofTransaction") + " "
					+ rs.getDate("TDate"));
		}

	}

	public List<Transactions> getTransactionCountBasedOnTDate(Date dt) throws SQLException {
		List<Transactions> acc = new ArrayList<>();
		Transactions a = null;

		String sqlQuery = "Select * from assignment1.transactions where TDate=?";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setDate(1, dt);
		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement

		while (rs.next()) {
			a = new Transactions();
			a.setTId(rs.getInt("Tid"));
			a.setTDate(rs.getDate("TDate"));
			a.setAgentID(rs.getInt("AgentId"));
			a.setClientID(rs.getInt("ClientID"));
			a.setAmountOfTransacction(rs.getInt("AmountofTransaction"));
			a.setTType(rs.getString("TType"));
			acc.add(a);
		}

		return acc;
	}

	public double getAgentCountOnWalletBalance(Date dt) throws SQLException {

		String sqlQuery = "Select avg(AmountofTransaction) from assignment1.transactions where TDate=?;";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setDate(1, dt);
		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement
		double sum = 0;
		while (rs.next()) {
			sum = rs.getDouble(1);
		}

		return sum;
	}

	public Map<Transactions, String> getTransactionInfoBasedOnTdate(Date dt) throws SQLException {
		Map<Transactions, String> map = new HashMap<>();
		Transactions a = null;

		String sqlQuery = "Select if(AmountofTransaction<5000,\"Good\",\"Ok\") as Status,Tid,TDate,AgentId,ClientID,AmountofTransacction,TType from assignment1.transactions where TDate=?;";

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setDate(1, dt);

		ResultSet rs = ps.executeQuery(); // call executeQuery in select statement
		while (rs.next()) {
			a = new Transactions();
			a.setTId(rs.getInt("Tid"));
			a.setTDate(rs.getDate("Tdate"));
			a.setAgentID(rs.getInt("agentId"));
			a.setClientID(rs.getInt("clientID"));
			a.setAmountOfTransacction(rs.getInt("AmountofTransaction"));
			a.setTType(rs.getString("TType"));
			String str = rs.getString("Status");
			map.put(a, str);
		}

		return map;
	}

}
