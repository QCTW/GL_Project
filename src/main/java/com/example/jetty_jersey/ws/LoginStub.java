package com.example.jetty_jersey.ws;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.glassfish.jersey.internal.util.Base64;

import com.example.jetty_jersey.Dao.*;

import io.netty.handler.codec.base64.Base64Encoder;

@Path("/login")
public class LoginStub {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}/{id}")
	public MRO getUser(@PathParam("username") String username,
			@PathParam("id") int id){
		return new MRO();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/connectMcc")
	public void connection(){
		try {
	        DefaultHttpClient Client = new DefaultHttpClient();
	        Client.getCredentialsProvider().setCredentials(
	                AuthScope.ANY,
	                new UsernamePasswordCredentials("mcc", "mccpass")
	        );

	        HttpGet httpGet = new HttpGet("https://httpbin.org/basic-auth/mcc/mccpass");
	        HttpResponse response = Client.execute(httpGet);

	        System.out.println("response = " + response);

	        BufferedReader breader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        StringBuilder responseString = new StringBuilder();
	        String line = "";
	        while ((line = breader.readLine()) != null) {
	            responseString.append(line);
	        }
	        breader.close();
	        String responseStr = responseString.toString();
	        System.out.println("responseStr = " + responseStr);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
}
