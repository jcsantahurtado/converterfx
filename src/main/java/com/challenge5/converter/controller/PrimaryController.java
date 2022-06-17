package com.challenge5.converter.controller;

import java.io.IOException;

import com.challenge5.converter.App;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("fxml/secondary");
    }
}
