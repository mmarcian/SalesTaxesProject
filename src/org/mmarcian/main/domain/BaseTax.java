package org.mmarcian.main.domain;

import java.math.BigDecimal;

public abstract class BaseTax {
	public BigDecimal getTaxAmount (Product product){
		return new BigDecimal(0);
	}
}
