package stepDefinition;

import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class StepDefinition {
	WebDriver driver=null;
	@Given("^User is on the Gmail Landing Page$")
	public void user_is_on_the_gmail_landing_page() throws Throwable {
		String usrdir=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", usrdir+"/src/test/java/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.yahoo.com/?.src=ym&.lang=en-US&.intl=us&.done=https%3A%2F%2Fmail.yahoo.com%2Fd&pspid=1197806870&activity=sign-in");

	}

	@When("^User login to the application with valid Username and Password$")
	public void user_login_to_the_application_with_valid_username_and_password() throws Throwable {
		WebElement Uname=driver.findElement(By.xpath("//input[@name='username']"));
		Uname.sendKeys("gitanjalipaul94@yahoo.com");
		WebElement Next=driver.findElement(By.xpath("//input[@name='signin']"));
		Next.click();
		WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("Buri@23115");
		WebElement Next2=driver.findElement(By.xpath("//button[@name='verifyPassword']"));
		Next2.click();

	}

	@Then("^User validates that logged in sucessfully$")
	public void user_validates_that_logged_in_sucessfully() throws Throwable {
		System.out.println("Logged in SuccessFully");

	}

	@And("^User validates the Compose button is displayed$")
	public void user_validates_the_compose_button_is_displayed() throws Throwable {
		WebElement Compose=driver.findElement(By.xpath("//a[@href ='/d/compose/']"));
		Compose.click();
		Thread.sleep(8000);
	}


	@And("^user enters the recepient mail address,Subject and body$")
	public void user_enters_the_recepient_mail_addresssubject_and_body() throws Throwable {
		WebElement recipient=driver.findElement(By.xpath("(//div[@class='typeahead-inputs-container M_0 p_R H_6NIX']//input)[1]"));
		recipient.sendKeys("gitanjalipaul94@yahoo.com");

		recipient.sendKeys(Keys.TAB);
		recipient.sendKeys(Keys.ESCAPE);

		WebElement subject=driver.findElement(By.xpath("//input[@aria-label='Subject']"));
		subject.click();
		subject.sendKeys("Sending Mail from Automation Subject");
		subject.sendKeys(Keys.TAB);
		WebElement Body=driver.findElement(By.xpath("(//div[@id='editor-container']/div)[1]"));
		Body.click();
		Body.sendKeys("Sending Mail from Automation Body");
		Body.sendKeys(Keys.TAB);
	}

	@When("^user uploads the file and clicks send$")
	public void user_uploads_the_file_and_clicks_send() throws Throwable {
		WebElement Attachbutton=driver.findElement(By.xpath("//button[@title='Attach files']"));
		Attachbutton.click();
		Thread.sleep(5000);
		WebElement fromcomputer=driver.findElement(By.xpath("//li[@title='Attach files from computer']"));
		fromcomputer.click();
		StringSelection selectstring = new StringSelection("C:\\Users\\shiva\\Desktop\\file.txt");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectstring,null);

		Thread.sleep(3000);

		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@title='Send this email']/span")).click();

	}

	@Then("^user navigates to the sent folder$")
	public void user_navigates_to_the_sent_folder() throws Throwable {
		driver.findElement(By.xpath("//span[contains(@title,'Sent')]")).click();
	}

	@And("^Verifies the the subject of the recently sent file$")
	public void verifies_the_the_subject_of_the_recently_sent_file() throws Throwable {
		driver.findElement(By.xpath("//span[contains(@title,'Sent')]")).click();
		String text=driver.findElement(By.xpath("(//div[@class='D_F ab_C o_h H_6D6F em_N E_fq7 J_x']/div/span)[1]")).getText();
		assertEquals(text, "Sending Mail from Automation Subject");
		driver.close();
	}

}
