package com.Trendlyne.PageObjects;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrendlyneHomePage {
	WebDriver ldriver;
	String shareName;
	FileOutputStream fos;
	File file;
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	public TrendlyneHomePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		String filePath = System.getProperty("user.dir") + "/src/test/java/com/Trendlyne/TestData/Output.txt";

		file = new File(filePath);
		try {
			fos = new FileOutputStream(file, true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FindBy(xpath = "//input[@name='search']")
	WebElement inputSearchTextBox;

	public void inputSearch(String stockName) {
		shareName = stockName;
		inputSearchTextBox.sendKeys(stockName);

		try {
			byte[] shareNameInBytes = shareName.getBytes();
			fos.write(shareNameInBytes);
			String newLine = "\n";
			byte[] newLineInBytes = newLine.getBytes();
			fos.write(newLineInBytes);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FindBy(xpath = "//a[text()='Share Holdings']")
	WebElement stockShareHolding;

	public void stockShareHoldingBtn() throws Exception {
		WebDriverWait wait = new WebDriverWait(ldriver, 20);
		Actions action = new Actions(ldriver);
		String shareSubString = shareName.substring(0, 4);
		WebElement searchResult = ldriver.findElement(
				By.xpath("//li[@class='ui-menu-item']/div[contains(text()," + "'" + shareSubString + "')]"));
		action.moveToElement(searchResult).perform();
		action.moveToElement(stockShareHolding).perform();
		stockShareHolding.click();
		String dropDownXpath = "//div[@class='dropdown']/a[normalize-space()='Shareholding']";
		WebElement dropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropDownXpath)));
		dropDown.click();

		String dropDownXpathElement = "//div[@class='dropdown-menu dropdown-menu-full titlecase p-y-0']/a[normalize-space()='Shareholding']";
		WebElement dropDownShareholdingElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropDownXpathElement)));
		dropDownShareholdingElement.click();

		String publicHoldingXpath = "//li/a[contains(text(),'Non-Institutional')]";
		WebElement publicHoldingElement = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(publicHoldingXpath)));
		publicHoldingElement = ldriver.findElement(By.xpath(publicHoldingXpath));

		JavascriptExecutor jse = (JavascriptExecutor) ldriver;
		jse.executeScript("arguments[0].scrollIntoView(false);", publicHoldingElement);

		publicHoldingElement.click();

		String publicHoldingHistoryXpath = "//tbody/tr[contains(@class, 'fw500')]/td[@class='cen']/a[contains(@href, '/individual-share-capital-upto-rs-2-lacs/')]/i[@class='fa fa-history']";
		WebElement publicHoldingHistory = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(publicHoldingHistoryXpath)));
		publicHoldingHistory.click();

		for (int i = 1; i <= 5; i++) {
			String headerXPath = "//*[@id=\"basemodal\"]/div/div/div/div[3]/table/thead/tr[1]/th[" + i + "]";
			WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(headerXPath)));
			String header_Element_Name = headerElement.getText();
			if (i == 3)
				header_Element_Name = "TOTAL_SHARE";
			else if (i == 4)
				header_Element_Name = "HOLDERS";
			String headerElementWithSpace = header_Element_Name + " 		";
			byte[] headerElementInBytes = headerElementWithSpace.getBytes();
			fos.write(headerElementInBytes);
		}

		String newLine = "\n";
		byte[] newLineInBytes = newLine.getBytes();
		fos.write(newLineInBytes);

		String publicHoldingElementsXPath = "//*[@id='basemodal']/div/div/div/div[3]/table/tbody";
		List<WebElement> elements = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(publicHoldingElementsXPath)));

		WebElement grid = ldriver.findElement(By.xpath(publicHoldingElementsXPath));
		List<WebElement> rows = grid.findElements(By.tagName("tr"));
		int totalRows = rows.size();

		for (int i = 1; i <= totalRows; i++) {

			String rowXPath = "//*[@id=\"basemodal\"]/div/div/div/div[3]/table/tbody/tr[" + i + "]";
			WebElement rowPath = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXPath)));

			List<WebElement> rowElements = rowPath.findElements(By.tagName("td"));
			int numberOfRowElements = rowElements.size();

			for (int j = 1; j < numberOfRowElements && j <= 5; j++) {
				String rowElementsPath = "//*[@id=\"basemodal\"]/div/div/div/div[3]/table/tbody/tr[" + i + "]/td[" + j
						+ "]";
				WebElement rowElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowElementsPath)));
				String rowElementData = rowElement.getText() + " 		";
				byte[] rowElementDataInBytes = rowElementData.getBytes();
				fos.write(rowElementDataInBytes);
			}

			newLine = "\n";
			newLineInBytes = newLine.getBytes();
			fos.write(newLineInBytes);

		}

		String closeButtonXPath = "//button[@type='button' and @class='close']";
		WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(closeButtonXPath)));
		closeButton.click();
	}

}
