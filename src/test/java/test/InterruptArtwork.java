package test;

import java.io.File;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InterruptArtwork {

	private static WebDriver driver;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;
	private static Logger log = LogManager.getLogger();

	private static DashboardFlow dashboardFlow;

	public InterruptArtwork(WebDriver driver) {
		InterruptArtwork.driver = driver;
		InterruptArtwork.js = (JavascriptExecutor) driver;
		InterruptArtwork.wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
	}

	public String interruptArtworkFlow(int regionOption, int packageType, String artworkUrl, String lidUrl)
			throws InterruptedException {
		log.info("entered into interrupt artwork, going to ad hoc project");
		WebElement region_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/header/app-header/div/div/div[2]/mat-form-field/div/div[1]/div[2]/mat-icon")));
		js.executeScript("arguments[0].click();", region_selection_element);
		log.info(" going to ad hoc project");

		WebElement region_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[" + regionOption + "]/span")));
		js.executeScript("arguments[0].click();", region_option_element);
		log.info("entered into interrupt artwork, going to ad hoc project");

		WebElement adhoc_project_btn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/main/app-get-started/div/div/div/div/div[4]/button[2]")));
		js.executeScript("arguments[0].click();", adhoc_project_btn);
		log.info("entered into interrupt artwork, going to ad hoc project");

		Thread.sleep(2000);

		WebElement package_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[1]/div[2]/mat-form-field/div/div[1]/div/mat-select/div/div[1]/span/span")));
		js.executeScript("arguments[0].click();", package_selection_element);

		WebElement package_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[" + packageType + "]/span")));
		js.executeScript("arguments[0].click();", package_option_element);

		WebElement aw_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[1]/app-file-upload/div/div[2]/div/input")));
		File artwork = new File(Constants.Flow1ArtworkUrl);
		log.info(artwork);
		aw_upload_element.sendKeys(artwork.getAbsolutePath());
		
		WebElement aw_upload_element_img = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[1]/app-file-upload/div/div[2]/div[1]/div/img")));
		log.info("Artwork Uploaded. Going to Label Right Home Page.");
		
		Thread.sleep(5000);
		
		WebElement projectName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/p")));
		String projectNamevalue = projectName.getText();
		log.info( projectNamevalue);
		String prjctName = projectNamevalue.split(" - ")[1];

		return prjctName;

	}
}
