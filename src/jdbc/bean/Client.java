package jdbc.bean;

public class Client {
	
	private int clientID;
	private String clientUsername;
	private String clientPassword;
	private int walletBalance;
	private String clientState;
	private long phoneNumber;

	public Client(int clientID, String clientUsername, String clientPassword, int walletBalance, String clientState,
			long phoneNumber) {
		super();
		this.clientID = clientID;
		this.clientUsername = clientUsername;
		this.clientPassword = clientPassword;
		this.walletBalance = walletBalance;
		this.clientState = clientState;
		this.phoneNumber = phoneNumber;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getClientUsername() {
		return clientUsername;
	}

	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public int getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(int string) {
		this.walletBalance = string;
	}

	public String getClientState() {
		return clientState;
	}

	public void setClientState(String clientState) {
		this.clientState = clientState;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Client [clientID=" + clientID + ", clientUsername=" + clientUsername + ", clientPassword="
				+ clientPassword + ", walletBalance=" + walletBalance + ", clientState=" + clientState
				+ ", phoneNumber=" + phoneNumber + "]";
	}


}
