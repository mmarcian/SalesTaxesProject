package org.mmarcian.main.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
	private static BigDecimal round(BigDecimal value, BigDecimal increment,
			RoundingMode roundingMode) {
		if (increment.signum() == 0) {
			return value;
		} else {
			BigDecimal divided = value.divide(increment, 0, roundingMode);
			BigDecimal result = divided.multiply(increment);
			return result;
		}
	}
	private static final BigDecimal TAX_INCREMENT=new BigDecimal("0.05");
	
	public static BigDecimal roundTax(BigDecimal value) {
		
		return round(value,TAX_INCREMENT,RoundingMode.CEILING);
	}
}
