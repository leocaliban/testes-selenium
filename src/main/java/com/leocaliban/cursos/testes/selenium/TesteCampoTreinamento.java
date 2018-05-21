package com.leocaliban.cursos.testes.selenium;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCampoTreinamento {
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
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Escrever texto para teste");
		
		Assert.assertEquals("Escrever texto para teste", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testTextFieldDuplo() {
		dsl.escrever("elementosForm:nome", "Primeira Palavra");
		Assert.assertEquals("Primeira Palavra", dsl.obterValorCampo("elementosForm:nome"));
		
		dsl.escrever("elementosForm:nome", "Segunda Palavra");
		Assert.assertEquals("Segunda Palavra", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void deveInteragirComTextArea() {	
		dsl.escrever("elementosForm:sugestoes", "Escrever texto para teste");
		
		Assert.assertEquals("Escrever texto para teste", dsl.obterValorCampo("elementosForm:sugestoes"));
		
	}
	
	
	@Test
	public void deveInteragirComRadioButton() {		
		dsl.clicarRadio("elementosForm:sexo:0");
		
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveInteragirComCheckBox() {	
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
	}
	
	
	@Test
	public void deveInteragirComboBox() {	
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");

		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresDoComboBox() {	
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));	
	}
	
	@Test
	public void deveVerificarValoresDoComboBoxMultiplo() {	
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.desmarcarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "Karate")));
	}
	
	@Test
	public void deveInteragirComBotoes() {		
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValorElemento("buttonSimple"));
	}
	
	
	@Test
	public void deveInteragirComLinks() {		
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	
	@Test
	public void deveBuscarTextosNaPagina() {		
		//busca por tag
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		//busca por classe
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
}
