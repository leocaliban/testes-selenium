package com.leocaliban.cursos.testes.selenium.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.leocaliban.cursos.testes.selenium.TesteCadastro;
import com.leocaliban.cursos.testes.selenium.TesteCampoTreinamento;
import com.leocaliban.cursos.testes.selenium.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class,
	TesteCampoTreinamento.class
})
public class SuiteTeste {

}
