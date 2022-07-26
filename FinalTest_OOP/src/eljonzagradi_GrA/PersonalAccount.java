package eljonzagradi_GrA;
import java.util.Date;

public class PersonalAccount extends BankAccount {
	
	protected String name;
	protected String surname;
	


	public PersonalAccount(String name,String surname,String iban, Date date, String currency) {
		super(iban, date, currency);
		this.name = name;
		this.surname = surname;
		
	}
	

	@Override
	public double calculateMonthFee() {
		
		double fee = 0;
		
		if(currency.equals("Dollar")) {
			fee = 1.5;
		} 
		
		else if(currency.equals("Euro")) {
			fee = 1.5;
		}
		
		else if(currency.equals("ALL")) {
			fee = 150;
		}
		return fee;
	}

}
