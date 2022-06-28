package com.challenge5.converter.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.challenge5.converter.model.Moneda;
import com.challenge5.converter.model.Temperatura;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController implements Initializable {

	@FXML
	private TextField entraMoneda;

	@FXML
	private TextField entraTemperatura;

	@FXML
	private TextField entraAlmacenamiento;

	@FXML
	private ComboBox<String> cbxDeMoneda;

	@FXML
	private ComboBox<String> cbxAMoneda;

	@FXML
	private ComboBox<String> cbxDeTemperatura;

	@FXML
	private ComboBox<String> cbxATemperatura;

	@FXML
	private ComboBox<String> cbxDeAlmacenamiento;

	@FXML
	private ComboBox<String> cbxAAlmacenamiento;

	@FXML
	private Label resultadoConversionMoneda;

	@FXML
	private Label resultadoConversionTemperatura;

	@FXML
	private Label resultadoConversionAlmacenamiento;

	private String[] medidasMoneda = { "COP", "USD", "EUR", "GBP", "JPY", "KRW" };
	private String[] medidasTemperatura = { "Celsius", "Fahrenheit", "Kelvin" };
	private String[] medidasAlmacenamiento = { "Kilobyte", "Megabyte", "Gigabyte", "Terabyte", "Petabyte" };

	@FXML
	private void botonConvertirMoneda(ActionEvent event) {

		if (entraMoneda.getText().isBlank()) {
			return;
		}

		Double cantidad;
		String from;
		String to;
		Double resultado;

		cantidad = validaCampoCantidad(entraMoneda.getText());
		from = cbxDeMoneda.getValue();
		to = cbxAMoneda.getValue();

		if (cantidad <= 0) {
			resultadoConversionMoneda.setText("");
			return;
		}

		Moneda moneda = new Moneda(cantidad, from, to);
		moneda.hacerConversionMoneda();
		resultado = moneda.getResultado();

		resultadoConversionMoneda.setText(String.format("%,.2f %s", resultado, to));

	}

	@FXML
	private void botonConvertirTemperatura(ActionEvent event) {

		Double cantidad;
		String from;
		String to;
		Double resultado;

		cantidad = Double.parseDouble(entraTemperatura.getText());
		from = cbxDeTemperatura.getValue();
		to = cbxATemperatura.getValue();

		Temperatura temperatura = new Temperatura(cantidad, from, to);
		temperatura.hacerConversionTemperatura();
		resultado = temperatura.getResultado();

		resultadoConversionTemperatura.setText(String.format("%,.2f º %s", resultado, to));

	}

	@FXML
	private void botonConvertirAlmacenamiento(ActionEvent event) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Esta opción esta deshabilitada.");
		alert.showAndWait();

	}

	private Double validaCampoCantidad(String txtEntraCantidad) {

		Double cantidad;

		try {

			cantidad = Double.parseDouble(txtEntraCantidad);

			if (cantidad <= 0) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Ingrese una cantidad superior a 0");
				alert.showAndWait();

				cantidad = -.1;
			}

		} catch (Exception e) {

			Alert alert = new Alert(Alert.AlertType.ERROR, "Ingrese una cantidad válida, solo números");
			alert.showAndWait();

			cantidad = .0;
		}

		return cantidad;

	}

	@FXML
	private void buttonCambiarOnAction(ActionEvent event) {

		if (event.getTarget().toString().contains("Moneda")) {

			cambiarOnAction(cbxDeMoneda, cbxAMoneda);
			botonConvertirMoneda(event);

		} else if (event.getTarget().toString().contains("Temperatura")) {

			cambiarOnAction(cbxDeTemperatura, cbxATemperatura);
			botonConvertirTemperatura(event);

		} else {

			cambiarOnAction(cbxDeAlmacenamiento, cbxAAlmacenamiento);
			botonConvertirTemperatura(event);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Loading options Currencies
		inicializa(cbxDeMoneda, cbxAMoneda, medidasMoneda);

		// Loading options Temperature
		inicializa(cbxDeTemperatura, cbxATemperatura, medidasTemperatura);

		// Loading Storage options
		inicializa(cbxDeAlmacenamiento, cbxAAlmacenamiento, medidasAlmacenamiento);

	}

	private void cambiarOnAction(ComboBox<String> de, ComboBox<String> a) {

		Integer index1 = de.getSelectionModel().getSelectedIndex();
		Integer index2 = a.getSelectionModel().getSelectedIndex();

		de.getSelectionModel().select(index2);
		a.getSelectionModel().select(index1);

	}

	private void inicializa(ComboBox<String> de, ComboBox<String> a, String[] unidadesDeConversion) {

		de.getItems().addAll(unidadesDeConversion);
		de.getSelectionModel().select(1);
		a.getItems().addAll(unidadesDeConversion);
		a.getSelectionModel().select(0);

	}

}