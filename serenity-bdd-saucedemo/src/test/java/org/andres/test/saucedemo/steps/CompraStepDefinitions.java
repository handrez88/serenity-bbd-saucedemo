package org.andres.test.saucedemo.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

public class CompraStepDefinitions {

    @Managed
    WebDriver driver;

    Actor actor = Actor.named("Usuario");

    @Before
    public void before() {
        // Configuración inicial antes de cada escenario
        actor.can(BrowseTheWeb.with(driver));
    }

    @Given("que estoy en la página de inicio de sesión")
    public void irAPaginaDeInicioDeSesion() {
        actor.attemptsTo(Open.url("https://www.saucedemo.com/"));
    }

    @When("ingreso mis credenciales válidas")
    public void ingresoCredenciales() {
        actor.attemptsTo(Enter.theValue("standard_user").into("#user-name"));
        actor.attemptsTo(Enter.theValue("secret_sauce").into("#password"));
    }

    @When("hago clic en login")
    public void clickEnLogin() {
        actor.attemptsTo(Click.on("#login-button"));
    }

    @Then("debería ser redirigido a la página de inventario")
    public void verificarPaginaInventario() {
        actor.should(seeThat(TheWebPage.currentUrl(), equalTo("https://www.saucedemo.com/inventory.html")));
    }

    @When("añado el producto #{int} y #{int} al carrito")
    public void seleccionarItems(int item1, int item2) {
        ProductsPage productsPage = new ProductsPage(driver);
        WebElementFacade product1 = productsPage.getBtnAddToCartByIndex(item1);
        WebElementFacade product2 = productsPage.getBtnAddToCartByIndex(item2);
        actor.attemptsTo(Click.on(product1));
        actor.attemptsTo(Click.on(product2));
    }

    @When("reviso el total del carrito")
    public void revisoTotalDelCarrito() {
        actor.attemptsTo(Click.on(By.cssSelector("a[data-test=\"shopping-cart-link\"]")));
    }

    @When("hago clic en checkout")
    public void hacerCheckout() {
        actor.attemptsTo(Click.on(By.cssSelector("#checkout")));
    }

    @When("lleno el formulario con los siguientes datos:")
    public void llenarFormularioCheckout(DataTable dataTable) {
        List<Map<String, String>> tabla = dataTable.asMaps(String.class, String.class);
        Map<String, String> fila1 = tabla.get(0);

        actor.attemptsTo(Enter.theValue(fila1.get("Nombre")).into(By.cssSelector("#first-name")));
        actor.attemptsTo(Enter.theValue(fila1.get("Apellido")).into(By.cssSelector("#last-name")));
        actor.attemptsTo(Enter.theValue(fila1.get("Código Postal")).into(By.cssSelector("#postal-code")));

        actor.attemptsTo(Click.on(By.cssSelector("input#continue")));
    }

    @When("confirmo la compra")
    public void finalizerCheckout() {
        actor.attemptsTo(Click.on(By.cssSelector("#finish")));
    }

    @Then("debería salir el mensaje {string}")
    public void validarMensaje(String mensaje) {
        actor.should(seeThat(Text.of(By.cssSelector("h2.complete-header")), containsStringIgnoringCase(mensaje)));
    }
}
