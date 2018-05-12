package com.leocaliban.cursos.testes.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
	@Test
	public void deveRealizarUmCadastro() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Batista de Araujo");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);

		combo.selectByVisibleText("Superior");
		
		element = driver.findElement(By.id("elementosForm:esportes"));
		combo = new Select(element);
		
		combo.selectByVisibleText("Futebol");
		
		element = driver.findElement(By.id("elementosForm:cadastrar"));
		element.click();

		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Leonardo"));
		
		Assert.assertEquals("Sobrenome: Batista de Araujo", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Carne Frango Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Futebol", driver.findElement(By.id("descEsportes")).getText());
		
		driver.quit();

	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		driver.quit();
		
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		driver.quit();
	}
	
	
	@Test
	public void deveValidarSexoObrigatorio() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Batista de Araujo");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		driver.quit();
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Batista de Araujo");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		driver.quit();
	}
	
	@Test
	public void deveValidarEsporteIndeciso() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Leonardo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Batista de Araujo");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
				
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		
		combo.selectByVisibleText("Futebol");
		combo.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		driver.quit();
	}
}


