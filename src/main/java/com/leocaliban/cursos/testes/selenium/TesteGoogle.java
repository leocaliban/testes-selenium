package com.leocaliban.cursos.testes.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	WebDriver driver;
	
	@Before
	public void iniciar() {
		driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}
	
	@Test
	public void teste() {		
		
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());

	}
}
