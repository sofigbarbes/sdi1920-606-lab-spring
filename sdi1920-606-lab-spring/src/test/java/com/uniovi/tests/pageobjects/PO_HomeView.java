package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.utils.SeleniumUtils;

public class PO_HomeView extends PO_NavView {

	static public void checkWelcome(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamosa quesecargueel saludodebienvenidaenEspañol
		PO_HomeView.checkWelcome(driver, locale1);
		// Cambiamosa segundoidioma
		PO_HomeView.changeIdiom(driver, textIdiom2);
		// COmprobamos queel textodebienvenidahayacambiadoa segundoidioma
		PO_HomeView.checkWelcome(driver, locale2);
		// Volvemosa Español.
		PO_HomeView.changeIdiom(driver, textIdiom1);
		// Esperamosa quesecargueel saludodebienvenidaenEspañol
		PO_HomeView.checkWelcome(driver, locale1);
	}
}
