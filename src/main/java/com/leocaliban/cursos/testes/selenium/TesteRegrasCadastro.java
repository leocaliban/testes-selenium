package com.leocaliban.cursos.testes.selenium;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.leocaliban.cursos.testes.selenium.pages.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String mensagem;
	@Parameter(value=6)
	public String cenario;
	
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
	
	@Parameters(name = "{6}")
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][]{
			{"", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio", 
				"deveValidarNomeObrigatorio"},
			{"Leonardo", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio", 
				"deveValidarSobreNomeObrigatorio"},
			{"Leonardo", "Batista de Araujo", "", Arrays.asList(), new String[] {}, 
				"Sexo eh obrigatorio", "deveValidarSexoObrigatorio"},
			{"Leonardo", "Batista de Araujo", "Masculino", Arrays.asList("Carne", "Vegetariano"), 
				new String[] {}, "Tem certeza que voce eh vegetariano?", "deveValidarComidaVegetariana"},
			{"Leonardo", "Batista de Araujo", "Masculino", Arrays.asList("Carne"), 
				new String[] {"Futebol", "O que eh esporte?"}, "Voce faz esporte ou nao?", "deveValidarEsporteIndeciso"}
		});
	}
	
	
	@Test
	public void deveValidarRegras() {		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}
		if(sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}
		if(comidas.contains("Carne")) {
			page.setComidaCarne();
		}
		if(comidas.contains("Pizza")) {
			page.setComidaPizza();
		}
		if(comidas.contains("Vegetariano")) {
			page.setComidaVegetariano();
		}
		page.setEsporte(esportes);
		page.cadastrar();
		
		Assert.assertEquals(mensagem, dsl.alertaObterTextoEAceitar());
	}
}
