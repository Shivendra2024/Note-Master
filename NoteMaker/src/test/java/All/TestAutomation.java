package All;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class TestAutomation {

    WebDriver driver;
    WebDriverWait wait; // ✅ Un-commented WebDriverWait

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        WebDriverManager.chromedriver().setup();  // ✅ Ensure ChromeDriver is set up
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://notes-makers.vercel.app/");
        
        // ✅ Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testSignup() {
        driver.findElement(By.cssSelector("button:nth-child(2) strong")).click();
        driver.findElement(By.id("name")).sendKeys("Shivendra");
        driver.findElement(By.id("email")).sendKeys("singh@7607@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Singh@123");
        driver.findElement(By.id("confirmPassword")).sendKeys("Singh@123");

        // ✅ Ensure wait is not null before using it
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/strong[contains(text(),'Sign Up')]")));
        signUpButton.click();
    }

    @Test(priority = 2)
    public void testLogin() {
        driver.get("https://notes-makers.vercel.app/pages/login");
        driver.findElement(By.id("email")).sendKeys("singh@7607@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Singh@123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/strong[contains(text(),'Login')]")));
        loginButton.click();
    }

    @Test(priority = 3)
    public void testCreateNote() {
        driver.get("https://notes-makers.vercel.app/pages/notes");

        WebElement writeNotesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[normalize-space()='Write Notes']")));
        writeNotesButton.click();

        driver.findElement(By.cssSelector("input[placeholder='Notes Title']")).sendKeys("First Note");
        driver.findElement(By.cssSelector(".ql-editor")).sendKeys("This is my first note.");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[normalize-space()='Save Changes']")));
        saveButton.click();
    }

    @Test(priority = 4)
    public void createNotebook() {
        driver.get("https://notes-makers.vercel.app/pages/notes");

        WebElement createNotebookButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[normalize-space()='Create NoteBook']")));
        createNotebookButton.click();

        driver.findElement(By.id("title")).sendKeys("This is a new notebook.");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[normalize-space()='Submit']")));
        submitButton.click();
    }

    @Test(priority = 5)
    public void editNote() {
        driver.get("https://notes-makers.vercel.app/pages/notes");

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[normalize-space()='Edit']")));
        editButton.click();

        driver.findElement(By.cssSelector(".ql-editor")).sendKeys("New update");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[normalize-space()='Save Changes']")));
        saveButton.click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
