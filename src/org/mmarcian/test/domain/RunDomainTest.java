package org.mmarcian.test.domain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mmarcian.main.domain.BaseTax;
import org.mmarcian.main.domain.ImportTax;
import org.mmarcian.main.domain.Product;
import org.mmarcian.main.domain.ProductType;
import org.mmarcian.main.domain.PurchaseOrder;
import org.mmarcian.main.domain.SalesTax;
import org.mmarcian.main.service.OrderPrinter;

public class RunDomainTest {

	PurchaseOrder order;
	@Before
	public void setUp() throws Exception {
		
		/*
		 * the idea in a proper application would be to build the order with a factory, so you can have different kind of orders
		 * with different kind of applied taxes
		 */
		List <BaseTax> taxes =  new ArrayList<BaseTax>(
			    Arrays.asList(new SalesTax(),
			    			  new ImportTax())
			    			  );
		order= new PurchaseOrder(taxes);
	}
	
	@After
	public void printResult() throws Exception {
		
		OrderPrinter.PrintOrder(order);
	}
	/*test case 1 of the document*/
	@Test
	public void Case1() {
		Product book = new Product("book", new BigDecimal("12.49"), ProductType.BOOK, false);
		order.addProduct(book, 1);
		
		Product musicCd = new Product ("music cd", new BigDecimal("14.99"),ProductType.MUSIC,false);
		order.addProduct(musicCd, 1);
		
		Product chocolateBar = new Product ("chocolate bar",new BigDecimal("0.85"),ProductType.FOOD,false);
		order.addProduct(chocolateBar, 1);
		
	}
	
	/*test case 2 of the document*/
	@Test
	public void Case2() {
		Product chocolateBox = new Product ("imported box of chocolates",new BigDecimal("10"),ProductType.FOOD,true);
		order.addProduct(chocolateBox, 1);
		
		Product perfume = new Product ("imported bottle of perfume", new BigDecimal("47.50"),ProductType.COSMETIC,true);
		order.addProduct(perfume, 1);
	}
	
	/*test case 4 of the document*/
	@Test
	public void Case3() {
		Product importedPerfume = new Product ("imported bottle of perfume", new BigDecimal("27.99"),ProductType.COSMETIC,true);
		order.addProduct(importedPerfume, 1);
		
		Product localPerfume = new Product ("bottle of perfume", new BigDecimal("18.99"),ProductType.COSMETIC,false);
		order.addProduct(localPerfume, 1);
		
		Product headachePills = new Product ("packet of headache pills",new BigDecimal("9.75"),ProductType.MEDICAL,false);
		order.addProduct(headachePills, 1);
		
		Product chocolateBox = new Product ("box of imported chocolates",new BigDecimal("11.25"),ProductType.FOOD,true);
		order.addProduct(chocolateBox, 1);
	}
	
	/*testing with multiple quantity per line*/
	@Test
	public void Case4() {
		Product importedPerfume = new Product ("importedPerfume", new BigDecimal("27.99"),ProductType.COSMETIC,true);
		order.addProduct(importedPerfume, 2);
		assertEquals(order.getTotalAmount(),new BigDecimal("64.38"));
		assertEquals(order.getTotalTaxesAmount(),new BigDecimal("8.40"));
	}
	
	/*testing with multiple line with the same product */
	@Test
	public void Case5() {
		Product importedPerfume = new Product ("importedPerfume", new BigDecimal("27.99"),ProductType.COSMETIC,true);
		order.addProduct(importedPerfume, 1);
		
		Product importedPerfume2 = new Product ("importedPerfume", new BigDecimal("27.99"),ProductType.COSMETIC,true);
		order.addProduct(importedPerfume2, 1);
		assertEquals(order.getTotalAmount(),new BigDecimal("64.38"));
		assertEquals(order.getTotalTaxesAmount(),new BigDecimal("8.40"));
	}
}
