package com.uniovi.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Sdi1920606ComplementariosTest {
	static String PathFirefox65 = "D:\\PROGRAMAS\\FIREFOX\\FIREFOX 65.0.1\\firefox.exe";
	static String Geckdriver024 = "F:\\TERCERO\\SDI\\OneDrive_2020-02-29\\material\\geckodriver024win64.exe";
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Test
	public void registroProfesorValido() {
		PO_PrivateView.loginUserCheck(driver, "99999988F", "123456", "Gestión de profesores");

		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'teachers-menu')]/a", 0);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, 'professor/add')]", 0);

		PO_PrivateView.fillFormAddProfessor(driver, "99999988S", "Sofia", "Garcia", "ciencias");
		PO_View.checkElement(driver, "text", "99999988S");
	}

	@Test
	public void registroProfesorInvalido() {
		PO_PrivateView.loginUserCheck(driver, "99999988F", "123456", "Gestión de profesores");

		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'teachers-menu')]/a", 0);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, 'professor/add')]", 0);

		PO_PrivateView.fillFormAddProfessor(driver, "99999988S", "Sofia", "Garcia", "ciencias");

		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'teachers-menu')]/a", 0);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, 'professor/add')]", 0);

		PO_PrivateView.fillFormAddProfessor(driver, "988S", "Sofdia", "Gardcia", "cidencias");

		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.dni.length", PO_Properties.getSPANISH());

		PO_PrivateView.fillFormAddProfessor(driver, "99999988S", "Sia", "Gardcia", "cidencias");

		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());

		PO_PrivateView.fillFormAddProfessor(driver, "99999988S", "Sofia", "Gar", "cidencias");

		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());

		PO_PrivateView.fillFormAddProfessor(driver, "999999884", "Sofia", "Garcia", "ciencias");

		PO_View.getP(); 
		PO_RegisterView.checkKey(driver, "Error.signup.dni.format", PO_Properties.getSPANISH());

	}

	@Test
	public void registroAñadirProfesorSoloAutorizado() {
		PO_PrivateView.loginUserCheck(driver, "99999993D", "123456", "Gestión de profesores");

		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'teachers-menu')]/a", 0);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, 'professor/add')]", 0);
		By.xpath("//html/body/h1[contains(text(), 'Forbidden')]");
	}

}