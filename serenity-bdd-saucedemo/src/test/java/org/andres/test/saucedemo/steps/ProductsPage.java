package org.andres.test.saucedemo.steps;

import java.util.List;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends PageObject {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public WebElementFacade getBtnAddToCartByIndex(int index) {
        List<WebElementFacade> elements = findAll(By.cssSelector("button.btn_inventory"));
        return elements.get(index);
    }
}
