package com.example.shopify.tasks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.example.shopify.AsyncJsonFragment;
import com.example.shopify.api.ShopifyEndpoints;

public class ProductsTask extends AsyncTask<Void, String, String>{

	static String PATH = "/products.json";
	
	AsyncJsonFragment fragment;
	
	public ProductsTask(AsyncJsonFragment fragment) {
		this.fragment = fragment;
	}
	
    @Override
    protected String doInBackground(Void... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
        	UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
        			ShopifyEndpoints.API_KEY, 
        			ShopifyEndpoints.PASSWORD);
        	HttpGet getRequest = new HttpGet(ShopifyEndpoints.HOSTNAME + PATH);
        	getRequest.addHeader(BasicScheme.authenticate(creds,"US-ASCII",false));
            response = httpClient.execute(getRequest);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(result==null) {
        	return;
        }
        fragment.recieveJsonData(result);
        //Do anything with response..
    }
    
    public interface GetJSONListener {
        public void onRemoteCallComplete(String jsonFromNet);
    }
}