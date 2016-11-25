package org.mmarcian.main.domain;

import java.math.BigDecimal;

public class OrderLine {
	int quantity=0;
	Product product;
	BigDecimal amount;
	
	private OrderLine(){
		
	}
	
	public OrderLine(Product product){
		this.quantity=1;
		this.product = product;
		this.amount=product.getTaxedPrice();
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void increaseQuantity() {
		this.quantity++;
	}
	public Product getProductName() {
		return product;
	}
	public void setProductName(Product product) {
		this.product = product;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public BigDecimal addAmount(BigDecimal newAmount){
		amount=amount.add(newAmount);
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
