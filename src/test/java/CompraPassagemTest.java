// ========== Importações necessárias ==========

// JUnit - Framework de testes
import static org.junit.jupiter.api.Assertions.assertEquals; // Importa método para verificar se valores esperados e reais são iguais
import org.junit.jupiter.api.AfterEach; // Executa após cada teste
import org.junit.jupiter.api.BeforeEach; // Executa antes de cada teste
import org.junit.jupiter.api.Test; // Marca o método como um teste

// Selenium - Automação de navegador
import org.openqa.selenium.By; // Localizador de elementos na página
import org.openqa.selenium.WebDriver; // Interface principal do Selenium WebDriver
// import org.openqa.selenium.WebElement; // Elemento HTML da página (comentado)
import org.openqa.selenium.chrome.ChromeDriver; // Implementação do WebDriver para o navegador Chrome

// ========== Classe de Teste ==========
public class CompraPassagemTest {

    // Atributo para controlar o navegador
    private WebDriver driver;

    // ===== Método executado antes de cada teste =====
    @BeforeEach
    public void iniciar() {
        driver = new ChromeDriver(); // Inicia o Chrome
        driver.manage().window().maximize(); // Maximiza a janela
    }

    // ===== Método executado após cada teste =====
    @AfterEach
    public void finalizar() {
        driver.quit(); // Fecha o navegador (comentado para manter o navegador aberto após o teste)
    }

    // ===== Caso de teste: Comprar uma passagem =====
    @Test
    public void comprarPassagem() {
        driver.get("https://www.blazedemo.com"); // Abre o site da aplicação

        // ===== Seleção simplificada da origem e destino =====
        driver.findElement(By.name("fromPort")).click(); // Clica no campo de origem
        driver.findElement(By.xpath("//option[. = \"São Paolo\"]")).click(); // Seleciona a opção "São Paolo"

        driver.findElement(By.name("toPort")).click(); // Clica no campo de destino
        driver.findElement(By.xpath("//option[. = \"New York\"]")).click(); // Seleciona a opção "New York"

        driver.findElement(By.cssSelector("input.btn.btn-primary")).click(); // Clica no botão "Find Flights"
        // Dica: outros localizadores úteis são By.id, By.name, By.className, By.tagName, By.xpath, By.cssSelector

        // ===== Verificação da nova página =====
        assertEquals("Flights from São Paolo to New York:", driver.findElement(By.cssSelector("h3")).getText()); // Confirma se o título da página está correto

        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click(); // Clica no botão "Choose This Flight" do primeiro voo listado

        // ===== Verificação da página de compra =====
        assertEquals("Your flight from TLV to SFO has been reserved.", driver.findElement(By.cssSelector("h2")).getText()); // Confirma a mensagem de reserva

        // ===== Preenchimento do formulário de compra =====
        driver.findElement(By.id("inputName")).sendKeys("David Medeiros"); // Nome
        driver.findElement(By.id("address")).sendKeys("Test"); // Endereço
        driver.findElement(By.id("city")).sendKeys("Rio de Janeiro"); // Cidade
        driver.findElement(By.id("state")).sendKeys("Rio de Janeiro"); // Estado
        driver.findElement(By.id("zipCode")).sendKeys("11111-222"); // CEP

        driver.findElement(By.id("cardType")).click(); // Abre as opções de tipo de cartão
        driver.findElement(By.xpath("//option[@value='visa']")).click(); // Seleciona "Visa"

        driver.findElement(By.id("creditCardNumber")).sendKeys("1111.2222.3333.4444"); // Número do cartão
        driver.findElement(By.id("creditCardMonth")).sendKeys("12"); // Mês
        driver.findElement(By.id("creditCardYear")).sendKeys("2028"); // Ano
        driver.findElement(By.id("nameOnCard")).sendKeys("David Medeiros"); // Nome no cartão

        driver.findElement(By.id("rememberMe")).click(); // Marca a opção "Remember Me"
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click(); // Clica para finalizar a compra

        // ===== Verificação final =====
        assertEquals("Thank you for your purchase today!", driver.findElement(By.cssSelector("h1")).getText()); // Confirma se a compra foi concluída com sucesso
    }
}