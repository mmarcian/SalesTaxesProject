package org.mmarcian.main.domain;

import java.math.BigDecimal;

public class Product {
	private String name;
	private BigDecimal basePrice;
	private ProductType type;
	//keeping this simple for this project. In a real scenario this would be decided runtime based on product origin/store location
	private boolean imported;
	private BigDecimal taxedPrice;

	public Product (String name, BigDecimal basePrice, ProductType type, boolean imported){
		this.name = name;
		this.basePrice = basePrice;
		this.taxedPrice = basePrice;
		this.type =type;
		this.imported = imported;
	}
	
	public void applyPrice(BigDecimal appliedPrice){
		this.taxedPrice= taxedPrice.add(appliedPrice);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public BigDecimal getTaxedPrice() {
		return taxedPrice;
	}
	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}
	
}
