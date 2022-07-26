package eljonzagradi_GrA;
import java.util.Date;

public abstract class BankAccount implements TransactionManager {
	
	protected String iban;
	protected Date start_date;
	protected Date end_date;
	protected String currency;
	protected double balance;
	
	public BankAccount(String iban, Date start_date, String currency) {
		this.iban = iban;
		this.start_date = start_date;
		this.currency = currency;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public abstract double calculateMonthFee();
	
	@Override
	public void processTransaction() throws Exception {
		
		
		
	}
}
