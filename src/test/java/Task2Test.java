import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Task2Test {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void countNexignMainPage() {
        driver.get("https://nexign.com/ru");

        WebElement pageBody = driver.findElement(By.tagName("body"));
        List<WebElement> pageTitleElements = driver.findElements(By.xpath("//*[@title]"));

        String visibleText;
        String keyWord = "Nexign";

        int countNexignBody = countWord(pageBody.getText(), keyWord);
        int countTitleElements = 0;

        for (WebElement element : pageTitleElements) {
            visibleText = element.getText() + element.getDomAttribute("title");
            countTitleElements += countWord(visibleText, keyWord);
        }

        System.out.println("Количество упоминаний 'Nexign' на главной странице: " + countNexignBody);
        System.out.println("Количество упоминаний 'Nexign' на главной странице, учитывая заголовок страницы: "
                + (countNexignBody + countTitleElements));
    }

    private int countWord(String text, String keyWord) {
        String textLow = text.toLowerCase();
        String keyWordLow = keyWord.toLowerCase();

        int count = 0;
        int index = 0;
        int lenKeyWord = keyWordLow.length();

        while ((index = textLow.indexOf(keyWordLow, index)) != -1) {
            count++;
            index += lenKeyWord;
        }

        return count;
    }
}
