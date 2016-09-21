package net.lkrnac.blog.pageobject.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.*;


/**
 * Created by: Lubos Krnac
 * Date: 2016-07-14.
 */
public class TodoPageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    public TodoPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public TodoPageObject get() {
        driver.get("localhost:8080");
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")));
        return this;
    }

    private WebElement findElementWithText(String text) {
        return driver.findElement(getConditionForText(text));
    }

    private List<WebElement> findElementsWithText(String text) {
        return driver.findElements(getConditionForText(text));
    }

    private By getConditionForText(String text) {
        return By.xpath(format("//*[text()='%s']", text));
    }

    public TodoPageObject addTodo(String todo) {
        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys(todo);
        WebElement button = driver.findElement(By.tagName("button"));
        button.click();
        return this;
    }

    public TodoPageObject clickOnTodoItem(String todoItem) {
        findElementWithText(todoItem).click();
        return this;
    }

    public TodoPageObject selectAll() {
        findElementWithText("All").click();
        return this;
    }

    public TodoPageObject selectActive() {
        findElementWithText("Active").click();
        return this;
    }

    public TodoPageObject selectCompleted() {
        findElementWithText("Completed").click();
        return this;
    }

    public TodoPageObject verifyTodoShown(String todoItem, boolean expectedStrikethrough) {
        WebElement todoElement = findElementWithText(todoItem);
        assertNotNull(todoElement);
        boolean actualStrikethrough = todoElement.getAttribute("style").contains("text-decoration: line-through;");
        assertEquals(expectedStrikethrough, actualStrikethrough);
        return this;
    }

    public TodoPageObject verifyTodoNotShown(String todoItem) {
        assertTrue(findElementsWithText(todoItem).isEmpty());
        return this;
    }
}
