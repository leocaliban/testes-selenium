package com.leocaliban.cursos.testes.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

	public static void main(String[] args) {
		/** Se a variavel de ambiente não for setada, é nescessário essa linha
			System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		*/
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
	}
}
