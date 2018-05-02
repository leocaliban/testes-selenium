package com.leocaliban.cursos.testes.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {
	
	@Test
	public void deveInteragirComAlertSimples() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		Assert.assertEquals("Alert Simples", textoAlerta);
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta);
		
		driver.quit();

	}
	
	@Test
	public void deveInteragirComAlertConfirmAceitar() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("confirm")).click();
		
		Alert alert = driver.switchTo().alert();

		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.accept();
				
		Assert.assertEquals("Confirmado", alert.getText());
		alert.accept();
		driver.quit();

	}
	
	@Test
	public void deveInteragirComAlertConfirmNegar() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("confirm")).click();
		
		Alert alert = driver.switchTo().alert();

		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.dismiss();
				
		Assert.assertEquals("Negado", alert.getText());
		alert.accept();
		driver.quit();

	}
}
