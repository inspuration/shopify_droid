package com.example.shopify.models;

import com.google.gson.annotations.SerializedName;

public class Image {
	@SerializedName("created_at") 
	public String createdAt;
	
	@SerializedName("id") 
	public long id;
	
	@SerializedName("position") 
	public long position;
	
	@SerializedName("product_id") 
	public long productId;
	
	@SerializedName("updated_at") 
	public String updatedAt;
	
	@SerializedName("src") 
	public String src;
}
