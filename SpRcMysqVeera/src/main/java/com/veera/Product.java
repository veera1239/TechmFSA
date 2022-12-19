package com.veera;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prods")
public class Product {
public Product() {
	
}
    @Id
    private String id;
    private String pName;
    private String loc;
	public Product(String id, String pName, String loc) {
		super();
		this.id = id;
		this.pName = pName;
		this.loc = loc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

	

    
    
}
