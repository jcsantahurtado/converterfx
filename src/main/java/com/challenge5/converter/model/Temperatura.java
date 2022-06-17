package com.challenge5.converter.model;

public class Temperatura extends ConversionUnidad {

	public Temperatura(Double cantidad, String convertirDe, String convertirA) {
		super(cantidad, convertirDe, convertirA);
	}

	public void hacerConversionTemperatura() {

		switch ("De " + this.getConvertirDe() + " a " + this.getConvertirA()) {

		case "De Kelvin a Celsius":
			this.setResultado(this.getCantidad() - 273.15);
			break;

		case "De Kelvin a Fahrenheit":
			this.setResultado(9 * (this.getCantidad() - 273.15) / 5 + 32);
			break;

		case "De Fahrenheit a Celsius":
			this.setResultado(5 * (this.getCantidad() - 32) / 9);
			break;

		case "De Fahrenheit a Kelvin":
			this.setResultado(5 * (this.getCantidad() - 32) / 9 + 273.15);
			break;

		case "De Celsius a Kelvin":
			this.setResultado(this.getCantidad() + 273.15);
			break;

		case "De Celsius a Fahrenheit":
			this.setResultado(9 * this.getCantidad() / 5 + 32);
			break;

		default:
			this.setResultado(this.getCantidad());
			break;
		}

	}

//	public static void main(String[] args) {
//		ConversorMoneda moneda = new ConversorMoneda(10, "USD", "COP");
//		
//		System.out.println(moneda.getCantidad());
//	}

}
