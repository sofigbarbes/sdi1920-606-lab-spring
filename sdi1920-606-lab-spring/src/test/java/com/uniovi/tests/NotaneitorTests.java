package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.utils.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorTests {

	static String PathFirefox65 = "D:\\PROGRAMAS\\FIREFOX\\FIREFOX\\firefox.exe";
	static String Geckdriver024 = "D:\\PROGRAMAS\\FIREFOX\\MATERIAL\\geckodriver024win64.exe";
	/*static String PathFirefox65 = "D:\\PROGRAMAS\\FIREFOX\\firefox.exe";
	static String Geckdriver024 = "D:\\PROGRAMAS\\FIREFOX\\geckodriver024win64.exe";*/
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
	public void PR01() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}

	// PR02. OPción de navegación. Pinchar en el enlace Registro en la página home

	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
	}

	// PR03. OPción de navegación. Pinchar en el enlace Identificate en la página
	// home

	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	}

	// PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta
	// Español

	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH()); //
		SeleniumUtils.esperarSegundos(driver, 2);
	}

	// PR05. Prueba del formulario de registro. registro con datos correctos

	@Test
	public void PR05() { // Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777"); // Comprobamos que entramos
																							// en la sección privada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	// PR06. Prueba del formulario de registro. DNI repetido en la BD, Nombrecorto,
	// // .... pagination

	@Test
	public void PR06() { // Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary"); // Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
		PO_View.getP(); // COmprobamos el error de DNI repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH()); // Rellenamos el
																									// formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777"); // COmprobamos el error de
																							// Nombre corto .
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH()); // Rellenamos el
																									// formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Per", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Per", "7777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.password.length", PO_Properties.getSPANISH());
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Perez", "77777", "7777");
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());

	}

	// PRN. Loguearse con exito desde el ROl de Usuario, 99999990D, 123456

	@Test
	public void PR07() { // Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456"); // COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");

	}

	// profe

	@Test
	public void PR08() { // Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999993D", "123456"); // COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "modificar");

	}

	// admin

	@Test
	public void PR09() { // Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999988F", "123456"); // COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Gestión de Usuarios");

	}

	// alumno

	@Test
	public void PR10() { // Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456"); // COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");

	}

	// alumno y desconexion

	@Test
	public void PR11() { // Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); // Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456"); // COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse
	// usando el rol de estudiante
	@Test
	public void PR12() {
		PO_PrivateView.loginUserCheck(driver, "99999990A", "123456", "Notas del usuario");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	// PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion
	// = Nota A2.
	// P13. Ver la lista de Notas.
	@Test
	public void PR13() {
		PO_PrivateView.loginUserCheck(driver, "99999990A", "123456", "Notas del usuario");
		SeleniumUtils.esperarSegundos(driver, 1);
		// Contamos las notas
		By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]");
		driver.findElement(enlace).click();
		SeleniumUtils.esperarSegundos(driver, 1);
		// Esperamos por la ventana de detalle
		PO_View.checkElement(driver, "text", "Detalles de la nota");
		SeleniumUtils.esperarSegundos(driver, 1);
		// Ahora nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	// P14. Loguearse como profesor y Agregar Nota A2.
	// P14. Esta prueba podría encapsularse mejor ...
	@Test
	public void PR14() {

		PO_PrivateView.loginUserCheck(driver, "99999993D", "123456", "99999993D");

		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id,'marks-menu')]/a", 0);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href, 'mark/add')]", 0);

		PO_PrivateView.fillFormAddMark(driver, 3, "Nota Nueva 1", "8");

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@class, 'page-link')]", 3);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@class, 'page-link')]", 3);

		PO_View.checkElement(driver, "text", "Nota Nueva 1");

		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	// PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota
	// Nueva 1.
	// PRN. Ver la lista de Notas.
	@Test
	public void PR15() {
		PO_PrivateView.loginUserCheck(driver, "99999993D", "123456", "99999993D");

		PO_PrivateView.checkElementClickIndex(driver, "//li[contains(@id, 'marks-menu')]/a", 0);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@href,'mark/list')]", 0);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@class, 'page-link')]", 3);

		PO_PrivateView.checkElementClickIndex(driver,
				"//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]", 0);

		PO_PrivateView.checkElementClickIndex(driver, "//a[contains(@class, 'page-link')]", 3);

		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva 1", PO_View.getTimeout());

		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

}