package org.mmarcian.main.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

import org.mmarcian.main.service.Utils;

public class SalesTax extends BaseTax {
	private final BigDecimal RATE= new BigDecimal ("0.10");
	private final ArrayList<ProductType> exemptProducts = new ArrayList<ProductType>(
		    Arrays.asList(ProductType.BOOK,
		    			  ProductType.FOOD,
		    			  ProductType.MEDICAL));
	
	public BigDecimal getTaxAmount(Product product){
		BigDecimal taxAmount = new BigDecimal ("0.0");
		if (!exemptProducts.contains(product.getType())){
			taxAmount=product.getBasePrice().multiply(RATE);
		}
		return Utils.roundTax(taxAmount);
	}
}
