package functional;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//System.setProperty("webdriver.chrome.driver", "C:\\train\\Selenium\\chromedriver\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap =  DesiredCapabilities.chrome();
		//ChromeOptions op = new ChromeOptions();
		//op.setBinary("C:\\train\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.15.12:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.15.12:8090/tasks");
		return driver;
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		try {
			driver.findElement(By.xpath("//*[@id=\'addTodo\']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'task\']")));
			
			driver.findElement(By.xpath("//*[@id=\'task\']")).sendKeys("Teste funcional automatizado");
			
			driver.findElement(By.xpath("//*[@id=\'dueDate\']")).sendKeys("10/10/2021");
			
			driver.findElement(By.xpath("//*[@id=\'saveButton\']")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Success!", message);
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		try {
			driver.findElement(By.xpath("//*[@id=\'addTodo\']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'task\']")));
			
			driver.findElement(By.xpath("//*[@id=\'dueDate\']")).sendKeys("10/10/2021");
			
			driver.findElement(By.xpath("//*[@id=\'saveButton\']")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the task description", message);
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		try {
			driver.findElement(By.xpath("//*[@id=\'addTodo\']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'task\']")));
			
			driver.findElement(By.xpath("//*[@id=\'task\']")).sendKeys("Teste funcional automatizado");
			
			driver.findElement(By.xpath("//*[@id=\'saveButton\']")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the due date", message);
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		try {
			driver.findElement(By.xpath("//*[@id=\'addTodo\']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'task\']")));
			
			driver.findElement(By.xpath("//*[@id=\'task\']")).sendKeys("Teste funcional automatizado");
			
			driver.findElement(By.xpath("//*[@id=\'dueDate\']")).sendKeys("10/01/2021");
			
			driver.findElement(By.xpath("//*[@id=\'saveButton\']")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Due date must not be in past", message);
		}finally {
			driver.quit();
		}
	}
	
}
