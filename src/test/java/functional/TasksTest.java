package functional;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		System.setProperty("webdriver.chrome.driver", "C:\\train\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8090/tasks");
		return driver;
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void naoDeveSalvarTarefaComDataPassada() {
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
