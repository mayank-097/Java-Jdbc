package jdbc.ui;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import jdbc.bean.Agent;
import jdbc.bean.Client;
import jdbc.bean.Transactions;
import jdbc.db.DBManagerClassAgent;
import jdbc.db.DBManagerClassClient;
import jdbc.db.DBManagerClassTransaction;
import java.util.List;
import java.util.Scanner;

import jdbc.bean.Client;
import jdbc.db.DBManagerClassClient;

public class MainClass {

	static Scanner sc = new Scanner(System.in);
	static DBManagerClassClient mysqldb1 = new DBManagerClassClient();
	static DBManagerClassAgent mysqldb2 = new DBManagerClassAgent();
	static DBManagerClassTransaction mysqldb3 = new DBManagerClassTransaction();

	public static void main(String[] args) {

		System.out.println("1.Add client");
		System.out.println("2.Update phone number");
		System.out.println("3.Display Client based on Id");
		System.out.println("4.Display Client based on State");
		System.out.println("5.Display Client base on Walletbalance");
		System.out.println("6.Count total Clients");
		System.out.println("7.Total wallet balnce of clients");
		System.out.println("8.Display name and phone number based on state and wallet balance");
		System.out.println("9.Display total Wallet balance based on state in ascending order");

		System.out.println("\n ---------------- \n");

		System.out.println("10.Add agent");
		System.out.println("11.Update Agent Phone Number");
		System.out.println("12.Display Agent based on State");
		System.out.println("13.Display Agent based on WalletBalance");
		System.out.println("14.Display Agent based on BankAccountName");
		System.out.println("15.Display Agent count");
		System.out.println("16.Count total walletbalance in all agents");
		System.out.println("17.Display Agent based on WalletBalance and State");
		System.out.println("18.Display Agent based on WalletBalance in Ascending order");

		System.out.println("\n ---------------- \n");

		System.out.println("19.Add Transaction");
		System.out.println("20.Transaction Get based on Date");
		System.out.println("21.Total Transactions Based on Tdate");
		System.out.println("22.Display Average of Transactions Based on Tdate");
		System.out.println("23.Display Transaction Info and Status of Transactions Based on Tdate");
		System.out.println("24. FUND Transfer.. ");
		System.out.println("25. Average cashback Transfer.. ");

		System.out.println("26. -- Exit-- ");

		int ch = Integer.parseInt(sc.nextLine());

		switch (ch) {
		case 1:
			addClient();
			break;
		case 2:
			updatePhoneNumber();
			break;
		case 3:
			displayClientBasedOnId();
			break;
		case 4:
			displayClientBasedOnState();
			break;

		case 5:
			displayClientBasedOnWalletbalance();
			break;

		case 6:
			countTotalClients();
			break;

		case 7:
			totalWalletBalance();
			break;

		case 8:
			displayNameAndPhoneNumber();
			break;

		case 9:
			displaytotalWalletBalanceInAscendingOrder();
			break;

		case 10:
			addAgent();
			break;
		case 11:
			updateAgentPhoneNumber();
			break;
		case 12:
			displayAgentBasedOnState();
			break;
		case 13:
			displayAgentBasedOnWalletbalance();
			break;
		case 14:
			displayAgentBasedOnBankAccountName();
			break;
		case 15:
			displayAgentCount();
			break;
		case 16:
			displayAgentCountOnTotalBalance();
			break;
		case 17:
			displayAgentBasedOnStateAndWalletBalance();
			break;
		case 18:
			displayAgentWalletStateSorting();
			break;
		case 19:
			addTransaction();
			break;
		case 20:
			getbyDate();
			break;
		case 21:
			displayTotalTransactionBasedOnTDate();
			break;
		case 22:
			displayAvgOfTransactionBasedOnTDate();
			break;
		case 23:
			displayInfoOfTransactionBasedOnTDate();
			break;
		case 24:
			System.out.println("transfer-  : " + mysqldb3.doTransaction());
		case 25:
			System.out.println("Cashback_added-  : " + mysqldb3.avgCashbackPro());
		case 26:
			System.exit(0);
			break;
		}

	}

	private static void displaytotalWalletBalanceInAscendingOrder() {
		// TODO Auto-generated method stub

		try {
			mysqldb1.displaytotalWalletBalanceInAscendingOrder();
		} catch (SQLException e) {
			System.out.println("SQLException in Main" + e);
		}
	}

	public static void displayClientBasedOnState() {
		// TODO Auto-generated method stub
		try {

			System.out.println("Enter the state");
			String state = sc.nextLine();
			List<Client> l = mysqldb1.getClientBasedOnState(state);

			for (Client cl : l) {
				System.out.println(cl);
			}
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

//		create client
	public static void addClient() {

		try {
			System.out.println("\nenter the details to add in client");
			System.out.println("enter the id");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the username ");
			String name = sc.nextLine();
			System.out.println("Enter the password");
			String password = sc.nextLine();
			System.out.println("Enter the wallet balance");
			int balance = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the state");
			String state = sc.nextLine();
			System.out.println("Enter the phone number");
			long number = Long.parseLong(sc.nextLine());
			boolean b = mysqldb1.createClient(new Client(id, name, password, balance, state, number));

			System.out.println(b);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

//		upatdate phone number
	public static void updatePhoneNumber() {

		try {

			System.out.println("Enter the id ");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the new phone number");
			long number = Long.parseLong(sc.nextLine());

			boolean b = mysqldb1.updatePhone(number, id);
			if (b == true) {
				System.out.println("updated succesfully ");
			} else {
				System.out.println("re-enter id Number ");
			}
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

//		getclient id
	public static void displayClientBasedOnId() {
		// TODO Auto-generated method stub
		try {

			System.out.println("Enter the client id");
			int id = Integer.parseInt(sc.nextLine());
			Client a = mysqldb1.getClientBasedOnClientId(id);
			if (a != null) {
				System.out.println(a);
			} else {
				System.out.println("re-enter id Number ");
			}
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void displayClientBasedOnWalletbalance() {
		// TODO Auto-generated method stub
		try {

			System.out.println("Enter the Wallet balance");
			int balance = Integer.parseInt(sc.nextLine());
			List<Client> l = mysqldb1.getClientBasedOnWalletBalance(balance);

			for (Client cl : l) {
				System.out.println(cl);
			}
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void countTotalClients() {
		// TODO Auto-generated method stub
		try {

			int count = mysqldb1.countTotalClients();

			System.out.println("Total clients are : " + count);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void totalWalletBalance() {
		try {
			long sum = mysqldb1.totalWalletBalance();
			System.out.println("Total wallet balance of client are : " + sum);

		} catch (SQLException e) {
			System.out.println("Exception in Main" + e);
		}
	}

	public static void displayNameAndPhoneNumber() {
		try {
			System.out.println("Enter the state");
			String state = sc.nextLine();
			System.out.println("Enter the wallet balance");
			int balance = Integer.parseInt(sc.nextLine());
			mysqldb1.displayNameAndPhoneNumber(state, balance);

		} catch (SQLException e) {
			System.out.println("Exception in Main" + e);
		}
	}
	
	public static void displayTotalTransactionBasedOnTDate() {
		String dt = "2021-10-08";
		Date date = Date.valueOf(dt);
		try {
			List<Transactions> a = mysqldb3.getTransactionCountBasedOnTDate(date);
			System.out.println("Total Transactions are: " + a.size());
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}
	}

	public static void displayAvgOfTransactionBasedOnTDate() {
		String dt = "2021-10-08";
		Date date = Date.valueOf(dt);
		try {
			double a = mysqldb3.getAgentCountOnWalletBalance(date);
			System.out.println("Total Transactions are: " + a);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}
	}

	public static void displayInfoOfTransactionBasedOnTDate() {
		String dt = "2021-10-10";
		Date date = Date.valueOf(dt);
		try {
			Map<Transactions, String> a = mysqldb3.getTransactionInfoBasedOnTdate(date);
			a.forEach((key, value) -> System.out.println(key + "  Status: " + value));
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}
	}

	private static void getbyDate() {
		// TODO Auto-generated method stub
		Date date = Date.valueOf("2021-10-08");
		try {
			mysqldb3.getTransactionBasedOnTdate(date);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	private static void addTransaction() {
		// TODO Auto-generated method stub
		try {
			String dt = "2021-10-08";
			Date date = Date.valueOf(dt);
			boolean b = mysqldb3.createTransaction(new Transactions(325, date, 113, 513, 2000, "ctoa"));
			System.out.println(b);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void displayAgentWalletStateSorting() {
		// TODO Auto-generated method stub
		try {
			mysqldb2.displayAgentStateSort("UP");
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void displayAgentBasedOnStateAndWalletBalance() {
		// TODO Auto-generated method stub
		try {
			mysqldb2.displayAgentStateWallet("UP", 1000);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void addAgent() {
		// TODO Auto-generated method stub
		try {
			boolean b = mysqldb2.createAgent(new Agent(121, "Naman", "Punjab", "sbi", 221, 8767609033L, 2050));
			System.out.println(b);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void displayAgentCountOnTotalBalance() {
		// TODO Auto-generated method stub
		try {
			int a = mysqldb2.getAgentCount();
			System.out.println("Total Agents are: " + a);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void displayAgentCount() {
		// TODO Auto-generated method stub
		try {
			int a = mysqldb2.getAgentCount();
			System.out.println("Total Agents are: " + a);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void displayAgentBasedOnBankAccountName() {
		// TODO Auto-generated method stub
		try {
			List<Agent> a = mysqldb2.getAgentBasedOnBankAcc("BOB");
			for (Agent acc : a) {
				System.out.println(acc);
			}
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void displayAgentBasedOnWalletbalance() {
		// TODO Auto-generated method stub
		try {
			List<Agent> a = mysqldb2.getAgentBasedOnWalletbalance(1000);
			for (Agent acc : a) {
				System.out.println(acc);
			}
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void displayAgentBasedOnState() {
		// TODO Auto-generated method stub

		try {
			List<Agent> a = mysqldb2.getAgentBasedOnState("Punjab");
			for (Agent acc : a) {
				System.out.println(acc);
			}
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

	public static void updateAgentPhoneNumber() {
		// TODO Auto-generated method stub
		try {
			boolean b = mysqldb2.updatePh(8877880099L, 118);
			System.out.println(b);
		} catch (SQLException e) {
			System.out.println("Exception in Main " + e);
		}

	}

}
