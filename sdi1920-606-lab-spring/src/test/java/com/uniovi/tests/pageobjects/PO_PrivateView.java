package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.utils.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {

	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	static public void loginUserCheck(WebDriver driver, String user, String password, String condition) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, user, password);
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", condition);
	}
	
	
	static public List<WebElement> checkElementClickIndex(WebDriver driver,String xPath, int index) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", xPath);
		elementos.get(index).click();
		return elementos;

	}
	

	static public void fillFormAddProfessor(WebDriver driver,  String dnip, String namep, String lastnamep, String categoria) {
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement name = driver.findElement(By.name("nombre"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		WebElement lastname = driver.findElement(By.name("apellido"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(lastnamep);

		WebElement category = driver.findElement(By.name("categoria"));
		category.click();
		category.clear();
		category.sendKeys(lastnamep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	
}
