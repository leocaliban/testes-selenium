package com.leocaliban.cursos.testes.selenium;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	@Test
	public void testeTextField() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Escrever texto para teste");
		
		Assert.assertEquals("Escrever texto para teste", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		driver.quit();
	}

	@Test
	public void deveInteragirComTextArea() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Escrever texto para teste");
		
		Assert.assertEquals("Escrever texto para teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCheckBox() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		
		driver.quit();
	}
	
	
	@Test
	public void deveInteragirComboBox() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		
		//combo.selectByIndex(3); SELECIONA PELO INDEX DO COMBO
		//combo.selectByValue("superior"); SELECIONA PELO VALUE DETERMINADO
		combo.selectByVisibleText("2o grau completo"); //SELECIONA PELO TEXTO VISÍVEL NO COMBO (VISÃO DO USUÁRIO)

		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarValoresDoComboBox() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		
		List<WebElement> options = combo.getOptions();

		Assert.assertEquals(8, options.size());
		
		driver.quit();
	}
	
	
	@Test
	public void deveVerificarValoresDoComboBoxMultiplo() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("Karate");
		
		List<WebElement> itensSelecionados = combo.getAllSelectedOptions();
		Assert.assertEquals(3, itensSelecionados.size());
		driver.quit();
	}
	
	
	@Test
	public void deveInteragirComBotoes() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		driver.quit();
	}
}
