package com.example.shopify;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shopify.models.Product;
import com.example.shopify.tasks.ProductsTask;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class ProductListFragment extends ListFragment implements AsyncJsonFragment {

	private List<Product> mProductList;
	private ArrayAdapter<Product> mAdapter;
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
		mProductList = new ArrayList<Product>();
        mAdapter = new ArrayAdapter<Product>(inflater.getContext(), R.layout.product_list_item ,mProductList) {
        	@Override
        	public View getView(int position, View convertView, ViewGroup parent) {
        		View row;
         
        		if (null == convertView) {
        			row = inflater.inflate(R.layout.product_list_item, null);
        		} else {
        			row = convertView;
        		}
         
        		TextView tv = (TextView) row.findViewById(R.id.text1);
        		tv.setText(((Product)getItem(position)).title);
        		tv = (TextView) row.findViewById(R.id.text2);
        		tv.setText(((Product)getItem(position)).vendor);
         
        		return row;
        	}
        };
        setListAdapter(mAdapter);
    	ProductsTask r = new ProductsTask(this);
    	r.execute();
    	return inflater.inflate(R.layout.fragment_product_list, container, false); 
	}

	@Override
	public void recieveJsonData(String data) {
        if(data==null) {
        	return;
        }
    	Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject element = (JsonObject)parser.parse(data);
        JsonElement products = element.get("products");
        Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
        mProductList = gson.fromJson(products, listType);
        mAdapter.addAll(mProductList);

        this.getListView().invalidateViews();
	}

}
