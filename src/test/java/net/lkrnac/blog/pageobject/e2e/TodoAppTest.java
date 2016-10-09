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
            .getTodoList()
            .verifyItemShown("testTodo1", false)
            .verifyItemShown("testTodo2", false);
    }

    @Test
    public void testCompleteTodo() {
        // GIVEN
        new TodoPageObject(driver).get()
            .addTodo("testTodo1")
            .addTodo("testTodo2")
            .getTodoList()

            // WHEN
            .clickOnTodoItem("testTodo1")

            // THEN
            .verifyItemShown("testTodo1", true)
            .verifyItemShown("testTodo2", false);
    }

    @Test
    public void testSelectTodosActive() {
        // GIVEN
        TodoPageObject todoPage = new TodoPageObject(driver).get();

        todoPage
            .addTodo("testTodo1")
            .addTodo("testTodo2")
            .getTodoList()
            .clickOnTodoItem("testTodo1");

        // WHEN
        todoPage
            .selectActive()

            // THEN
            .getTodoList()
            .verifyItemNotShown("testTodo1")
            .verifyItemShown("testTodo2", false);
    }

    @Test
    public void testSelectTodosCompleted() {
        // GIVEN
        TodoPageObject todoPage = new TodoPageObject(driver).get();
        todoPage
            .addTodo("testTodo1")
            .addTodo("testTodo2")
            .getTodoList()
            .clickOnTodoItem("testTodo1");

        // WHEN
        todoPage
            .selectCompleted()

            // THEN
            .getTodoList()
            .verifyItemShown("testTodo1", true)
            .verifyItemNotShown("testTodo2");
    }

    @Test
    public void testSelectTodosAll() {
        // GIVEN
        TodoPageObject todoPage = new TodoPageObject(driver).get();
        todoPage
            .addTodo("testTodo1")
            .addTodo("testTodo2")
            .getTodoList()
            .clickOnTodoItem("testTodo1");
        todoPage
            .selectCompleted()

            // WHEN
            .selectAll()

            // THEN
            .getTodoList()
            .verifyItemShown("testTodo1", true)
            .verifyItemShown("testTodo2", false);
    }

    @Test
    public void testCreateGroceryItems() {
        // GIVEN
        new TodoPageObject(driver).get()

            // WHEN
            .addGroceryItem("avocados")
            .addGroceryItem("tomatoes")

            // THEN
            .getGroceryList()
            .verifyItemShown("avocados", false)
            .verifyItemShown("tomatoes", false);
    }

    @Test
    public void testCompleteGroceryItem() {
        // GIVEN
        new TodoPageObject(driver).get()
            .addGroceryItem("avocados")
            .addGroceryItem("tomatoes")
            .getGroceryList()

            // WHEN
            .clickOnTodoItem("avocados")

            // THEN
            .verifyItemShown("avocados", true)
            .verifyItemShown("tomatoes", false);
    }

    @Test
    public void testSelectGroceryItemsActive() {
        // GIVEN
        TodoPageObject todoPage = new TodoPageObject(driver).get();

        todoPage
            .addGroceryItem("avocados")
            .addGroceryItem("tomatoes")
            .getGroceryList()
            .clickOnTodoItem("avocados");

        // WHEN
        todoPage
            .selectActive()

            // THEN
            .getGroceryList()
            .verifyItemNotShown("avocados")
            .verifyItemShown("tomatoes", false);
    }

    @Test
    public void testSelectGroceryItemsCompleted() {
        // GIVEN
        TodoPageObject todoPage = new TodoPageObject(driver).get();
        todoPage
            .addGroceryItem("avocados")
            .addGroceryItem("tomatoes")
            .getGroceryList()
            .clickOnTodoItem("avocados");

        // WHEN
        todoPage
            .selectCompleted()

            // THEN
            .getGroceryList()
            .verifyItemShown("avocados", true)
            .verifyItemNotShown("tomatoes");
    }

    @Test
    public void testSelectGroceryItemsAll() {
        // GIVEN
        TodoPageObject todoPage = new TodoPageObject(driver).get();
        todoPage
            .addGroceryItem("avocados")
            .addGroceryItem("tomatoes")
            .getGroceryList()
            .clickOnTodoItem("avocados");
        todoPage
            .selectCompleted()

            // WHEN
            .selectAll()

            // THEN
            .getGroceryList()
            .verifyItemShown("avocados", true)
            .verifyItemShown("tomatoes", false);
    }
}
