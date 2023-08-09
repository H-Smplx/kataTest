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

	private static Integer calc(String input) throws OperandsException {
		ArithmeticExpression ae = new ArithmeticExpression(input);
		
		if(ae.getArithmeticOperation() == '+') {
			return Integer.valueOf(ae.getLeftOperand()) + Integer.valueOf(ae.getRightOperand());
		}
		else if(ae.getArithmeticOperation() == '*') {
			return Integer.valueOf(ae.getLeftOperand()) * Integer.valueOf(ae.getRightOperand());
		}
		else if(ae.getArithmeticOperation() == '/') {
			return Integer.valueOf(ae.getLeftOperand()) / Integer.valueOf(ae.getRightOperand());
		}
		else if(ae.getArithmeticOperation() == '-' && ae.getNumeralSystem().equals("roman") && Integer.valueOf(ae.getLeftOperand()) < Integer.valueOf(ae.getRightOperand())) {
			throw new OperandsException("В римской системе нет отрицательных чисел");
		}
		else {
			return Integer.valueOf(ae.getLeftOperand()) - Integer.valueOf(ae.getRightOperand());
		}
	}
}