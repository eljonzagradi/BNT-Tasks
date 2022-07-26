package application;

public class Modal {
	
	public float calculate(long num1, long num2, String op) {
		
		switch(op) {
		
		case "+":
			
			return num1 + num2;
			
        case "-":
			
			return num1 - num2;

			
        case "*":
        	
			return num1 * num2;

        case "/":
        	
        	if(num2 == 0 )
        		return 0;
        	return num1 / num2;
	
	default: 
		return 0;

		}
		
	}

}
