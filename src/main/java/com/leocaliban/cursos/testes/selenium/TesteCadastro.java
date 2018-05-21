package com.leocaliban.cursos.testes.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void iniciar() {
		driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}
	
	@Test
	public void deveRealizarUmCadastro() {	
		dsl.escrever("elementosForm:nome", "Leonardo");
		
		dsl.escrever("elementosForm:sobrenome", "Batista de Araujo");

		dsl.clicarRadio("elementosForm:sexo:0");
		
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		
		dsl.clicarBotao("elementosForm:cadastrar");

		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Leonardo"));
		
		Assert.assertEquals("Sobrenome: Batista de Araujo", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Futebol", dsl.obterTexto("descEsportes"));
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {	
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceitar());
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {		
		dsl.escrever("elementosForm:nome", "Leonardo");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceitar());
	}
	
	
	@Test
	public void deveValidarSexoObrigatorio() {		
		dsl.escrever("elementosForm:nome", "Leonardo");
		dsl.escrever("elementosForm:sobrenome", "Batista de Araujo");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceitar());
	}
	
	@Test
	public void deveValidarComidaVegetariana() {		
		dsl.escrever("elementosForm:nome", "Leonardo");
		dsl.escrever("elementosForm:sobrenome", "Batista de Araujo");
		dsl.clicarRadio("elementosForm:sexo:0");
		
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		
		dsl.clicarBotao("elementosForm:cadastrar");

		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceitar());
	}
	
	@Test
	public void deveValidarEsporteIndeciso() {		
		dsl.escrever("elementosForm:nome", "Leonardo");
		dsl.escrever("elementosForm:sobrenome", "Batista de Araujo");
		dsl.clicarRadio("elementosForm:sexo:0");
				
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceitar());
	}
}


