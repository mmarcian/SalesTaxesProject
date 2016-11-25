package org.mmarcian.main.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseOrder {
	private Map<String,OrderLine> orderLines = new HashMap<String,OrderLine>();
	private List<BaseTax> taxes;
	private BigDecimal totalAmount = new BigDecimal ("0.0");
	private BigDecimal totalTaxesAmount = new BigDecimal("0.0");

	private PurchaseOrder (){	
	}
	
	public PurchaseOrder (List<BaseTax> taxes){
		if (taxes!=null){
			this.taxes = taxes;
		}
		else this.taxes= new ArrayList<BaseTax>();	
	}
		
	public void addProduct (Product newProduct, int quantity){
		BigDecimal taxAmount =new BigDecimal("0.0");
		//calculating the taxes before everything, so it's done once per product
		for(BaseTax tax: taxes){
			taxAmount =taxAmount.add(tax.getTaxAmount(newProduct));			
		}
		newProduct.applyPrice(taxAmount);
		for (int i=0;i<quantity;i++){
			totalTaxesAmount=totalTaxesAmount.add(taxAmount);
			totalAmount=totalAmount.add(newProduct.getTaxedPrice());
			
			OrderLine orderLine;
			if (orderLines.get(newProduct.getName())!=null){
				orderLine = orderLines.get(newProduct.getName());
				orderLine.addAmount(newProduct.getTaxedPrice());
				orderLine.increaseQuantity();
			}
			else {
				orderLine= new OrderLine(newProduct);
			}
			orderLines.put(newProduct.getName(),orderLine);
		}
	}

	public Map<String,OrderLine> getOrderLines() {
		return orderLines;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public BigDecimal getTotalTaxesAmount() {
		return totalTaxesAmount;
	}
	
	
}
