package com.leocaliban.cursos.testes.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteJanelas {
	
	@Test
	public void deveInteragirComJanelas() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Teste Popup");
		driver.close();
		
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("Teste Concluído");
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		
		System.out.println(driver.getWindowHandle()); //obtendo o id da janela atual
		System.out.println(driver.getWindowHandles()); // ids das janelas gerenciadas
		
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		
		driver.findElement(By.tagName("textarea")).sendKeys("Escrevendo na janela nova!");
		
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		
		driver.findElement(By.tagName("textarea")).sendKeys("Escrevendo na janela principal!");

		driver.quit();
		
	}
	
}
