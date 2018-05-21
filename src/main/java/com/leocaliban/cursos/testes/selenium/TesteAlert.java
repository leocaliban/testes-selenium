package com.leocaliban.cursos.testes.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void iniciar() {
		driver = new ChromeDriver();
		dsl = new DSL(driver);
		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));

		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
	}

	@After
	public void finalizar() {
		driver.quit();
	}
	@Test
	public void deveInteragirComAlertSimples() {		
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceitar();
		Assert.assertEquals("Alert Simples", texto);
		dsl.escrever("elementosForm:nome", texto);
	}
	
	@Test
	public void deveInteragirComAlertConfirmAceitar() {		
		dsl.clicarBotao("confirm");

		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceitar());
				
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceitar());

	}
	
	@Test
	public void deveInteragirComAlertConfirmNegar() {		
		dsl.clicarBotao("confirm");

		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENegar());
				
		Assert.assertEquals("Negado", dsl.alertaObterTextoENegar());
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {		
		dsl.clicarBotao("prompt");

		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("200");
				
		Assert.assertEquals("Era 200?", dsl.alertaObterTextoEAceitar());
		
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceitar());
	}
}
