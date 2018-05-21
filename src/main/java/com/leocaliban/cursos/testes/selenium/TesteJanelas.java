package com.leocaliban.cursos.testes.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteJanelas {
	
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
	public void deveInteragirComJanelas() {		
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Teste Popup.");
		driver.close();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "Teste Concluído.");
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {	
		dsl.clicarBotao("buttonPopUpHard");
		
		System.out.println(driver.getWindowHandle()); //obtendo o id da janela atual
		System.out.println(driver.getWindowHandles()); // ids das janelas gerenciadas
		
		dsl.trocarJanela((String)driver.getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Escrevendo na janela nova!");
		
		dsl.trocarJanela((String)driver.getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "Escrevendo na janela principal!");	
	}
}
