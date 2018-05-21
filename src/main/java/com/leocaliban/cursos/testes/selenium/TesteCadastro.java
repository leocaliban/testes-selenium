package com.leocaliban.cursos.testes.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.leocaliban.cursos.testes.selenium.pages.CampoTreinamentoPage;

public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void iniciar() {
		driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(50, 50));
		driver.manage().window().setSize(new Dimension(1080, 500));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo-treinamento/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}
	
	@Test
	public void deveRealizarUmCadastro() {	
		page.setNome("Leonardo");
		page.setSobrenome("Batista de Araujo");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Futebol");
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Leonardo"));
		
		Assert.assertEquals("Sobrenome: Batista de Araujo", page.obterSobreNomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Futebol", page.obterEsporteCadastro());
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {	
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceitar());
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {		
		page.setNome("Leonardo");
		page.cadastrar();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceitar());
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {		
		page.setNome("Leonardo");
		page.setSobrenome("Batista de Araujo");
		
		page.cadastrar();
		
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceitar());
	}
	
	@Test
	public void deveValidarComidaVegetariana() {	
		page.setNome("Leonardo");
		page.setSobrenome("Batista de Araujo");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceitar());
	}
	
	@Test
	public void deveValidarEsporteIndeciso() {		
		page.setNome("Leonardo");
		page.setSobrenome("Batista de Araujo");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Futebol", "O que eh esporte?");
		page.cadastrar();
		
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceitar());
	}
}


