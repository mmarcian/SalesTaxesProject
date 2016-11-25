package org.mmarcian.main.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.mmarcian.main.service.Utils;

public class ImportTax extends BaseTax {
	private final BigDecimal RATE= new BigDecimal ("0.05");
	
	public BigDecimal getTaxAmount(Product product){
		BigDecimal taxAmount = new BigDecimal ("0.0");
		if (product.isImported()){
			taxAmount=product.getBasePrice().multiply(RATE);
		}
		return Utils.roundTax(taxAmount);
	}
}
