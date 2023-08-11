package calc;

import java.util.List;

public class RomanNumsDetection {
	int roman;

	public RomanNumsDetection(int roman) {
		this.roman = roman;		
	}

	public String result() {
		if ((roman <= 0) || (roman > 4000)) {
	        throw new IllegalArgumentException(roman + " is not in range (0,4000]");
	    }

	    List<Object> romanNums = RomanNums.getReverseSortedValues();

	    int i = 0;
	    StringBuilder sb = new StringBuilder();

	    while ((roman > 0) && (i < romanNums.size())) {
	        RomanNums currentSymbol = (RomanNums) romanNums.get(i);
	        if (currentSymbol.getValue() <= roman) {
	            sb.append(currentSymbol.name());
	            roman -= currentSymbol.getValue();
	        } else {
	            i++;
	        }
	    }

	    return sb.toString();
	}
}
