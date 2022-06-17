package com.challenge5.converter.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Moneda extends ConversionUnidad {

	public Moneda(Double cantidad, String convertirDe, String convertirA) {
		super(cantidad, convertirDe, convertirA);
	}

	public void hacerConversionMoneda() {

		try {

			// Setting URL
			String url_str = "https://api.exchangerate.host/convert?from=" + this.getConvertirDe() + "&to="
					+ this.getConvertirA() + "&amount=" + this.getCantidad() + "&places=2";

			// Making Request
			URL url = new URL(url_str);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			// Convert to JSON
			JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
			JsonObject jsonobj = root.getAsJsonObject();

			this.setResultado(jsonobj.get("result").getAsDouble());

			request.disconnect();

		} catch (Exception e) {

		}

	}

//	public static void main(String[] args) {
//		ConversorMoneda moneda = new ConversorMoneda(10, "USD", "COP");
//		
//		System.out.println(moneda.getCantidad());
//	}

}
