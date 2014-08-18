package com.example.shopify.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Product {
	
	@SerializedName("body_html")
	public String bodyHtml;
	
	@SerializedName("created_at") 
	public String createdAt;

	@SerializedName("handle") 
	public String handle;
	
	@SerializedName("id") 
	public long id;
	
	@SerializedName("product_type") 
	public String productType;
	
	@SerializedName("published_at") 
	public String publishedAt;
	
	@SerializedName("published_scope") 
	public String publishedScope;
	
	@SerializedName("template_suffix") 
	public String templateSuffix;
	
	@SerializedName("title") 
	public String title;
	
	@SerializedName("updated_at") 
	public String updatedAt;
	
	@SerializedName("vendor") 
	public String vendor;
	
	@SerializedName("tags") 
	public String tags;
	
	//variants
	
	//options
	
	public List<Image> images;
	
	@SerializedName("image") 
	public Image image;
	
}
