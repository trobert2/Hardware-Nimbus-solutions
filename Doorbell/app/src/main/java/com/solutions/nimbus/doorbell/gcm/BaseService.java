package com.solutions.nimbus.doorbell.gcm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class BaseService {

	private String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {

		InputStream in = entity.getContent();

		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];

			n = in.read(b);

			if (n > 0)
				out.append(new String(b, 0, n));

		}

		return out.toString();

	}
	
	protected String executeService(String url) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(url);
		
		String text = null;
		
		try {

			HttpResponse response = httpClient.execute(httpGet,
					localContext);

			HttpEntity entity = response.getEntity();

			text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();

		}

		return text;
		
	}

}
