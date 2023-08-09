package calc;

import java.util.HashMap;
import java.util.Map;

public class ArithmeticExpression {
	
	private char arithmeticOperation;
	private String leftOperand;
	private String rightOperand;
	private String numeralSystem;

	private String expression;
	private char[] operations;
	private String[] romanOperands;
	private Map<String, String> operands = new HashMap<>();
	
	public ArithmeticExpression(String expression) {
		this.expression = expression;
		this.operations = new char[] {'+', '-', '*', '/'};
		this.romanOperands = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};		
		for(int i = 0; i < romanOperands.length; i++) {
			this.operands.put(String.valueOf(i + 1), romanOperands[i]);
		}
		
		try {
			arithmeticOperationCheck();
			numeralSystemCheck();
		} catch (NumberOfOperationException e) {
			e.printStackTrace();
		} catch (OperandsException e) {
			e.printStackTrace();
		}
	}
	
	private void arithmeticOperationCheck() throws NumberOfOperationException {
		int numberOfOperators = 0;
		
		for(char operation : operations) {
			if(expression.indexOf(operation) != -1) {
				int symbolCount = (int) expression.chars().filter(symbol -> symbol == operation).count();
				if(symbolCount == 1) {
					numberOfOperators++;
					arithmeticOperation = operation;
				}
				else {
					numberOfOperators = symbolCount;
				}
			}
		}
		
		if(numberOfOperators > 1) {
			throw new NumberOfOperationException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
		}
		else if(numberOfOperators == 0) {
			throw new NumberOfOperationException("Строка не является математической операцией");
		}
	}
	
	private void numeralSystemCheck() throws OperandsException {
		String leftSubstr = expression.substring(0, expression.indexOf(arithmeticOperation)).replaceAll("\\s", "");
		String rightSubstr = expression.substring(expression.indexOf(arithmeticOperation) + 1, expression.length()).replaceAll("\\s", "");
		
		if(operands.keySet().contains(leftSubstr) && operands.keySet().contains(rightSubstr)) {
			this.numeralSystem = "arabic";
			this.leftOperand = leftSubstr;
			this.rightOperand = rightSubstr;
		}
		else if(operands.values().contains(leftSubstr) && operands.values().contains(rightSubstr)) {
			this.numeralSystem =  "roman";
			this.leftOperand = operands.keySet()
	                .stream()
	                .filter(key -> leftSubstr.equals(operands.get(key)))
	                .findFirst().get();
			this.rightOperand = operands.keySet()
	                .stream()
	                .filter(key -> rightSubstr.equals(operands.get(key)))
	                .findFirst().get();
		}
		else if(!operands.keySet().contains(leftSubstr) && !operands.keySet().contains(rightSubstr) 
				|| !operands.values().contains(leftSubstr) && !operands.values().contains(rightSubstr)) {
			throw new OperandsException("Аргументы вычислительной операции не соответствуют требованиям");
		}
		else {
			throw new OperandsException("Одновременно используются разные системы счисления");
		}
	}

	public char getArithmeticOperation() {
		return arithmeticOperation;
	}

	public String getLeftOperand() {
		return leftOperand;
	}

	public String getRightOperand() {
		return rightOperand;
	}

	public String getNumeralSystem() {
		return numeralSystem;
	}
}
