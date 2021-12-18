package jdbc.bean;

public class Agent {

	private int agentid;
	private String agentName;
	private String agentState;
	private String linkedBankAccountName;
	private long bankAccountNumber;
	private long phoneNumber;
	private int walletBalance;

	public Agent(int agentid, String agentName, String agentState, String linkedBankAccountName, long bankAccountNumber,
			long phoneNumber, int walletBalance) {
		super();
		this.agentid = agentid;
		this.agentName = agentName;
		this.agentState = agentState;
		this.linkedBankAccountName = linkedBankAccountName;
		this.bankAccountNumber = bankAccountNumber;
		this.phoneNumber = phoneNumber;
		this.walletBalance = walletBalance;
	}

	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAgentid() {
		return agentid;
	}

	public void setAgentid(int agentid) {
		this.agentid = agentid;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentState() {
		return agentState;
	}

	public void setAgentState(String agentState) {
		this.agentState = agentState;
	}

	public String getLinkedBankAccountName() {
		return linkedBankAccountName;
	}

	public void setLinkedBankAccountName(String linkedBankAccountName) {
		this.linkedBankAccountName = linkedBankAccountName;
	}

	public long getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(long bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(int walletBalance) {
		this.walletBalance = walletBalance;
	}

	@Override
	public String toString() {
		return "Agent [agentid=" + agentid + ", agentName=" + agentName + ", agentState=" + agentState
				+ ", linkedBankAccountName=" + linkedBankAccountName + ", bankAccountNumber=" + bankAccountNumber
				+ ", phoneNumber=" + phoneNumber + ", walletBalance=" + walletBalance + "]";
	}

}
