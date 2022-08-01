package application;

import java.sql.Date;
import java.time.LocalDate;

public class DisabledRange {
	
	private final LocalDate check_in;
	private final LocalDate check_out;
	
	public DisabledRange(Date in, Date out) {	
		this.check_in = in.toLocalDate();
		this.check_out = out.toLocalDate();
	}

	public LocalDate getCheck_in() {
		return check_in;
	}

	public LocalDate getCheck_out() {
		return check_out;
	}
	
	
	

}
