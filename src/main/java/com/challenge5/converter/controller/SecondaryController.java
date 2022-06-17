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
import javafx.scene.input.KeyEvent;

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

	Alert alert = new Alert(Alert.AlertType.ERROR, "Hubo un error!");

	@FXML
	private void botonConvertirMoneda(ActionEvent event) {

		Double cantidad = Double.parseDouble(entraMoneda.getText());
		String from = cbxDeMoneda.getValue();
		String to = cbxAMoneda.getValue();
		Double resultado;

		Moneda moneda = new Moneda(cantidad, from, to);
		moneda.hacerConversionMoneda();
		resultado = moneda.getResultado();

		resultadoConversionMoneda.setText(String.format("%,.2f %s", resultado, to));

	}

	@FXML
	private void botonConvertirTemperatura(ActionEvent event) {

		Double cantidad = Double.parseDouble(entraTemperatura.getText());
		String from = cbxDeTemperatura.getValue();
		String to = cbxATemperatura.getValue();
		Double resultado;

		Temperatura temperatura = new Temperatura(cantidad, from, to);
		temperatura.hacerConversionTemperatura();
		resultado = temperatura.getResultado();

		resultadoConversionTemperatura.setText(String.format("%,.2f º %s", resultado, to));

	}

	@FXML
	private void botonConvertirAlmacenamiento(ActionEvent event) {

		Alert alert = new Alert(Alert.AlertType.ERROR, "Esta opción esta deshabilitada.");
		alert.showAndWait();

	}

	@FXML
	private void txtFieldOnKeyReleased(KeyEvent event) {

		resultadoConversionMoneda.setText("");
		resultadoConversionTemperatura.setText("");
		resultadoConversionAlmacenamiento.setText("");

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

		// Cargando opciones Monedas
		inicializa(cbxDeMoneda, cbxAMoneda, medidasMoneda);

		// Cargando opciones Temperatura
		inicializa(cbxDeTemperatura, cbxATemperatura, medidasTemperatura);

		// Cargando opciones de Almacenamiento
		inicializa(cbxDeAlmacenamiento, cbxAAlmacenamiento, medidasAlmacenamiento);

	}

	private void cambiarOnAction(ComboBox<String> de, ComboBox<String> a) {

		Integer index1 = de.getSelectionModel().getSelectedIndex();
		Integer index2 = a.getSelectionModel().getSelectedIndex();

		de.getSelectionModel().select(index2);
		a.getSelectionModel().select(index1);

	}

	private void inicializa(ComboBox<String> de, ComboBox<String> a, String[] medidas) {

		de.getItems().addAll(medidas);
		de.getSelectionModel().select(1);
		a.getItems().addAll(medidas);
		a.getSelectionModel().select(0);

	}

}