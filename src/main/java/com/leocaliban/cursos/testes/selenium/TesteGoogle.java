package com.leocaliban.cursos.testes.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

	@Test
	public void teste() {
		/** Se a variavel de ambiente não for setada, é nescessário essa linha
			System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		*/
		WebDriver driver = new ChromeDriver();
		
		//Define a posição da janela de teste
		driver.manage().window().setPosition(new Point(50, 50));
		
		//Define o tamanho da janela de teste
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
	}
}
