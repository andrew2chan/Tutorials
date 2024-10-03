package com.udacity.jdnd.course1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Course1ApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(Course1ApplicationTests.class);
	@LocalServerPort
	private Integer port;

	private static WebDriver driver;
	private static SignupPage signupPage;
	private static LoginPage loginPage;
	private static ChatPage chatPage;

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		signupPage = new SignupPage(driver);
		loginPage = new LoginPage(driver);
		chatPage = new ChatPage(driver);
	}

	@AfterAll
	public static void afterAll() {
		//driver.quit();
	}

	@Test
	public void testThatRegisterWorks() {
		driver.get("http://localhost:" + port + "/signup");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));

		signupPage.setFirstname("bob");
		signupPage.setLastname("marley");
		signupPage.setUsername("bb");
		signupPage.setPassword("bob");

		signupPage.submit();

		assertEquals(signupPage.successfulRegistration(), "You successfully signed up! Please continue to the login page.");
	}

	@Test
	public void testThatLoginWorks() {
		driver.get("http://localhost:" + port + "/login");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));

		loginPage.setUsername("bb");
		loginPage.setPassword("bob");

		loginPage.submit();

		assertEquals(loginPage.getAlertCount(), 0);
	}

	@Test
	public void testThatUsernameAndMessageMatch() {
		driver.get("http://localhost:" + port + "/chat");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(webDriver -> webDriver.findElement(By.cssSelector("input[type='submit']")));

		chatPage.setMessageText("yee");

		chatPage.submit();

		String username = "bb";

		assertEquals(chatPage.containsUsernameAndPassword(username), true);

	}
}
