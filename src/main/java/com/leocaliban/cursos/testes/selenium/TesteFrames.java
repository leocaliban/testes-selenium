package com.leocaliban.cursos.testes.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrames {
	
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
	public void deveInteragirComFrames() {
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String mensagem = dsl.alertaObterTextoEAceitar();
		Assert.assertEquals("Frame OK!", mensagem);
		
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", mensagem);
	}
	
}
