package northwind.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id 
	private String ProductID;
	private String ProductName;
	
	@JoinColumn(name="CategoryID")
	@ManyToOne()
	private Category CategoryID;
	private String QuantityPerUnit;
	private String UnitPrice;

	public Product(String productID, String productName, Category categoryID, String quantityPerUnit, String unitPrice) {
		super();
		this.ProductID = productID;
		this.ProductName = productName;
		this.CategoryID = categoryID;
		this.QuantityPerUnit = quantityPerUnit;
		this.UnitPrice = unitPrice;
	}

	
	
	public Product() {
		super();
	}



	public String getProductID() {
		return ProductID;
	}



	public void setProductID(String productID) {
		ProductID = productID;
	}



	public String getProductName() {
		return ProductName;
	}



	public void setProductName(String productName) {
		ProductName = productName;
	}



	public Category getCategoryID() {
		return CategoryID;
	}



	public void setCategoryID(Category categoryID) {
		CategoryID = categoryID;
	}



	public String getQuantityPerUnit() {
		return QuantityPerUnit;
	}



	public void setQuantityPerUnit(String quantityPerUnit) {
		QuantityPerUnit = quantityPerUnit;
	}



	public String getUnitPrice() {
		return UnitPrice;
	}



	public void setUnitPrice(String unitPrice) {
		UnitPrice = unitPrice;
	}	
	
}
