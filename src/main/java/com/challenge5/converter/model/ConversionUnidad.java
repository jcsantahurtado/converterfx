package com.challenge5.converter.model;

public class ConversionUnidad {

	private Double cantidad;
	private String convertirDe; // Example: USD
	private String convertirA; // Example: COP
	private Double resultado;

	public ConversionUnidad(Double cantidad, String convertirDe, String convertirA) {
		this.cantidad = cantidad;
		this.convertirDe = convertirDe;
		this.convertirA = convertirA;

	}

	public Double getCantidad() {
		return cantidad;
	}

	public String getConvertirDe() {
		return convertirDe;
	}

	public String getConvertirA() {
		return convertirA;
	}

	public Double getResultado() {
		return resultado;
	}
	
	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}

}
