package calc;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in); 
		
		try {
			System.out.println(calc(in.nextLine()));
		} catch (OperandsException e) {
			e.printStackTrace();
		}
		in.close();
	}

	private static Object calc(String input) throws OperandsException {		
		ArithmeticExpression ae = new ArithmeticExpression(input);
		Object result = null;
		
		if(ae.getNumeralSystem().equals("roman")) {
			if(ae.getArithmeticOperation() == '+') {
				result = new RomanNumsDetection(Integer.valueOf(ae.getLeftOperand()) + Integer.valueOf(ae.getRightOperand())).result();
			}
			else if(ae.getArithmeticOperation() == '*') {
				result = new RomanNumsDetection(Integer.valueOf(ae.getLeftOperand()) * Integer.valueOf(ae.getRightOperand())).result();
			}
			else if(ae.getArithmeticOperation() == '/') {
				result = new RomanNumsDetection(Integer.valueOf(ae.getLeftOperand()) / Integer.valueOf(ae.getRightOperand())).result();
			}
			else if(ae.getArithmeticOperation() == '-' && Integer.valueOf(ae.getLeftOperand()) <= Integer.valueOf(ae.getRightOperand())) {
				throw new OperandsException("В римской системе отсутствуют 0 и отрицательные числа");
			}
			else {
				result = new RomanNumsDetection(Integer.valueOf(ae.getLeftOperand()) - Integer.valueOf(ae.getRightOperand())).result();
			}
		}
		
		if(ae.getNumeralSystem().equals("arabic")) {
			if(ae.getArithmeticOperation() == '+') {
				result = Integer.valueOf(ae.getLeftOperand()) + Integer.valueOf(ae.getRightOperand());
			}
			else if(ae.getArithmeticOperation() == '*') {
				result = Integer.valueOf(ae.getLeftOperand()) * Integer.valueOf(ae.getRightOperand());
			}
			else if(ae.getArithmeticOperation() == '/') {
				result = Integer.valueOf(ae.getLeftOperand()) / Integer.valueOf(ae.getRightOperand());
			}
			else {
				result = Integer.valueOf(ae.getLeftOperand()) - Integer.valueOf(ae.getRightOperand());
			}
		}
		return result;
	}
}