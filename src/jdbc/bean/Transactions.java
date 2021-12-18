package jdbc.bean;

import java.sql.Date;

public class Transactions {

	private int TId;
	private Date TDate;
	private int AgentID;
	private int ClientID;
	private int amountOfTransacction;
	private String TType;

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(int tId, Date tDate, int agentID, int clientID, int amountOfTransacction, String tType) {
		super();
		TId = tId;
		TDate = tDate;
		AgentID = agentID;
		ClientID = clientID;
		this.amountOfTransacction = amountOfTransacction;
		TType = tType;
	}

	public int getTId() {
		return TId;
	}

	public void setTId(int tId) {
		TId = tId;
	}

	public Date getTDate() {
		return TDate;
	}

	public void setTDate(Date tDate) {
		TDate = tDate;
	}

	public int getAgentID() {
		return AgentID;
	}

	public void setAgentID(int agentID) {
		AgentID = agentID;
	}

	public int getClientID() {
		return ClientID;
	}

	public void setClientID(int clientID) {
		ClientID = clientID;
	}

	public int getAmountOfTransacction() {
		return amountOfTransacction;
	}

	public void setAmountOfTransacction(int amountOfTransacction) {
		this.amountOfTransacction = amountOfTransacction;
	}

	public String getTType() {
		return TType;
	}

	public void setTType(String tType) {
		TType = tType;
	}

	@Override
	public String toString() {
		return "Transactions [TId=" + TId + ", TDate=" + TDate + ", AgentID=" + AgentID + ", ClientID=" + ClientID
				+ ", amountOfTransacction=" + amountOfTransacction + ", TType=" + TType + "]";
	}

}
