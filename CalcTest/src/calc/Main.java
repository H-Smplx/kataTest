package calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	private static Map<Integer, String> romansResults = new HashMap<>();

	public static void main(String[] args) {
		String[] romansArr = new String[]{"I", "II", "III", "IV", "V", "VI","VII", "VIII", "IX", "X", 
						"XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
		for(int i = 0; i < romansArr.length; i++) {
			romansResults.put(i + 1, romansArr[i]);
		}
		
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
				result = romansResults.get(Integer.valueOf(ae.getLeftOperand()) + Integer.valueOf(ae.getRightOperand()));
			}
			else if(ae.getArithmeticOperation() == '*') {
				result = romansResults.get(Integer.valueOf(ae.getLeftOperand()) * Integer.valueOf(ae.getRightOperand()));
			}
			else if(ae.getArithmeticOperation() == '/') {
				result = romansResults.get(Integer.valueOf(ae.getLeftOperand()) / Integer.valueOf(ae.getRightOperand()));
			}
			else if(ae.getArithmeticOperation() == '-' && Integer.valueOf(ae.getLeftOperand()) <= Integer.valueOf(ae.getRightOperand())) {
				throw new OperandsException("В римской системе отсутствуют 0 и отрицательные числа");
			}
			else {
				result = romansResults.get(Integer.valueOf(ae.getLeftOperand()) - Integer.valueOf(ae.getRightOperand()));
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