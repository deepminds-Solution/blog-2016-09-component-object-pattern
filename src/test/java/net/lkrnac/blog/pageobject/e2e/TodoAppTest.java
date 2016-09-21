package net.lkrnac.blog.pageobject.e2e;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import net.lkrnac.blog.pageobject.TodoApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by: Lubos Krnac
 * Date: 2016-07-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TodoApplication.class)
@WebIntegrationTest
public class TodoAppTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testCreateTodos() {
        // GIVEN
        new TodoPageObject(driver).get()

            // WHEN
            .addTodo("testTodo1")
            .addTodo("testTodo2")

            // THEN
            .verifyTodoShown("testTodo1", false)
            .verifyTodoShown("testTodo2", false);
    }

    @Test
    public void testCompleteTodo() {
        // GIVEN
        new TodoPageObject(driver).get()
            .addTodo("testTodo1")
            .addTodo("testTodo2")

            // WHEN
            .clickOnTodoItem("testTodo1")

            // THEN
            .verifyTodoShown("testTodo1", true)
            .verifyTodoShown("testTodo2", false);
    }

    @Test
    public void testSelectActive() {
        // GIVEN
        new TodoPageObject(driver).get()
            .addTodo("testTodo1")
            .addTodo("testTodo2")
            .clickOnTodoItem("testTodo1")

            // WHEN
            .selectActive()

            // THEN
            .verifyTodoNotShown("testTodo1")
            .verifyTodoShown("testTodo2", false);
    }

    @Test
    public void testSelectCompleted() {
        // GIVEN
        new TodoPageObject(driver).get()
            .addTodo("testTodo1")
            .addTodo("testTodo2")
            .clickOnTodoItem("testTodo1")

            // WHEN
            .selectCompleted()

            // THEN
            .verifyTodoShown("testTodo1", true)
            .verifyTodoNotShown("testTodo2");
    }

    @Test
    public void testSelectAll() {
        // GIVEN
        new TodoPageObject(driver).get()
            .addTodo("testTodo1")
            .addTodo("testTodo2")
            .clickOnTodoItem("testTodo1")
            .selectCompleted()

            // WHEN
            .selectAll()

            // THEN
            .verifyTodoShown("testTodo1", true)
            .verifyTodoShown("testTodo2", false);
    }
}
